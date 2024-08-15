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

public class ProductDAO extends BaseDAO<Product, UUID> {

    public ProductDAO(ObjectMapper mapper, File file) {
        super(mapper, file, new TypeReference<>() {

        });
    }

    @Override
    protected UUID getId(Product product){
        return product.getUuid();
    }
}