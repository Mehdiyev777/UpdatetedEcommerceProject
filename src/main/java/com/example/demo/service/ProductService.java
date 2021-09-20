package com.example.demo.service;

import com.example.demo.model.Order;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public List<Product> getAllProduct(){
        List<Product> products= new ArrayList<>();
        productRepository.findAll().forEach(product -> products.add(product));
        return products;
    }
public List<Product> findByName(String searchname){
     List <Product> productList  = productRepository.findbyName(searchname);
        return productList;
}
public void updateOrSave(Product product){
        productRepository.save(product);
}
public void deleteProduct(Long productId){
        productRepository.deleteById(productId);
}
public List <Product> findProductByName(String searchname){
   return productRepository.findbyName(searchname);
}
}