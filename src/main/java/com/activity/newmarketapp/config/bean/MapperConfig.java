package com.activity.newmarketapp.config.bean;

import com.activity.newmarketapp.domain.mapper.ProductRequestMapper;
import com.activity.newmarketapp.domain.mapper.ProductResponseMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MapperConfig {

    @Bean
    public ProductRequestMapper productRequestMapper() {
        return new ProductRequestMapper();
    }

    @Bean
    public ProductResponseMapper productResponseMapper() {
        return new ProductResponseMapper();
    }
}
