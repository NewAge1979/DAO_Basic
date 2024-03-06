package org.example.basic.service;

import lombok.RequiredArgsConstructor;
import org.example.basic.repository.ProductsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductsServiceImpl implements ProductsService {
    private final ProductsRepository repository;
    @Override
    public List<String> getProductsName(String name) {
        return repository.getProductsName(name);
    }
}