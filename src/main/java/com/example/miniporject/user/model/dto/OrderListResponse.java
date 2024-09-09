package com.example.miniporject.user.model.dto;

import com.example.miniporject.user.model.entitiy.OrderEntitiy;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class OrderListResponse {


    private String email;

    private Long totalSiZe;

    private int totalPrice;

    private List<OrderItem> orderItemList = new ArrayList<>();

    public OrderListResponse(String email, Long totalSiZe, int totalPrice, List<OrderItem> orderItemList) {
        this.email = email;
        this.totalSiZe = totalSiZe;
        this.totalPrice = totalPrice;
        this.orderItemList = orderItemList;
    }
}
