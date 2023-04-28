package ru.gb.perov.Part3HW8.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.perov.Part3HW8.Data.InMemoryCart;
import ru.gb.perov.Part3HW8.Data.Product;

import java.util.HashMap;

@Service()
@RequiredArgsConstructor
public class CartService {
    @Autowired
    private final InMemoryCart cart;


    public HashMap<String, Integer> findAll() {
        return cart.findAll();
    }

    public void addProductToCart(Long id) {
        cart.addProductToCart(id);
    }
}
