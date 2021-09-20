package com.example.demo.request;

import com.example.demo.model.Address;
import lombok.Data;

import java.util.List;

@Data
public class ReqUser {
    private  Long userid;
    private String username;
    private String name;
    private String Surname;
    private List<Address> addresslist;

}
