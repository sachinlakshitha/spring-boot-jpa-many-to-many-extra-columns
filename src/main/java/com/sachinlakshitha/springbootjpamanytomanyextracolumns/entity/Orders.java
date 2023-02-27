package com.sachinlakshitha.springbootjpamanytomanyextracolumns.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Orders {
    @Id
    private String id;
    private String number;
    private Double amount;
    private Date createdTime;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orders", orphanRemoval = true)
    private List<OrdersProduct> products;
}
