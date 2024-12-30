package com.hd.v1.item.dto;

import com.hd.v1.item.entity.ItemEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Getter
@NoArgsConstructor
public class ItemResponseDto {
    Long id;
    String name;
    Long price;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    public ItemResponseDto(ItemEntity itemEntity) {
        this.id = itemEntity.getId();
        this.name = itemEntity.getName();
        this.price = itemEntity.getPrice();
        this.createdAt = itemEntity.getCreatedAt();
        this.updatedAt = itemEntity.getUpdatedAt();
    }
}
