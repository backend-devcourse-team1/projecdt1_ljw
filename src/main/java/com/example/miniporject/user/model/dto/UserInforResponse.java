package com.example.miniporject.user.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class UserInforResponse {

    private String email;

    private int size;

    public UserInforResponse(String email, int size) {
        this.email = email;
        this.size = size;
    }
}
