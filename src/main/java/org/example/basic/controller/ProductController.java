package org.example.basic.controller;

import lombok.RequiredArgsConstructor;
import org.example.basic.service.ProductsServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductsServiceImpl service;

    @GetMapping(value = "/fetch-product", produces = "application/json")
    public ResponseEntity<List<String>> getProductByName(@RequestParam String name) {
        return new ResponseEntity<>(service.getProductsName(name), HttpStatus.OK);
    }
}