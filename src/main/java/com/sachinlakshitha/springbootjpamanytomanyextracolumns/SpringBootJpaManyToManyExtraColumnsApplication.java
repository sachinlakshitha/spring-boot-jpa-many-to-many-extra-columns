package com.sachinlakshitha.springbootjpamanytomanyextracolumns;

import com.sachinlakshitha.springbootjpamanytomanyextracolumns.dto.OrdersDto;
import com.sachinlakshitha.springbootjpamanytomanyextracolumns.dto.OrdersProductDto;
import com.sachinlakshitha.springbootjpamanytomanyextracolumns.dto.ProductDto;
import com.sachinlakshitha.springbootjpamanytomanyextracolumns.entity.OrderProductPK;
import com.sachinlakshitha.springbootjpamanytomanyextracolumns.service.OrdersService;
import com.sachinlakshitha.springbootjpamanytomanyextracolumns.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.UUID;

@SpringBootApplication
@AllArgsConstructor
@Slf4j
public class SpringBootJpaManyToManyExtraColumnsApplication implements CommandLineRunner {
	private ProductService productService;
	private OrdersService ordersService;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaManyToManyExtraColumnsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ProductDto product1 = new ProductDto();
		product1.setId(UUID.randomUUID().toString());
		product1.setName("Product 1");
		product1.setUnitPrice(100.00);

		Boolean isProduct1Saved = productService.save(product1);

		if (isProduct1Saved) {
			log.info("Product [{}] saved successfully", product1.getName());
		} else {
			log.info("Product [{}] saving failed", product1.getName());
		}

		ProductDto product2 = new ProductDto();
		product2.setId(UUID.randomUUID().toString());
		product2.setName("Product 2");
		product2.setUnitPrice(200.00);

		Boolean isProduct2Saved = productService.save(product2);

		if (isProduct2Saved) {
			log.info("Product [{}] saved successfully", product2.getName());
		} else {
			log.info("Product [{}] saving failed", product2.getName());
		}

		OrdersDto ordersDto = new OrdersDto();
		ordersDto.setId(UUID.randomUUID().toString());
		ordersDto.setNumber("ORD-001");
		ordersDto.setAmount(300.00);
		ordersDto.setCreatedTime(new Date());

		OrdersProductDto ordersProductDto1 = new OrdersProductDto();
		ordersProductDto1.setOrders(ordersDto);
		ordersProductDto1.setProduct(product1);
		ordersProductDto1.setQty(1);
		ordersProductDto1.setTotal(product1.getUnitPrice() * ordersProductDto1.getQty());

		ordersDto.getProducts().add(ordersProductDto1);

		OrdersProductDto ordersProductDto2 = new OrdersProductDto();
		ordersProductDto2.setOrders(ordersDto);
		ordersProductDto2.setProduct(product2);
		ordersProductDto2.setQty(1);
		ordersProductDto2.setTotal(product2.getUnitPrice() * ordersProductDto2.getQty());

		ordersDto.getProducts().add(ordersProductDto2);

		Boolean isOrderSaved = ordersService.save(ordersDto);

		if (isOrderSaved) {
			log.info("Order [{}] saved successfully", ordersDto.getNumber());
		} else {
			log.info("Order [{}] saving failed", ordersDto.getNumber());
		}

		log.info("[FIND_BY_ID] {}", ordersService.findById(ordersDto.getId()));

		log.info("[FIND_ALL] {}", ordersService.findAll());

		// Pagination example with page size 10
		log.info("[FIND_ALL_BY_PAGINATION] {}", ordersService.findAllByPage(PageRequest.of(0,10)));

		// Sort by order number in ascending order
		log.info("[FIND_ALL_BY_SORT] {}", ordersService.findAllBySort(Sort.by(Sort.Direction.fromString("ASC"), "number")));

		// Sort by order number in descending order and pagination with page size 10
		log.info("[FIND_ALL_BY_SORT_AND_PAGINATION] {}", ordersService.findAllBySortAndPage(PageRequest.of(0,10, Sort.by(Sort.Direction.fromString("DESC"), "number"))));

		log.info("[UPDATE]");
		ordersDto.setAmount(200.00);
		ordersDto.getProducts().remove(ordersProductDto1);
		Boolean isOrderUpdated = ordersService.update(ordersDto);

		if (isOrderUpdated) {
			log.info("Order [{}] updated successfully", ordersDto.getNumber());
		} else {
			log.info("Order [{}] updating failed", ordersDto.getNumber());
		}

		log.info("[FIND_BY_ID] {}", ordersService.findById(ordersDto.getId()));

		log.info("[DELETE]");
		ordersService.delete(ordersDto.getId());
		log.info("Order [{}] deleted successfully", ordersDto.getNumber());

		log.info("[FIND_ALL] {}", ordersService.findAll());
	}
}
