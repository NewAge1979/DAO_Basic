package org.example.basic.repository;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class ProductsRepositoryImpl implements ProductsRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final String query;

    public ProductsRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.query = read("script1.sql");
    }

    public List<String> getProductsName(String name) {
        return jdbcTemplate.queryForList(query, Map.of("name", name), String.class);
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