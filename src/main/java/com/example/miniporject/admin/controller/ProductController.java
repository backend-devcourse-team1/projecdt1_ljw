package com.example.miniporject.admin.controller;

import com.example.miniporject.admin.model.dto.Coffee;
import com.example.miniporject.admin.model.dto.RequestUpdateCoffe;
import com.example.miniporject.admin.model.dto.ResponseCoffee;
import com.example.miniporject.admin.service.AdminService;
import com.example.miniporject.admin.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/admin/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    //커피 상품 업데이트
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") String id,
                                           @RequestBody RequestUpdateCoffe requestUpdateCoffe) {
        return ResponseEntity.ok().body(productService.updateProduct(id,requestUpdateCoffe));
    }

    //새로운 커피 상품 추가
    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody Coffee coffee) {
        ResponseCoffee responseCoffee = productService.saveProduct(coffee);
        return ResponseEntity.created(URI.create("/coffe/" + responseCoffee.getId()))
                .body(responseCoffee);
    }

    //커피 상품 제거
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") String id) {
        productService.deleteProduct(id);
        return ResponseEntity.notFound().build();
    }

    //커피 상품 리스트 제공
    @GetMapping
    public ResponseEntity<?> getProductList() {
        return ResponseEntity.ok().body(productService.getProductList());
    }


}
