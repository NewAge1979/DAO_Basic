package org.example.basic.repository;

import java.util.List;

public interface ProductsRepository {
    List<String> getProductsName(String name);
}