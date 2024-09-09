package com.example.miniporject.admin.model.dto;

import com.example.miniporject.admin.model.entitiy.Category;
import lombok.Data;

@Data
public class RequestUpdateCoffe {


    private String productName;

    private Category category;

    private int price;

    private String description;
}
