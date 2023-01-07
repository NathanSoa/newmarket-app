package com.activity.newmarketapp.data.repository.filter;

import lombok.Data;

@Data
public class ProductFilter {

    private String name;
    private String description;
    private Boolean active;
}
