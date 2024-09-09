package com.example.miniporject.user.model.dto;

import com.example.miniporject.admin.model.entitiy.Category;
import lombok.Data;

@Data
public class OrderRequest{

    private String email;

    private String address;

    private String postCode;

    private int quantity;

    private Category category;

    private String productName;

}
