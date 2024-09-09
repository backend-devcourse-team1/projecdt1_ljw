package com.example.miniporject.admin.model.dto;

import com.example.miniporject.admin.model.entitiy.Category;
import com.example.miniporject.admin.model.entitiy.ProductEntity;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
public class ResponseCoffee {

    private Long id;

    private String productName;

    private Category category;

    private int price;

    private String description;

    public ResponseCoffee(Long id, String productName, Category category, int price, String description) {
        this.id = id;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.description = description;
    }

    public ResponseCoffee(ProductEntity productEntity) {
        this.id = productEntity.getId();
        this.productName = productEntity.getProductName();
        this.category = productEntity.getCategory();
        this.price = productEntity.getPrice();
        this.description = productEntity.getDescription();
    }
}
