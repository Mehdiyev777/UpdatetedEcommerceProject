package com.example.demo.repository;

import com.example.demo.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProductRepositoryTest {
    @Autowired
    private  ProductRepository productRepository;
    @Test
    public void saveProduct(){
        //Product product1 = new Product();
        //product1.setName("Azure");
       // product1.setPrice(1882);
        //productRepository.save(product1);
//List<Product> productlist =productRepository.findbyName("nu");
        //System.out.println("artan sira" +productlist);
           }


}