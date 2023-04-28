package ru.gb.perov.Part3HW8.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.perov.Part3HW8.Data.Customer;
import ru.gb.perov.Part3HW8.Repository.CustomerRepository;

import java.util.List;
@Service()
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public List<Customer> findAllCustomer() {
        return customerRepository.findAll();
    }
}
