package com.sachinlakshitha.springbootjpamanytomanyextracolumns.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class OrdersProduct implements Serializable {
    private Integer qty;
    private Double total;
    @ManyToOne
    @JoinColumn(name="order_id",referencedColumnName = "id",insertable = false,updatable = false)
    private Orders orders;
    @ManyToOne
    @JoinColumn(name="product_id",referencedColumnName = "id",insertable = false,updatable = false)
    private Product product;
    @EmbeddedId
    private OrderProductPK orderProductPK;
}
