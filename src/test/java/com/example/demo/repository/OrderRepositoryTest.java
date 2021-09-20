package com.example.demo.repository;

import ch.qos.logback.core.util.CachingDateFormatter;
import com.example.demo.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;
    @Test
 public  void saveOrder(){
       // Address a1 =new Address();
       // a1.setCity("Baku");
       // a1.setStreet("Refi Refiyev");
       // a1.setHome("ev no 37");
       // Address a2 = new Address();
       // a2.setCity("Baku");
       // a2.setStreet("AZADLIQ");
       // a2.setHome("ev 35");
       // User user1 = new User();
       // user1.setName("Ali");
        //user1.setSurname("Coban");
        //user1.setPassword("asder52");
        //user1.setUsername("Coban");
        //user1.setAddresses(List.of(a1,a2));
       // user1.setBalance(4082);
        //Product p = new Product();
        //p.setName("nutella");
        //p.setPrice(11);
        //Product p2 = new Product();
        //p2.setName("Toy Box");
        //p2.setPrice(7);
       // Product p3 = new Product();
       // p3.setName("cipsi");
        //p3.setPrice(5);

       // Order order2 = new Order();
       // order2.setUser(user1);
      //  order2.setProducts(List.of(p,p2,p3));
       // order2.setBillingAdress(user1.getAddresses().get(0));

   //order2.setAmount(order2.getTotalAmount());
       // order2.getUser().setBalance(user1.getBalance()- order2.getAmount());
      //  orderRepository.save(order2);

     //  orderRepository.save(order2);
       // Calendar takvim = new GregorianCalendar(2020, Calendar.SEPTEMBER, 13);
      //  Date tarih = takvim.getTime();
      //  Calendar takvim2 = new GregorianCalendar(2021, Calendar.SEPTEMBER, 15);
      //  Date tarih2 = takvim2.getTime();
     //  List<Order > order1=orderRepository.findOrdersByUserId(5L);
         //  System.out.println("liste" +order1);





 }
}