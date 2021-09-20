package com.example.demo.request;

import com.example.demo.model.Product;
import com.example.demo.model.Status;
import com.example.demo.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReqOrder {
    private Long orderId;
    private User user;
    private List<Product> products;
    private int billingAddressId;
    private Status status;



}
