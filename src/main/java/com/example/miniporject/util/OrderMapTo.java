package com.example.miniporject.util;

import com.example.miniporject.admin.model.dto.ResponseCoffee;
import com.example.miniporject.admin.model.entitiy.ProductEntity;
import com.example.miniporject.user.model.dto.OrderItem;
import com.example.miniporject.user.model.dto.OrderListResponse;
import com.example.miniporject.user.model.dto.OrderRequest;
import com.example.miniporject.user.model.entitiy.OrderEntitiy;
import com.example.miniporject.user.model.entitiy.OrderItemEntity;
import com.example.miniporject.user.model.entitiy.OrderStatus;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class OrderMapTo {

   private static LocalDateTime timeCheck() {
      return LocalDateTime.now();
   }

   public static OrderListResponse mapToOrderList(List<OrderEntitiy> orderEntitiy, String email) {

      return OrderListResponse.builder()
              .email(email)
              .totalSiZe(orderEntitiy.stream()
                      .mapToInt(order -> order.getOrderItemList().size())
                      .count())
              .totalPrice(orderEntitiy.stream()
                      .flatMap(order -> order.getOrderItemList().stream())
                              .mapToInt(OrderItemEntity::getPrice)
                              .sum()
              )
              .orderItemList(orderEntitiy.stream()
                      .flatMap(order -> order.getOrderItemList().stream()
                              .map(OrderMapTo::toOrderItem))
                      .toList())
              .build();
   }

   private static OrderItem toOrderItem(OrderItemEntity orderItem) {
      ProductEntity productEntity = orderItem.getProductEntity();
      OrderEntitiy orderEntitiy = orderItem.getOrderEntitiy();
      return OrderItem.builder()
              .address(orderEntitiy.getAddress())
              .postCode(orderEntitiy.getPostCode())
              .orderStatus(orderEntitiy.getOrderStatus())
              .productName(productEntity.getProductName())
              .category(productEntity.getCategory())
              .description(productEntity.getDescription())
              .totalPrice(productEntity.getPrice() * orderItem.getQuantity())
              .build();
   }

   public static OrderItemEntity mapToOrderItemEntity(OrderEntitiy orderEntitiy, ProductEntity productEntity,OrderRequest orderRequest ) {
      return OrderItemEntity.builder()
              .orderEntitiy(orderEntitiy)
              .productEntity(productEntity)
              .quantity(orderRequest.getQuantity())
              .category(orderRequest.getCategory())
              .price(orderRequest.getQuantity() * productEntity.getPrice())
              .updatedAt(timeCheck())
              .build();
   }

   public static List<ResponseCoffee> getProductList(List<ProductEntity> productEntity) {
      return productEntity.stream()
              .map(ResponseCoffee::new)
              .collect(Collectors.toList());
   }

   public static OrderItem mapToOrderItem(OrderItemEntity orderItem) {
      ProductEntity productEntity = orderItem.getProductEntity();
      return OrderItem.builder()
              .productName(productEntity.getProductName())
              .description(productEntity.getDescription())
              .totalPrice(orderItem.getQuantity() * orderItem.getPrice())
              .build();
   }

   public static ResponseCoffee mapToProductDto(ProductEntity productEntity) {
      return ResponseCoffee.builder()
              .id(productEntity.getId())
              .productName(productEntity.getProductName())
              .category(productEntity.getCategory())
              .description(productEntity.getDescription())
              .price(productEntity.getPrice())
              .build();
   }
   public static OrderEntitiy mapToOrderEntity(String email, String address, String postCode) {
      return OrderEntitiy.builder()
              .email(email)
              .address(address)
              .postCode(postCode)
              .updateAt(timeCheck())
              .orderStatus(orderStatusCheck(timeCheck()))
              .build();
   }
   private static OrderStatus orderStatusCheck(LocalDateTime now) {
      LocalTime twoPM = LocalTime.of(14, 0);
      if (now.toLocalTime().isBefore(twoPM)) {
         return OrderStatus.READY_FOR_DELIVERY;
      } else {
         return OrderStatus.ACCEPTED;
      }
   }
}
