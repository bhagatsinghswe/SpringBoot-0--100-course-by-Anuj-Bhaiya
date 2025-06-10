package com.bhagat.springboot.week3.springboot.week3.controllers;


import com.bhagat.springboot.week3.springboot.week3.entities.ProductEntity;
import com.bhagat.springboot.week3.springboot.week3.repositories.ProductRepository;
import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    private final int PAGE_SIZE = 5;

    private final ProductRepository productRepository;


    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

//    @GetMapping
////    public List<ProductEntity> getAllProducts(@RequestParam Optional<String> sortBy){
//    public List<ProductEntity> getAllProducts(@RequestParam(defaultValue = "id") String sortBy){
////        return productRepository.findBy(Sort.by(sortBy));
////        return productRepository.findBy(Sort.by(Sort.Direction.DESC,sortBy));
//
////        // if same sortby then by PRICE , then quantity...
////        return productRepository.findBy(Sort.by(Sort.Direction.DESC,sortBy,"price", "quantity"));
//
//        //
//        return productRepository.findBy(Sort.by(
//                Sort.Order.desc(sortBy),
//                Sort.Order.asc("price"),
//                Sort.Order.desc("title")
//        ));
//    }

    @GetMapping
    public List<ProductEntity> getAllProducts(
            @RequestParam( defaultValue = "") String title,
            @RequestParam( defaultValue = "id")  String sortBy,
            @RequestParam( defaultValue = "0") Integer pageNumber){


        //

//        Pageable pageable =PageRequest.of(
//                pageNumber,
//                PAGE_SIZE,
//                Sort.by(sortBy)
//        );
//
//        // return type page in place of list-> return productRepository.findAll(pageable);
//        return productRepository.findAll(pageable).getContent();
//



        return productRepository.findByTitleContainingIgnoreCase(
                title,
                PageRequest.of(
                        pageNumber,
                        PAGE_SIZE,
                        Sort.by(sortBy)
                )
        );
    }




}
