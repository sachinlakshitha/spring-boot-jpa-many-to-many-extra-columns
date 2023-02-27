package com.sachinlakshitha.springbootjpamanytomanyextracolumns.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class OrdersDto {
    private String id;
    private String number;
    private Double amount;
    private Date createdTime;
    private List<OrdersProductDto> products = new ArrayList<>();
}
