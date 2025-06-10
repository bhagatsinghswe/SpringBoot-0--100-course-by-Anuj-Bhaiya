package com.bhagat.springboot.week3.springboot.week3;

import com.bhagat.springboot.week3.springboot.week3.entities.ProductEntity;
import com.bhagat.springboot.week3.springboot.week3.repositories.ProductRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.core.annotation.Order;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Rollback(value = false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SpringbootWeek3ApplicationTests {

	@Autowired
	ProductRepository productRepository;


	@Test
	@Order(1)
	void contextLoads() {
	}

	@Test
	@Order(2)
	void testRepository(){
		ProductEntity productEntity= ProductEntity.builder()
				.sku("nestle234")
				.title("Nestle Chocolate")
				.price(BigDecimal.valueOf(23.45))
				.quantity(4)
//				.createdAt(LocalDateTime.now())
				.build();
		ProductEntity savedProductEntity = productRepository.save(productEntity);
		System.out.println(savedProductEntity);
	}

	@Test
	@Order(3)
	void getRepository(){
		List<ProductEntity> entities = productRepository.findAll();
		System.out.println(entities);
	}

	@Test
	@Order(4)
	void getRepositoryByTitle(){
		List<ProductEntity> entities = productRepository.findByOrderByPrice();
		System.out.println(entities);
	}

	@Test
	@Order(5)
	void getRepositoryByQuantity(){
		List<ProductEntity> entities = productRepository.findByQuantity(4);
		System.out.println(entities);
	}

	@Test
	@Order(6)
	void getRepositoryCreatedAfter(){
		List<ProductEntity> entities = productRepository.findByCreatedAtAfter(
				LocalDateTime.of(2025, 1, 1, 0, 0, 0));
		System.out.println(entities);
	}

	@Test
	@Order(7)
	void getRepositoryByQuantityAndPrice(){
		List<ProductEntity> entities = productRepository.findByQuantityAndPrice(4, BigDecimal.valueOf(23.45));
		System.out.println(entities);
	}


//	findByQuantityGreaterThanAndPriceLessThan
//			findByQuantityGreaterThanorPriceLessThan
//  findByTitleIn("%part_of_string%")
//  findByTitleContaining("part_of_string")
//                       ignoreCase("GHGHghgh")





//gpt
	@Test
	@Order(8)
	void testSaveAndQueryCreatedAt() {
		ProductEntity productEntity = ProductEntity.builder()
				.sku("test123")
				.title("Test Product")
				.price(BigDecimal.valueOf(23.45))
				.quantity(4)
//				.createdAt(LocalDateTime.now())
				.build();

		productRepository.save(productEntity);

		List<ProductEntity> results = productRepository.findByCreatedAtAfter(
				LocalDateTime.now().minusMinutes(1)); // should include recent records

		System.out.println("Results: " + results);
	}


	@Test
	@Order(9)
	void customQueryGetSingleFromRepository(){
		Optional<ProductEntity> productEntity = productRepository
				.findByTitleAndPrice("Pepsi", BigDecimal.valueOf(14.4));
		productEntity.ifPresent(System.out::println);
	}




}
