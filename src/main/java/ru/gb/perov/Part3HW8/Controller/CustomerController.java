package ru.gb.perov.Part3HW8.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.gb.perov.Part3HW8.Data.Customer;
import ru.gb.perov.Part3HW8.Service.CustomerService;


import java.util.List;

@RestController
@RequestMapping("")

public class CustomerController {

    @Autowired
    private CustomerService customerService;

//    public ProductController(ProductDaoService productDaoService) {
//        this.productDaoService = productDaoService;
//    }

    @GetMapping("/all-customers")
    public List<Customer> findAllCustomer() {
        return customerService.findAllCustomer();
    }

//
//    @GetMapping("/product/{id}")
//    public List<Product> findById(@PathVariable Long id) {
//        return productDaoService.findById(id);
//    }
//
//
//    @PostMapping("/add")
//    public void addProduct(@RequestParam String title, @RequestParam Double cost) {
//        productDaoService.addProduct(title, cost);
//    }
//
//    @GetMapping("/delete/{id}")
//    public void addProduct(@PathVariable Long id) {
//        productDaoService.deleteById(id);
//    }

}