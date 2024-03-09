package org.example.basic.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductsRepositoryImpl implements ProductsRepository {
    @PersistenceContext
    private final EntityManager entityManager;
    private final String query;

    public ProductsRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.query = read("script1.sql");
    }

    public List<String> getProductsName(String name) {
        return entityManager.createQuery(query).setParameter("name", name).getResultList();
    }

    private static String read(String scriptFileName) {
        try (
                BufferedReader buffer = new BufferedReader(
                        new InputStreamReader(
                                new ClassPathResource(scriptFileName).getInputStream()
                        )
                )
        ) {
            return buffer.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}