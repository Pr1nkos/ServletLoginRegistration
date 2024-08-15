package service;

import dao.ProductDAO;
import lombok.RequiredArgsConstructor;
import model.Product;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * The type Product service.
 */
@RequiredArgsConstructor
public class ProductService {
    private final ProductDAO productDAO;

    /**
     * Create product.
     *
     * @param product the product
     * @throws IOException the io exception
     */
    public void createProduct(Product product) throws IOException {
        productDAO.save(product);
        System.out.println(product);
    }

    /**
     * Gets all products.
     *
     * @return the all products
     * @throws IOException the io exception
     */
    public List<Product> getAllProducts() throws IOException {
        return productDAO.findAll();
    }

    /**
     * Delete product.
     *
     * @param productUUID the product uuid
     * @throws IOException the io exception
     */
    public void deleteProduct(UUID productUUID) throws IOException {
        productDAO.delete(productUUID);
    }
}