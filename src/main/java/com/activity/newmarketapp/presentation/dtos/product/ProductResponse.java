package com.activity.newmarketapp.presentation.dtos.product;

import java.math.BigDecimal;
import java.util.Set;

public record ProductResponse (
        Long id,
        String name,
        String description,
        String photo,
        BigDecimal price,
        Boolean active,
        Set<String> categories
) {
}
