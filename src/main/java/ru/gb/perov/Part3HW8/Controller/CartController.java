package ru.gb.perov.Part3HW8.Controller;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.perov.Part3HW8.Data.Product;
import ru.gb.perov.Part3HW8.Service.CartService;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/cart")
@AllArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("")
    @JsonAnyGetter
    public HashMap<String, Integer> findAll() {
//        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
//        String jsonString = gson.toJson(cartService.findAll());
        return cartService.findAll();
    }
}
