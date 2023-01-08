package com.activity.newmarketapp.data.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "app_user")
public class User extends BaseEntity {

    private String username;
    private String password;
}
