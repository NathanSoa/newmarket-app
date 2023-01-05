package com.activity.newmarketapp.data.entities;

import jakarta.persistence.Entity;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product extends BaseEntity {

    private String name;
    private String description;
    private String photo;
    private BigDecimal price;
    private Boolean active;
}
