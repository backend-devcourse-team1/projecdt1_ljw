package com.example.miniporject.admin.controller;

import com.example.miniporject.admin.service.AdminService;
import com.example.miniporject.user.model.entitiy.OrderStatus;
import com.example.miniporject.user.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/user")
@RequiredArgsConstructor
public class AdminUserController {

    private final AdminService adminService;
    private final OrderService orderService;

    //사용자 이메일 삭제
    @DeleteMapping("{email}")
    public ResponseEntity<?> deleteUser(@PathVariable("email") String email) {
        adminService.deleteUser(email);
        return ResponseEntity.notFound().build();
    }

    //기존 사용자의 이메일 변경시 장바구니에 있던 모든 이메일 변경
    //유저의 개인 테이블이 존재하지 않아 일일이 다 바꿔줘야 함.
    @PutMapping("{email}")
    public ResponseEntity<?> updateUserEmail(@PathVariable("email") String oldEmail,
                                        @RequestParam("newEmail") String newEmail) {
        return ResponseEntity.ok().body(adminService.updateUserEmail(oldEmail,newEmail));
    }

    //특정 사용자의 주문 내역의 주문 상태를 변경
    @PutMapping("/order/{email}")
    public ResponseEntity<?> updateUserEmail(@PathVariable("email") String email,
                                             @RequestParam("orderEntityID") Long id,
                                             @RequestParam("orderStatus")OrderStatus orderStatus) {
        orderService.updateOrderStatus(email,id,orderStatus);
        return ResponseEntity.ok().body("changed order status : " + orderStatus);
    }
}
