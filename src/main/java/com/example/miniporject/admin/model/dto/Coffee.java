package com.example.miniporject.admin.model.dto;

import com.example.miniporject.admin.model.entitiy.Category;
import lombok.Builder;
import lombok.Data;

@Data
public class Coffee {

    private String productName;

    private Category category;

    private int price;

    private String description;

}
