package com.activity.newmarketapp.data.repository.product;

import com.activity.newmarketapp.data.entities.Product;
import com.activity.newmarketapp.data.repository.filter.ProductFilter;

import java.util.Set;

public interface ProductRepositoryQuery {

    Set<Product> findAllFiltered(ProductFilter productFilter);
}
