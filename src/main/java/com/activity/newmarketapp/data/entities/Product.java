package com.activity.newmarketapp.data.entities;

import jakarta.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends BaseEntity {

    private String name;
    private String description;
    private Byte[] photo;
    private BigDecimal price;
}
