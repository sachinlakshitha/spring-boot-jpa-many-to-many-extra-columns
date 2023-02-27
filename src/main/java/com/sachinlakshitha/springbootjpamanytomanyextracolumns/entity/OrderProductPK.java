package com.sachinlakshitha.springbootjpamanytomanyextracolumns.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductPK implements Serializable {
    private String order_id;
    private String product_id;
}
