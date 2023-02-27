package com.sachinlakshitha.springbootjpamanytomanyextracolumns.repository;

import com.sachinlakshitha.springbootjpamanytomanyextracolumns.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
