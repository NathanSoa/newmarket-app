package com.activity.newmarketapp.presentation.dtos.entity;

import lombok.Data;

import java.util.List;

@Data
public class DynamicPage<E> {

    private List<E> content;
    private Long size;
    private Long page;
    private String sortField;
}
