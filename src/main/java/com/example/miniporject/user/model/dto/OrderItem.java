package com.example.miniporject.user.model.dto;


import com.example.miniporject.admin.model.entitiy.Category;
import com.example.miniporject.user.model.entitiy.OrderStatus;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItem {
    private String address;

    private String postCode;

    private OrderStatus orderStatus;

    private String productName;

    private Category category;

    private String description;

    private int totalPrice;


    public OrderItem(String address, String postCode, OrderStatus orderStatus, String productName, Category category, String description, int totalPrice) {
        this.address = address;
        this.postCode = postCode;
        this.orderStatus = orderStatus;
        this.productName = productName;
        this.category = category;
        this.description = description;
        this.totalPrice = totalPrice;
    }
}

