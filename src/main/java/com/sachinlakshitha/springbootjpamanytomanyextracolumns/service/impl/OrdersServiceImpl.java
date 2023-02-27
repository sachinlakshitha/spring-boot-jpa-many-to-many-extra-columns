package com.sachinlakshitha.springbootjpamanytomanyextracolumns.service.impl;

import com.sachinlakshitha.springbootjpamanytomanyextracolumns.dto.OrdersDto;
import com.sachinlakshitha.springbootjpamanytomanyextracolumns.entity.OrderProductPK;
import com.sachinlakshitha.springbootjpamanytomanyextracolumns.entity.Orders;
import com.sachinlakshitha.springbootjpamanytomanyextracolumns.repository.OrdersRepository;
import com.sachinlakshitha.springbootjpamanytomanyextracolumns.service.OrdersService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@AllArgsConstructor
public class OrdersServiceImpl implements OrdersService {
    private OrdersRepository ordersRepository;
    private ModelMapper modelMapper;

    @Override
    public Boolean save(OrdersDto ordersDto) {
        Orders orders = convertToEntity(ordersDto);

        orders.getProducts().forEach(product -> {
            OrderProductPK orderProductPK = new OrderProductPK(product.getOrders().getId(), product.getProduct().getId());
            product.setOrderProductPK(orderProductPK);
        });

        return ordersRepository.save(orders) != null;
    }

    @Override
    public List<OrdersDto> findAll() {
        return ordersRepository.findAll().stream().map(this::convertToDto).toList();
    }

    @Override
    public List<OrdersDto> findAllByPage(Pageable page) {
        return ordersRepository.findAll(page).stream().map(this::convertToDto).toList();
    }

    @Override
    public List<OrdersDto> findAllBySort(Sort sort) {
        return ordersRepository.findAll(sort).stream().map(this::convertToDto).toList();
    }

    @Override
    public List<OrdersDto> findAllBySortAndPage(Pageable page) {
        return ordersRepository.findAll(page).stream().map(this::convertToDto).toList();
    }

    @Override
    public OrdersDto findById(String id) {
        return ordersRepository.findById(id).map(this::convertToDto).orElse(null);
    }

    @Override
    public Boolean update(OrdersDto ordersDto) {
        Orders orders = convertToEntity(ordersDto);

        orders.getProducts().forEach(product -> {
            OrderProductPK orderProductPK = new OrderProductPK(product.getOrders().getId(), product.getProduct().getId());
            product.setOrderProductPK(orderProductPK);
        });

        return ordersRepository.save(orders) != null;
    }

    @Override
    public void delete(String id) {
        ordersRepository.deleteById(id);
    }

    public OrdersDto convertToDto(Orders orders) {
        return modelMapper.map(orders, OrdersDto.class);
    }

    public Orders convertToEntity(OrdersDto ordersDto) {
        return modelMapper.map(ordersDto, Orders.class);
    }
}
