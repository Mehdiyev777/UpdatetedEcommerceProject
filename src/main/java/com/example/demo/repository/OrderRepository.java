package com.example.demo.repository;

import com.example.demo.model.Order;
import com.example.demo.model.Status;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order,Long> {

  List <Order> findByStatus(Status status);
List<Order>getAllByUserId(Long userid);
  @Query(value = "from Order t where t.finishedDate BETWEEN :startDate AND :endDate")
List<Order>findByCreateDateBetweenAndFinishedDate( @Param("startDate")Date startDate,@Param("endDate")Date endDaTE);

 @Query(value="select o from Order o where o.user.id =?1")
 List<Order> findOrdersByUserId(@Param("id")Long id);



}
