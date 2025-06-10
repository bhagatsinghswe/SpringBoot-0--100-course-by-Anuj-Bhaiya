package com.bhagat.springboot.week3.springboot.week3.repositories;

import com.bhagat.springboot.week3.springboot.week3.entities.ProductEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository< ProductEntity, Long> {


    List<ProductEntity> findBy(Sort sort);
//    List<ProductEntity> findByTitle(String title);
    //renamed for order by
    //List<ProductEntity> findByOrderByPrice();

    List<ProductEntity> findByCreatedAtAfter(LocalDateTime createdAt);

    List<ProductEntity> findByQuantityAndPrice(Integer quantity, BigDecimal price);

    List<ProductEntity> findByQuantity(Integer Quantity);


//    Optional<ProductEntity> findByTitleAndPrice(String title, BigDecimal price);


    @Query("select  e from ProductEntity e where e.title=?1 and e.price=?2")
//    @Query("select  e from ProductEntity e where e.title:title and e.price:price")
    Optional<ProductEntity> findByTitleAndPrice(String title, BigDecimal price);


//    List<ProductEntity> findByOderByPrice();



    List<ProductEntity> findByTitleContainingIgnoreCase(String title, Pageable pageable);


    List<ProductEntity> findByOrderByPrice();

    List<ProductEntity> findByTitleContainingIgnoreCase(String title, PageRequest of);
}
