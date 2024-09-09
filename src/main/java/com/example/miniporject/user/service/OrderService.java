package com.example.miniporject.user.service;

import com.example.miniporject.admin.model.entitiy.ProductEntity;
import com.example.miniporject.admin.repository.ProductRepository;
import com.example.miniporject.user.model.dto.*;
import com.example.miniporject.user.model.entitiy.OrderEntitiy;
import com.example.miniporject.user.model.entitiy.OrderItemEntity;
import com.example.miniporject.user.model.entitiy.OrderStatus;
import com.example.miniporject.user.repository.OrderItemRepository;
import com.example.miniporject.user.repository.OrderRepository;
import com.example.miniporject.util.OrderMapTo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;

    //사용자가 주문 내역 추가시 현재 시간을 체크하여 오후 2시 전이면 오늘 배송, 이후 면 내일 배송
    public OrderItem saveOrder(OrderRequest orderRequest) {
        OrderEntitiy orderEntitiy = OrderMapTo.mapToOrderEntity(orderRequest.getEmail(), orderRequest.getAddress(), orderRequest.getAddress());
        Optional<ProductEntity> byProductName = productRepository.findByProductName(orderRequest.getProductName());
        byProductName.orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + orderRequest.getProductName()));

        OrderEntitiy save = orderRepository.save(orderEntitiy);

        OrderItemEntity orderItemEntity = OrderMapTo.mapToOrderItemEntity(save, byProductName.get(), orderRequest);
        orderEntitiy.settingOrderItemList(orderItemEntity);

        OrderItemEntity save1 = orderItemRepository.save(orderItemEntity);
        return OrderMapTo.mapToOrderItem(save1);
    }

    public void deleteOrder(String id) {
        orderRepository.deleteById(Long.parseLong(id));
    }

    public OrderListResponse getOrderList(String email) {
        Optional<List<OrderEntitiy>> byEmail = orderRepository.findByEmail(email);
        byEmail.orElseThrow(() -> new EntityNotFoundException("Email not found with email: " + email));
        return OrderMapTo.mapToOrderList(byEmail.get(), email);
    }

    @Transactional
    public void updateOrderStatus(String email, Long orderId, OrderStatus orderStatus) {
        Optional<OrderEntitiy> byEmailAndId = orderRepository.findByEmailAndId(email, orderId);
        byEmailAndId.orElseThrow(() -> new EntityNotFoundException("Order not found"));
        OrderEntitiy orderEntitiy = byEmailAndId.get();
        orderEntitiy.updateOrderStatus(orderStatus);
    }


}
