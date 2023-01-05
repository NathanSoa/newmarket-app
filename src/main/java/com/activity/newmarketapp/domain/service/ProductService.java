package com.activity.newmarketapp.domain.service;

import com.activity.newmarketapp.data.entities.Product;
import com.activity.newmarketapp.data.repository.ProductRepository;
import com.activity.newmarketapp.domain.mapper.ProductRequestMapper;
import com.activity.newmarketapp.domain.mapper.ProductResponseMapper;
import com.activity.newmarketapp.presentation.dtos.ProductRequest;
import com.activity.newmarketapp.presentation.dtos.ProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductService {

    private final ProductRepository repository;
    private final ProductRequestMapper requestMapper;
    private final ProductResponseMapper responseMapper;

    public List<ProductResponse> findAll() {
        return repository
                .findAll()
                .stream()
                .map(responseMapper::toDTO)
                .toList();
    }

    public ProductResponse save(ProductRequest productRequest) {
        Product savedProduct = repository.save(requestMapper.toEntity(productRequest));
        return responseMapper.toDTO(savedProduct);
    }
}
