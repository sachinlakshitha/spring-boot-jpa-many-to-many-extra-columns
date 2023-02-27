package com.sachinlakshitha.springbootjpamanytomanyextracolumns.repository;

import com.sachinlakshitha.springbootjpamanytomanyextracolumns.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, String> {
}
