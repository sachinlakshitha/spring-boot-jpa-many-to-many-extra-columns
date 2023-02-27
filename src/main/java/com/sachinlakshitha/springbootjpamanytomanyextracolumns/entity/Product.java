package com.sachinlakshitha.springbootjpamanytomanyextracolumns.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Product {
    @Id
    private String id;
    private String name;
    private Double unitPrice;
}
