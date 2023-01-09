package com.activity.newmarketapp.presentation.controller;

import com.activity.newmarketapp.domain.service.ProductItemService;
import com.activity.newmarketapp.presentation.dtos.productitem.ProductItemRequest;
import com.activity.newmarketapp.presentation.dtos.productitem.ProductItemResponse;

import jakarta.validation.Valid;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/list")
@CrossOrigin
public class ProductItemController {

    private ProductItemService service;

    @GetMapping
    public ResponseEntity<List<ProductItemResponse>> findAll(Principal principal) {
        return ResponseEntity.ok(service.findAll(principal.getName()));
    }

    @PostMapping("/product")
    public ResponseEntity<String> addProduct(Principal principal, @Valid @RequestBody ProductItemRequest productItemRequest) {
        String message = service.addProduct(principal.getName(), productItemRequest);
        return ResponseEntity.ok(message);
    }
}