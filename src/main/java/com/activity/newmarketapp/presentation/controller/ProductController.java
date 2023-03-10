package com.activity.newmarketapp.presentation.controller;

import com.activity.newmarketapp.data.repository.filter.ProductFilter;
import com.activity.newmarketapp.domain.service.ProductService;
import com.activity.newmarketapp.presentation.dtos.product.ProductRequest;
import com.activity.newmarketapp.presentation.dtos.product.ProductResponse;

import jakarta.validation.Valid;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {

    private final ProductService service;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ProductResponse>> findAllFiltered(ProductFilter filter) {
        return ResponseEntity.ok(service.findAllFiltered(filter));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProductResponse> save(@RequestBody @Valid ProductRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<ProductResponse> toggleActive(@PathVariable Long id) {
        return ResponseEntity.ok(service.toggleActive(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> fullyUpdate(@PathVariable Long id, @RequestBody @Valid ProductRequest request) {
        return ResponseEntity.ok(service.fullyUpdate(request, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }

    @PostMapping("/{productId}/category/{categoryId}")
    public ResponseEntity<ProductResponse> saveProductInCategory(@PathVariable Long productId, @PathVariable Long categoryId) {
        ProductResponse productResponse = service.saveCategoryInProduct(productId, categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(productResponse);
    }
}
