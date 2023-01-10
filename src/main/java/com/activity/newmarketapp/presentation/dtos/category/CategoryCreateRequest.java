package com.activity.newmarketapp.presentation.dtos.category;

import jakarta.validation.constraints.NotBlank;

public record CategoryCreateRequest (
        @NotBlank String name
) {
}
