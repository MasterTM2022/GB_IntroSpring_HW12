package ru.gb.perov.Part3HW8.Repository.Specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.gb.perov.Part3HW8.Data.Product;

public class ProductsSpecifications {
    public static Specification<Product> idEquals(Long id) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("id"), id);
    }

        public static Specification<Product> priceGreaterOrEqualsThan(Double minPrice) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("productPrice"), minPrice);
    }

    public static Specification<Product> priceLesserOrEqualsThan(Double maxPrice) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("productPrice"), maxPrice);
    }

    public static Specification<Product> titleLike(String titlePart) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("productName"), String.format("%%%s%%", titlePart));
    }
}
