package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired
    ProductService productService;
    @GetMapping(value = "/getAllProducts")
    public List<Product> getAllProduct(){
        return productService.getAllProduct();
    }
    @GetMapping(value = "/searchName/{productName}")
    public List<Product> findByName(@PathVariable String productName){
       return productService.findByName(productName);
    }
    @PostMapping(value = "/updateOrSave")
    public void update(@RequestBody Product product){
        productService.updateOrSave(product);
    }
    public void deleteProduct(@RequestParam("productId") Long productId){
        productService.deleteProduct(productId);
    }
}
