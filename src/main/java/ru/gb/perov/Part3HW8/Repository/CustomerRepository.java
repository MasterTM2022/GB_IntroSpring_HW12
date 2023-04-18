package ru.gb.perov.Part3HW8.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.perov.Part3HW8.Data.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
