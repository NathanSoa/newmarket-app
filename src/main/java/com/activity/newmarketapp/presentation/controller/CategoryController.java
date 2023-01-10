package com.activity.newmarketapp.presentation.controller;

import com.activity.newmarketapp.domain.service.CategoryService;
import com.activity.newmarketapp.presentation.dtos.category.CategoryCreateRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
@CrossOrigin
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<String> save(@Valid @RequestBody CategoryCreateRequest categoryCreateRequest) {
        String message = categoryService.save(categoryCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }
}
