package com.activity.newmarketapp.domain.service;

import com.activity.newmarketapp.data.entities.Product;
import com.activity.newmarketapp.data.entities.ProductItem;
import com.activity.newmarketapp.data.entities.User;
import com.activity.newmarketapp.data.repository.ProductItemRepository;
import com.activity.newmarketapp.data.repository.ProductRepository;
import com.activity.newmarketapp.data.repository.UserRepository;
import com.activity.newmarketapp.domain.mapper.ProductItemResponseMapper;
import com.activity.newmarketapp.presentation.dtos.productitem.ProductItemRequest;
import com.activity.newmarketapp.presentation.dtos.productitem.ProductItemResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductItemService {

    private final UserRepository userRepository;
    private final ProductItemRepository productItemRepository;
    private final ProductRepository productRepository;
    private final ProductItemResponseMapper productItemResponseMapper;

    public List<ProductItemResponse> findAll(String name) {
        User user = userRepository.findByUsername(name).get();

        List<ProductItem> productItems = productItemRepository.findAllByUser(user);

        return productItems.stream()
                .map(productItemResponseMapper::toDTO)
                .toList();
    }

    public String addProduct(String name, ProductItemRequest productItemRequest) {
        User user = userRepository.findByUsername(name).get();
        Optional<Product> optionalProduct = productRepository.findByName(productItemRequest.name());

        if(optionalProduct.isEmpty())
            throw new EmptyResultDataAccessException("There's no available product with name " + productItemRequest.name(), 1);

        if(itemAlreadyInList(user, optionalProduct.get())) {
            ProductItem databaseProduct = productItemRepository.findByProductAndUser(optionalProduct.get(), user).get();
            updateQuantity(databaseProduct, databaseProduct.getQuantity() + 1);
            return "Added one more unit of " + databaseProduct.getProduct().getName() + " to your list";
        }

        ProductItem productItem = new ProductItem();
        productItem.setProduct(optionalProduct.get());
        productItem.setUser(user);
        productItem.setQuantity(productItemRequest.quantity());
        productItemRepository.save(productItem);

        return "Product " + productItem.getProduct().getName() + " was added to your list with " + productItem.getQuantity() + " units";
    }

    public ProductItemResponse changeQuantity(String name, ProductItemRequest productItemRequest) {
        User user = userRepository.findByUsername(name).get();
        Optional<Product> optionalProduct = productRepository.findByName(productItemRequest.name());

        if(optionalProduct.isEmpty())
            throw new EmptyResultDataAccessException("There's no available product with name " + productItemRequest.name(), 1);

        Optional<ProductItem> optionalProductItem = productItemRepository.findByProductAndUser(optionalProduct.get(), user);

        if(optionalProductItem.isEmpty())
            throw new EmptyResultDataAccessException("There's no product with name " + productItemRequest.name() + " in your list, you should add it first.", 1);

        ProductItem databaseProductItem = optionalProductItem.get();
        updateQuantity(databaseProductItem, productItemRequest.quantity());
        return productItemResponseMapper.toDTO(databaseProductItem);
    }

    private boolean itemAlreadyInList(User user, Product product) {
        return productItemRepository.findByProductAndUser(product, user).isPresent();
    }

    private void updateQuantity(ProductItem databaseProductItem, Integer newQuantity) {
        ProductItem updatedProductItem = new ProductItem();
        BeanUtils.copyProperties(databaseProductItem, updatedProductItem);
        updatedProductItem.setQuantity(newQuantity);
        productItemRepository.save(updatedProductItem);
    }

}
