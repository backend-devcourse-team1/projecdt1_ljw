package com.example.miniporject.admin.model.entitiy;

import com.example.miniporject.admin.model.dto.RequestUpdateCoffe;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(nullable = false)
    private int price;

    private String description;

    @Column(nullable = false)
    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    @Builder
    public ProductEntity(String productName, Category category, int price, String description, LocalDateTime createAt, LocalDateTime updateAt) {
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.description = description;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }


    public void updateProduct(RequestUpdateCoffe requestUpdateCoffe) {
        this.productName = requestUpdateCoffe.getProductName();
        this.updateAt = LocalDateTime.now();
        this.category = requestUpdateCoffe.getCategory();
        this.description = requestUpdateCoffe.getDescription();
        this.price = requestUpdateCoffe.getPrice();
    }
}
