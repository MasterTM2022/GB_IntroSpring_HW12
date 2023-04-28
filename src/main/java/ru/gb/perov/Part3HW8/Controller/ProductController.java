package ru.gb.perov.Part3HW8.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.perov.Part3HW8.DTO.ProductDTO;
import ru.gb.perov.Part3HW8.Service.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")

public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public Page<ProductDTO> findAll(
            @RequestParam(name = "id", required = false) Long id,
            @RequestParam(name = "minPrice", required = false) Double minPrice,
            @RequestParam(name = "maxPrice", required = false) Double maxPrice,
            @RequestParam(name = "partTitle", required = false) String partTitle,
            @RequestParam(name = "page", required = false) Integer page) {
        if (page == null || page < 1) {
            page = 1;
        }
        return productService.findAll(id, minPrice, maxPrice, partTitle, page).map(
                ProductDTO::new);
    }

    @GetMapping("/{id}")
    public List<ProductDTO> findById(@PathVariable Long id) {
        return productService.findProductById(id).stream().filter(Optional::isPresent).map(p -> new ProductDTO(p.get())).collect(Collectors.toList());

    }

    @PostMapping("")
    public void addProduct(@RequestParam String title, @RequestParam Double price) {
        productService.addProduct(title, price);
    }

    @GetMapping("/min-max")
    public List<Double> findFullIntervalPrice() {
        return productService.findFullIntervalPrice();
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

    @GetMapping("/{id}/add_to_cart")
    public void addProductToCart(@PathVariable("id") Long id) {
        productService.addProductToCart(id);
    }
}