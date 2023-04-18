package ru.gb.perov.Part3HW8.Service;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.gb.perov.Part3HW8.Data.Customer;
import ru.gb.perov.Part3HW8.Data.Order;
import ru.gb.perov.Part3HW8.Data.OrderString;
import ru.gb.perov.Part3HW8.Data.Product;
import ru.gb.perov.Part3HW8.Repository.CustomerRepository;
import ru.gb.perov.Part3HW8.Repository.OrderRepository;
import ru.gb.perov.Part3HW8.Repository.OrderStringRepository;
import ru.gb.perov.Part3HW8.Repository.ProductRepository;

import java.util.Date;

@Component
public class DataGenerator {
    private static final int NUMBER_START_PRODUCTS = 20;
    private static final int NUMBER_START_CUSTOMERS = 5;
    private static final int NUMBER_START_ORDERS = 15;
    private static final int NUMBER_START_ORDER_STRINGS = 100;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderStringRepository orderStringRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void generateProductsOnStartUp() {
        Faker faker = new Faker();

        for (int i = 1; i <= NUMBER_START_PRODUCTS; i++) {
            Product p = new Product();
            p.setProductName(faker.food().ingredient());
            p.setProductCost(faker.number().randomDouble(2, 1, 100));
            productRepository.saveAndFlush(p);
        }

        for (int i = 1; i <= NUMBER_START_CUSTOMERS; i++) {
            Customer c = new Customer();
            c.setCustomerName(faker.name().fullName());
            customerRepository.saveAndFlush(c);
        }

        for (int i = 1; i <= NUMBER_START_ORDERS; i++) {
            Order o = new Order();
            o.setDate(DateUtils.toCalendar(faker.date().between(new Date(new Date().getTime() - 1000L * 60 * 60 * 24 * 365), new Date())));
            if (i <= NUMBER_START_CUSTOMERS) {
                o.setCustomer(customerRepository.getReferenceById((long) i));
            } else {
                o.setCustomer(customerRepository.getReferenceById((long) (Math.random() * NUMBER_START_CUSTOMERS) + 1));
            }
            orderRepository.saveAndFlush(o);
        }

        for (int i = 1; i <= NUMBER_START_ORDER_STRINGS; i++) {
            OrderString os = new OrderString();

            if (i <= NUMBER_START_ORDERS) {
                os.setOrder(orderRepository.getReferenceById((long) i));
            } else {
                os.setOrder(orderRepository.getReferenceById((long) (Math.random() * NUMBER_START_ORDERS + 1)));
            }
            os.setProduct(productRepository.getReferenceById((long) (Math.random() * NUMBER_START_PRODUCTS + 1)));
            os.setOrderStringPrice((double)Math.round(Math.random()*5000+25 * 100d) / 100d);
            orderStringRepository.saveAndFlush(os);
        }
    }
}