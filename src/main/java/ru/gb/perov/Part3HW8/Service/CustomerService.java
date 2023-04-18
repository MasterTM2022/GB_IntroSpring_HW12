package ru.gb.perov.Part3HW8.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.perov.Part3HW8.Data.Customer;
import ru.gb.perov.Part3HW8.Repository.CustomerRepository;
import ru.gb.perov.Part3HW8.Repository.OrderRepository;

import java.util.List;
@Service()
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderRepository orderRepository;

    public List<Customer> findAllCustomer() {
        return customerRepository.findAll();
    }
}
