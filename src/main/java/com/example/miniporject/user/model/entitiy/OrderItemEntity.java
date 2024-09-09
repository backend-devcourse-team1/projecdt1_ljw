package com.example.miniporject.user.model.entitiy;

import com.example.miniporject.admin.model.entitiy.Category;
import com.example.miniporject.admin.model.entitiy.ProductEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Category category;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private final LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "orderEntity_id")
    private OrderEntitiy orderEntitiy;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

    @Builder
    public OrderItemEntity(Category category, int price, int quantity, LocalDateTime updatedAt, OrderEntitiy orderEntitiy, ProductEntity productEntity) {
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.updatedAt = updatedAt;
        this.orderEntitiy = orderEntitiy;
        this.productEntity = productEntity;
    }
}
