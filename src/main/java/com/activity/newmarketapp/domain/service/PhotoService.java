package com.activity.newmarketapp.domain.service;

import com.activity.newmarketapp.data.entities.Product;

import com.activity.newmarketapp.data.repository.ProductRepository;
import com.activity.newmarketapp.domain.mapper.ProductResponseMapper;
import com.activity.newmarketapp.presentation.dtos.product.ProductResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class PhotoService {

    private final ProductService productService;
    private final ProductResponseMapper responseMapper;
    private final ProductRepository repository;
    @Value("${project.images.directory}")
    private String imageDirectory;

    public PhotoService(ProductService service, ProductResponseMapper responseMapper, ProductRepository repository) {
        this.productService = service;
        this.responseMapper = responseMapper;
        this.repository = repository;
    }

    public String savePhoto(MultipartFile multipartFile, Long id) {
        try{
            String imagePath = imageDirectory + multipartFile.getOriginalFilename();
            multipartFile.transferTo(new File(imagePath));
            ProductResponse product = productService.findById(id);
            Product productWithPhoto = responseMapper.toEntity(product);
            productWithPhoto.setPhoto(imagePath);
            repository.save(productWithPhoto);
            return "Photo successfully updated!";
        } catch (IOException e) {
            return "An error occurred!";
        }
    }
}
