package com.activity.newmarketapp.data.entities;

import jakarta.persistence.*;

import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "product_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories;
}
