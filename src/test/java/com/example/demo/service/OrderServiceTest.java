package com.example.demo.service;

import com.example.demo.model.Order;
import com.example.demo.request.ReqOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OrderServiceTest {
    @Autowired
    private OrderService orderService;
    @Test
    public void setCompleteOrder(){
       // ReqOrder reqOrder=new ReqOrder();
        //reqOrder.setOrderId( 45l);

        //orderService.setcomletedOrderStatus(reqOrder);
        List<Order> orderList = orderService.getOrderList();
        System.out.println(orderList);
    }

    }

