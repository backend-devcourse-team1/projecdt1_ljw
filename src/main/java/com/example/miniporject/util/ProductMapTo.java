package com.example.miniporject.util;

import com.example.miniporject.admin.model.dto.Coffee;
import com.example.miniporject.admin.model.entitiy.ProductEntity;

import java.time.LocalDateTime;

public class ProductMapTo {

    private static LocalDateTime timeCheck() {
        return LocalDateTime.now();
    }

    public static ProductEntity mapToProductEntity(Coffee coffee) {
        return ProductEntity.builder()
                .productName(coffee.getProductName())
                .description(coffee.getDescription())
                .price(coffee.getPrice())
                .category(coffee.getCategory())
                .updateAt(timeCheck())
                .createAt(timeCheck())
                .build();
    }
}
