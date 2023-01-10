package com.activity.newmarketapp.domain.service;

import com.activity.newmarketapp.data.entities.Category;
import com.activity.newmarketapp.data.repository.CategoryRepository;
import com.activity.newmarketapp.domain.mapper.CategoryDTOMapper;
import com.activity.newmarketapp.presentation.dtos.category.CategoryCreateRequest;
import com.activity.newmarketapp.config.exception.customexceptions.EntityAlreadyExistsException;
import com.activity.newmarketapp.presentation.dtos.category.CategoryDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryDTOMapper categoryDTOMapper;

    public Set<CategoryDTO> findAll() {
        return categoryRepository.findAll().stream().map(categoryDTOMapper::toDTO).collect(Collectors.toSet());
    }

    public String save(CategoryCreateRequest categoryCreateRequest) {

        if (categoryAlreadyExists(categoryCreateRequest.name()))
            throw new EntityAlreadyExistsException("category " + categoryCreateRequest.name() + " already exists!");

        Category category = new Category();
        category.setName(categoryCreateRequest.name());
        categoryRepository.save(category);
        return "Successfully created category " + categoryCreateRequest.name();
    }

    private Boolean categoryAlreadyExists(String categoryName) {
        return categoryRepository.existsByName(categoryName);
    }
}
