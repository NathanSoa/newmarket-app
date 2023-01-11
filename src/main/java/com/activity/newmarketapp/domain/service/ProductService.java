package com.activity.newmarketapp.domain.service;

import com.activity.newmarketapp.data.entities.BaseEntity;
import com.activity.newmarketapp.data.entities.Category;
import com.activity.newmarketapp.data.entities.Product;
import com.activity.newmarketapp.data.repository.CategoryRepository;
import com.activity.newmarketapp.data.repository.ProductRepository;
import com.activity.newmarketapp.data.repository.filter.ProductFilter;
import com.activity.newmarketapp.domain.mapper.ProductRequestMapper;
import com.activity.newmarketapp.domain.mapper.ProductResponseMapper;
import com.activity.newmarketapp.presentation.dtos.entity.DynamicPage;
import com.activity.newmarketapp.presentation.dtos.product.ProductRequest;
import com.activity.newmarketapp.presentation.dtos.product.ProductResponse;

import lombok.AllArgsConstructor;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRequestMapper requestMapper;
    private final ProductResponseMapper responseMapper;

    public DynamicPage<ProductResponse> findAll(Pageable pageable) {
        Page<Product> page = productRepository.findAll(pageable);
        DynamicPage<ProductResponse> dynamicPage = new DynamicPage<>();
        dynamicPage.setPage((long) page.getNumber());
        dynamicPage.setSize((long) page.getSize());
        dynamicPage.setContent(page.getContent().stream().map(responseMapper::toDTO).toList());
        dynamicPage.setSortField(page.getSort().toString());
        return dynamicPage;
    }

    public List<ProductResponse> findAllFiltered(ProductFilter filter) {
        return productRepository
                .findAllFiltered(filter)
                .stream()
                .map(responseMapper::toDTO)
                .toList();
    }

    public ProductResponse findById(Long id) {
        return responseMapper.toDTO(getEntityOrThrowException(id, productRepository, "product"));
    }

    public ProductResponse save(ProductRequest productRequest) {
        Product savedProduct = productRepository.save(requestMapper.toEntity(productRequest));
        return responseMapper.toDTO(savedProduct);
    }

    public ProductResponse toggleActive(Long id) {
        Product databaseProduct = getEntityOrThrowException(id, productRepository, "product");
        databaseProduct.setActive(!databaseProduct.getActive());
        productRepository.save(databaseProduct);
        return responseMapper.toDTO(databaseProduct);
    }

    public ProductResponse fullyUpdate(ProductRequest request, Long id) {
        Product product = getEntityOrThrowException(id, productRepository, "product");
        product = requestMapper.toEntity(request);
        product.setId(id);
        return responseMapper.toDTO(productRepository.save(product));
    }

    public String deleteById(Long id) {
        getEntityOrThrowException(id, productRepository, "product");
        productRepository.deleteById(id);
        return "Product successfully deleted!";
    }

    public ProductResponse saveCategoryInProduct(Long productId, Long categoryId) {
        Product product = getEntityOrThrowException(productId, productRepository, "product");
        Category category = getEntityOrThrowException(categoryId, categoryRepository, "category");

        Product productWithCategory = new Product();
        BeanUtils.copyProperties(product, productWithCategory);

        if(Objects.isNull(productWithCategory.getCategories()))
            productWithCategory.setCategories(new HashSet<>());

        productWithCategory.getCategories().add(category);

        return responseMapper.toDTO(productRepository.save(productWithCategory));
    }

    private<E extends BaseEntity> E getEntityOrThrowException(Long id, JpaRepository<E, Long> repository, String entityName) {
        Optional<E> entityOptional = repository.findById(id);
        return entityOptional.orElseThrow(() -> new EmptyResultDataAccessException("Cannot find any " + entityName + " with id " + id, 1));
    }
}
