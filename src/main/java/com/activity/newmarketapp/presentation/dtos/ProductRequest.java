package com.activity.newmarketapp.presentation.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRequest (
        @NotBlank(message = "Name should not be blank") String name,
        @NotBlank(message = "Description should not be blank")  String description,
        @NotNull(message = "Price should not be null") BigDecimal price,
        @NotNull Boolean active
){}
