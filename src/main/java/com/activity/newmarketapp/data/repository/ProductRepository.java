package com.activity.newmarketapp.data.repository;

import com.activity.newmarketapp.data.entities.Product;
import com.activity.newmarketapp.data.repository.product.ProductRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryQuery {

    Optional<Product> findByName(String name);
}
