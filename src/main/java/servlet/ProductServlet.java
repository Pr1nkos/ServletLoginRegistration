package servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Product;
import service.ProductService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * The type Product servlet.
 */
@WebServlet(
        urlPatterns = "/secure/products")
public class ProductServlet extends HttpServlet {
    private ProductService productService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        productService = (ProductService) servletContext.getAttribute("productService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<Product> allProducts = productService.getAllProducts();
        List<Product> userProducts = allProducts.stream()
                .filter(product -> product.getUserID().equals(session.getAttribute("userID")))
                .toList();
        req.setAttribute("userProducts", userProducts);
        req.getRequestDispatcher("/secure/products.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String imageURL = req.getParameter("imageURL");
        HttpSession session = req.getSession();

        productService.createProduct(Product.builder()
                .uuid(UUID.randomUUID())
                .name(name)
                .imageURL(imageURL)
                .userID((UUID) session.getAttribute("userID"))
                .build());

        resp.sendRedirect("/secure/products");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID productID = UUID.fromString(req.getParameter("productID"));
        productService.deleteProduct(productID);
        resp.sendRedirect("/secure/products");
    }
}