package com.activity.newmarketapp.data.repository;
import com.activity.newmarketapp.data.entities.Product;
import com.activity.newmarketapp.data.entities.ProductItem;
import com.activity.newmarketapp.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductItemRepository extends JpaRepository<ProductItem, Long> {

    List<ProductItem> findAllByUser(User user);
    Optional<ProductItem> findByProductAndUser(Product product, User user);
}
