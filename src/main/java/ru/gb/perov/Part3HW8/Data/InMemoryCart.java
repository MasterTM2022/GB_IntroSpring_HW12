package ru.gb.perov.Part3HW8.Data;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.annotation.PostConstruct;
import lombok.*;
import org.springframework.stereotype.Component;
import ru.gb.perov.Part3HW8.Data.Mappers.ProductMapperToJSON;
import ru.gb.perov.Part3HW8.Repository.ProductRepository;

import java.util.*;


@Component
@Data
@RequiredArgsConstructor
public class InMemoryCart {
    private HashMap<Long, Integer> cart;
    private final ProductRepository productRepository;

    @PostConstruct
    public void init() {
        cart = new HashMap<>();
    }

    public HashMap<String, Integer> findAll() {
        for (HashMap.Entry<Long, Integer> pair : cart.entrySet()) {
            Long key = pair.getKey();
            Integer value = pair.getValue();
            System.out.println(key + ":" + value);
        }

        HashMap<String, Integer> cartProducts = new HashMap<>();
        cart.forEach((key, value) -> {
            try {
                cartProducts.put(productRepository.findById(key).get().productToJsonString(), value);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

        });
        return cartProducts;

    }

    public void addProductToCart(Long id) {
        if (cart.containsKey(id)) {
            System.out.println(productRepository.findById(id).get().toString());
            System.out.println(cart.get(id) + 1);
            cart.put(id, cart.get(id) + 1);
        } else {
            cart.put(id, 1);
        }
    }

    public void deleteProductFromCart(Long ID) {

    }


    public void decreaseProductQuantity(Long ID) {

    }

    public void increaseProductQuantity(Long ID) {

    }

    public void setProductQuantity(Long ID, Integer quantity) {

    }

}
