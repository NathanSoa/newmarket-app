package com.activity.newmarketapp.presentation.dtos.productitem;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductItemRequest(@NotBlank String name,
                                 @NotNull Integer quantity) {
}
