package ru.gb.perov.Part3HW8.Data;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
//@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "products")

public class Product {
    @Getter
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_price")
    private double productPrice;

    @Column(name = "product_cost")
    private double productCost;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                '}';
    }
}