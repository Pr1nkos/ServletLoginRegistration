package dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import model.Product;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * The type Product dao.
 */
@RequiredArgsConstructor
public class ProductDAO {

    private final ObjectMapper mapper;
    private final File file;

    /**
     * Save.
     *
     * @param product the product
     * @throws IOException the io exception
     */
    public void save(Product product) throws IOException {
        List<Product> products = getAllProducts();
        products.add(product);
        mapper.writeValue(file, products);
    }

    /**
     * Gets all products.
     *
     * @return the all products
     * @throws IOException the io exception
     */
    public List<Product> getAllProducts() throws IOException {
        return mapper.readValue(file, new TypeReference<>() {
        });
    }

    /**
     * Delete product by id.
     *
     * @param uuid the uuid
     * @throws IOException the io exception
     */
    public void deleteProductById(UUID uuid) throws IOException {
        List<Product> products = getAllProducts();
        products.removeIf(product -> product.getUuid().equals(uuid));
        mapper.writeValue(file, products);
    }
}