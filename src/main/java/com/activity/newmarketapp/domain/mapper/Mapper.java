package com.activity.newmarketapp.domain.mapper;

import com.activity.newmarketapp.data.entities.BaseEntity;

public interface Mapper<E extends BaseEntity, DTO> {

    E toEntity(DTO dto);
    DTO toDTO(E entity);
}
