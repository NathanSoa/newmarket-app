package com.activity.newmarketapp.domain.mapper;

import com.activity.newmarketapp.data.entities.Category;
import com.activity.newmarketapp.data.entities.Product;
import com.activity.newmarketapp.presentation.dtos.category.CategoryDTO;

import java.util.Set;
import java.util.stream.Collectors;

public class CategoryDTOMapper {

    public CategoryDTO toDTO(Category category) {
        Set<String> products = category.getProducts().stream().map(Product::getName).collect(Collectors.toSet());
        return new CategoryDTO(category.getName(), products);
    }
}
