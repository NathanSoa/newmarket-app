package com.activity.newmarketapp.presentation.dtos.category;

import java.util.Set;

public record CategoryDTO(String name,
                          Set<String> products) {
}
