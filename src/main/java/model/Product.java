package model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * The type Product.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private UUID uuid;
    private UUID userID;
    private String name;
    private String imageURL;
}
