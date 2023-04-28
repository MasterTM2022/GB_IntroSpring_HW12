package ru.gb.perov.Part3HW8.Service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.gb.perov.Part3HW8.Data.Product;
import ru.gb.perov.Part3HW8.Repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ru.gb.perov.Part3HW8.Repository.Specifications.ProductsSpecifications.*;

@Service()
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CartService cartService;


    public Page<Product> findAll(Long id, Double minPrice, Double maxPrice, String partTitle, Integer page){
        Specification<Product> spec = Specification.where(null);
        if (id != null) {
            spec= spec.and(idEquals(id));
        }
        if (minPrice != null) {
            spec = spec.and(priceGreaterOrEqualsThan(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(priceLesserOrEqualsThan(maxPrice));
        }
        if (partTitle != null) {
            spec = spec.and(titleLike(partTitle));
        }
        return productRepository.findAll(spec, PageRequest.of(page - 1, 25));
    }

    public List<Optional<Product>> findProductById(Long id) {
        ArrayList<Optional<Product>> products = new ArrayList();
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            products.add(product);
        }
        return products;
    }

    public void addProduct(String title, Double price) {
        Product product = new Product();
        product.setProductName(title);
        product.setProductPrice(price);
        productRepository.saveAndFlush(product);
    }

    public void deleteById(Long id) {
        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Trying to delete not-free product...");
        }
    }

    public List<Double> findFullIntervalPrice() {
        ArrayList<Double> interval = new ArrayList<>();
        interval.add(0, productRepository.findMinPrice());
        interval.add(1, productRepository.findMaxPrice());
        return interval;
    }

    public List<Product> findAllBetween(Double minParam, Double maxParam) {
        return productRepository.findAllBetween(Math.min(minParam, maxParam), Math.max(minParam, maxParam));
    }

    public void addProductToCart(Long id) {
        try {
            cartService.addProductToCart(id);
        } catch (Exception e) {
            System.out.println("Product with ID=" + id + " not found...");
        }
        cartService.findAll();
    }
}