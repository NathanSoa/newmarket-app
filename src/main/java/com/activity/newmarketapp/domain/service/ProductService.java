package com.activity.newmarketapp.domain.service;

import com.activity.newmarketapp.data.entities.Product;
import com.activity.newmarketapp.data.repository.ProductRepository;
import com.activity.newmarketapp.data.repository.filter.ProductFilter;
import com.activity.newmarketapp.domain.mapper.ProductRequestMapper;
import com.activity.newmarketapp.domain.mapper.ProductResponseMapper;
import com.activity.newmarketapp.presentation.dtos.product.ProductRequest;
import com.activity.newmarketapp.presentation.dtos.product.ProductResponse;

import lombok.AllArgsConstructor;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<ProductResponse> findAllFiltered(ProductFilter filter) {
        return repository
                .findAllFiltered(filter)
                .stream()
                .map(responseMapper::toDTO)
                .toList();
    }

    public ProductResponse findById(Long id) {
        return responseMapper.toDTO(getProductOrThrowException(id));
    }

    public ProductResponse save(ProductRequest productRequest) {
        Product savedProduct = repository.save(requestMapper.toEntity(productRequest));
        return responseMapper.toDTO(savedProduct);
    }

    public ProductResponse toggleActive(Long id) {
        Product databaseProduct = getProductOrThrowException(id);
        databaseProduct.setActive(!databaseProduct.getActive());
        repository.save(databaseProduct);
        return responseMapper.toDTO(databaseProduct);
    }

    public ProductResponse fullyUpdate(ProductRequest request, Long id) {
        Product product = getProductOrThrowException(id);
        product = requestMapper.toEntity(request);
        product.setId(id);
        return responseMapper.toDTO(repository.save(product));
    }

    public String deleteById(Long id) {
        getProductOrThrowException(id);
        repository.deleteById(id);
        return "Product successfully deleted!";
    }

    private Product getProductOrThrowException(Long id) {
        Optional<Product> productOptional = repository.findById(id);
        return productOptional.orElseThrow(() ->  new EmptyResultDataAccessException("Cannot find any product with id "  + id, 1));
    }
}
