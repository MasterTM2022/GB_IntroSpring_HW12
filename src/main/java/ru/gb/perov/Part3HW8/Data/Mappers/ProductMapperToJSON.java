package ru.gb.perov.Part3HW8.Data.Mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.gb.perov.Part3HW8.Data.Product;

public class ProductMapperToJSON {
        static ObjectMapper objectMapper = new ObjectMapper();

//                @Test
        public String productToJsonString(Product product) throws JsonProcessingException {
            return objectMapper.writeValueAsString(product);
        }
    }