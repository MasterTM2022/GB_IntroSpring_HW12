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


    @GetMapping("/all-customers")
    public List<Customer> findAllCustomer() {
        return customerService.findAllCustomer();
    }
}