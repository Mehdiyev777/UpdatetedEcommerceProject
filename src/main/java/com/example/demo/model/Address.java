package com.example.demo.model;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Data
@Entity
@Table(name="addresses")
@DynamicInsert

public class Address {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String street;
    private String home;
    @ColumnDefault(value="1")
    private Integer active;
}
