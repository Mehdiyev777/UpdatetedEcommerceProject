package com.example.demo.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;


import java.util.*;

@Data
@Table(name="users")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicInsert

public class User {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private  Long id;
 private String username;
 private String password;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;
 private String name;
 private String surname;
 private double balance;
 @OneToMany(cascade = CascadeType.ALL)
 private List<Address>addresses;
    @ColumnDefault(value="1")
    private Integer active;
@ManyToMany
  private Collection <Role> roles = new ArrayList<>();
    }

