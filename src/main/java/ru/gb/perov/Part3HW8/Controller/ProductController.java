package ru.gb.perov.Part3HW8.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.perov.Part3HW8.Data.Product;
import ru.gb.perov.Part3HW8.Service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")

public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public List<Product> findAllProducts() {
        return productService.findAllProducts();
    }

    @GetMapping("/{id}")
    public ArrayList<Optional<Product>> findById(@PathVariable Long id) {
        return productService.findProductById(id);
    }

    @PostMapping("")
    public void addProduct(@RequestParam String title, @RequestParam Double cost) {
        productService.addProduct(title, cost);
    }

    @GetMapping("/filtered")
    public List<Product> findAllBetween(@RequestParam Double costMin, @RequestParam Double costMax) {
        return productService.findAllBetween(costMin, costMax);
    }

    @GetMapping("/min-max")
    public List<Double> findFullIntervalCost() {
        return productService.findFullIntervalCost();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProductById(@PathVariable("id") Long id) {
        if (productService.findProductById(id).size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            productService.deleteById(id);

            if (productService.findProductById(id).size() != 0) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
    }
}