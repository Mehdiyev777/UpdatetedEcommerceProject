package com.example.demo.controller;

import com.example.demo.model.Order;
import com.example.demo.model.Status;
import com.example.demo.request.ReqOrder;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @RequestMapping(value="/getOrderList",method = {RequestMethod.GET,RequestMethod.POST})
    public List <Order> getOrderList(){
return  orderService.getOrderList();
    }
    @PostMapping (value="/cancelledorder")
    public void cancelOrder(@RequestBody ReqOrder reqOrder){
        orderService.setStatusCancelOrder(reqOrder);

    }
    @PostMapping  (value="/completedorder")
    public void setCompleteOrder(@RequestBody ReqOrder reqOrder){
        orderService.setcomletedOrderStatus(reqOrder);
    }
    @PostMapping(value="/createorder")
    public void createOrder(@RequestBody ReqOrder reqOrder){
        orderService.createOrder(reqOrder);
    }
@GetMapping(value="/orderbySearchStatus")
    public List<Order> findByStatus( @RequestBody Status status){
        return orderService.findByStatus(status);
}
    @GetMapping(value="/orderbyBetweenDate")
    public List<Order> findByCreateDateBetweenAndFinishedDate(@RequestParam Date startDate,@RequestParam Date endDate ){
        return orderService.findByCreateDateBetweenAndFinishedDate(startDate, endDate);
    }
    @GetMapping(value="/myOrders")
public List<Order>getMyOrderList(@PathVariable("userid") Long userid){
        return orderService.getMyOrders(userid);

}

}
