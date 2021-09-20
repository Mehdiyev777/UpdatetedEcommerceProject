package com.example.demo.service;

import com.example.demo.model.Order;
import com.example.demo.model.Status;
import com.example.demo.repository.OrderRepository;
import com.example.demo.request.ReqOrder;
import com.example.demo.request.ReqUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.filter.OrderedRequestContextFilter;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public void createOrder(ReqOrder reqOrder){
        Order order = new Order();
        order.setUser(reqOrder.getUser());
        order.setProducts(reqOrder.getProducts());

        order.setBillingAdress(reqOrder.getUser().getAddresses().get(reqOrder.getBillingAddressId()));
        orderRepository.save(order);
    }

    public void setStatusCancelOrder(ReqOrder reqOrder){
        Long id  = reqOrder.getOrderId();
        Order order = orderRepository.findById(reqOrder.getOrderId()).get();
        order.setStatus(Status.Cancelled);
        orderRepository.save(order);

    }
    public void setcomletedOrderStatus(ReqOrder reqOrder){
        Long id = reqOrder.getOrderId();
        Order order = orderRepository.findById(id).get();

        order.setStatus(Status.Completed);
        order.setAmount(order.getTotalAmount());
        order.getUser().setBalance(order.getUser().getBalance()-order.getAmount());
        order.setFinishedDate(new Date());
        orderRepository.save(order);


    }
    public List <Order> getOrderList(){
        List <Order> orderList = new ArrayList<>();
        orderRepository.findAll().forEach(order -> orderList.add(order));
return  orderList;
            }



    public List<Order> getMyOrders(Long id ) {
   return orderRepository.findOrdersByUserId(id);


    }
    public List<Order>findByCreateDateBetweenAndFinishedDate(Date satartDate,Date endDate){
        return orderRepository.findByCreateDateBetweenAndFinishedDate(satartDate, endDate);
    }
    public List <Order>findByStatus(Status status){
       return orderRepository.findByStatus(status);
    }
}






