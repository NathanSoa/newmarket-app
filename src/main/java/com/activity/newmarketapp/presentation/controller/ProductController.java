package com.activity.newmarketapp.presentation.controller;

import com.activity.newmarketapp.domain.service.ProductService;
import com.activity.newmarketapp.presentation.dtos.ProductRequest;
import com.activity.newmarketapp.presentation.dtos.ProductResponse;
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

    @PostMapping
    public ResponseEntity<ProductResponse> save(@RequestBody @Valid ProductRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
    }
}
