package ru.gb.perov.Part3HW8.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.perov.Part3HW8.Data.Product;
import ru.gb.perov.Part3HW8.Repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service()
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public ArrayList<Optional<Product>> findProductById(Long id) {
        ArrayList<Optional<Product>> products = new ArrayList();
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            products.add(product);
        }
        return products;
    }

    public void addProduct(String title, Double cost) {
        Product product = new Product();
        product.setProductName(title);
        product.setProductCost(cost);
        productRepository.saveAndFlush(product);
    }

    public void deleteById(Long id) {
        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Trying to delete not-free product...");
        }
    }

    public List<Double> findFullIntervalCost() {
        ArrayList<Double> interval = new ArrayList<>();
        interval.add(0, productRepository.findMinCost());
        interval.add(1, productRepository.findMaxCost());
        return interval;
    }

    public List<Product> findAllBetween(Double minParam, Double maxParam){
        return productRepository.findAllBetween(Math.min(minParam, maxParam), Math.max(minParam, maxParam));
    }
}