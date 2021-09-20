package com.example.demo.service;

import com.example.demo.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProductServiceTest {
    @Autowired
private   ProductService productService;
    @Test
public void searchByName(){
        List <Product>productlist=productService.findByName("nu");
        System.out.println("listye bulundu " +productlist);

}
}