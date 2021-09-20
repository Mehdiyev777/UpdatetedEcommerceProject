package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.tools.StandardLocation;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@DynamicInsert
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")

    private User user;
    @OneToMany(cascade = CascadeType.ALL)
    private List <Product> products;
    @OneToOne
    private Address billingAdress;
    @Column(length = 32, columnDefinition = "varchar(32) default 'In_Process'")
@Enumerated(EnumType.ORDINAL)

    private Status status=Status.In_Process;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "finished_date")
    private Date finishedDate;

    @ColumnDefault(value = "1")
    private Integer active;
    @Column(nullable = true)

   private double amount;
    @Transient
public double getTotalAmount(){
    double sum =0;
    for (int i=0;i<this.getProducts().size();i++){
        sum=sum+this.getProducts().get(i).getPrice();

    }
    return sum;
    }



}
