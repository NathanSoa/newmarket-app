package com.activity.newmarketapp.presentation.dtos.login;

import jakarta.validation.constraints.NotBlank;

public record LoginResponse(@NotBlank String username,
                            @NotBlank String token) {
}
