package com.sachinlakshitha.springbootjpamanytomanyextracolumns.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrdersProductDto {
    @ToString.Exclude
    private OrdersDto orders;
    @ToString.Exclude
    private ProductDto product;
    private Integer qty;
    private Double total;
}
