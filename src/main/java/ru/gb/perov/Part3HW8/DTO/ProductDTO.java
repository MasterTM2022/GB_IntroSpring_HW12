package ru.gb.perov.Part3HW8.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.gb.perov.Part3HW8.Data.Product;

import java.util.Optional;


@Data
public class ProductDTO {
    private Long id;
    private String productName;
    private double productPrice;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.productName = product.getProductName();
        this.productPrice = product.getProductPrice();
    }

    public ProductDTO(Optional<Product> p) {
    }
}