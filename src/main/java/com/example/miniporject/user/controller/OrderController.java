package com.example.miniporject.user.controller;

import com.example.miniporject.user.model.dto.OrderItem;
import com.example.miniporject.user.model.dto.OrderRequest;
import com.example.miniporject.user.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    //주문내역에 상품 추가
    @PostMapping
    public ResponseEntity<?> addOrder(@RequestBody OrderRequest orderRequest) {
        orderService.saveOrder(orderRequest);
        return ResponseEntity.created(URI.create("/order/"))
                .body("?");
    }

    //주문내역에 있는 상품을 제거 ( 프론트에 번호가 표시된다는 가정 하에 ) -> 주문 취소
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") String id) {
        orderService.deleteOrder(id);
        return ResponseEntity.notFound().build();
    }

    //주문내역 변경
    @PutMapping
    public ResponseEntity<?> updateOrder(@RequestBody OrderRequest orderRequest) {
        return ResponseEntity.ok().body("OK");
    }

    //주문 내역 불러오기
    @GetMapping("{email}")
    public ResponseEntity<?> getOrderList(@PathVariable("email") String email) {
        return ResponseEntity.ok().body(orderService.getOrderList(email));
    }


}
