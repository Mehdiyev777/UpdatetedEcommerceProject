package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product,Long> {
    @Query(value = "   from Product order by  price  DESC ")
 public List<Product>finddesc();
    @Query(value = "   from Product order by  price  ASC ")
    public List<Product>findasc();
    @Query(value = "select p from  Product p where p.name LIKE ?1% ")
    public  List<Product>findbyName(String searchname);





}
