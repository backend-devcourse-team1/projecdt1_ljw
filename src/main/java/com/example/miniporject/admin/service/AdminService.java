package com.example.miniporject.admin.service;

import com.example.miniporject.user.model.entitiy.OrderEntitiy;
import com.example.miniporject.user.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final OrderRepository orderRepository;

    @Transactional

    public String deleteUser(String email) {
        orderRepository.deleteByEmail(email);
        return email;
    }

    @Transactional
    public String updateUserEmail(String oldEmail, String newEmail) {
        Optional<List<OrderEntitiy>> byEmail = orderRepository.findByEmail(oldEmail);
        byEmail.orElseThrow(() -> new EntityNotFoundException("User not found with Email: " + oldEmail));
        List<OrderEntitiy> orderEntitiys = byEmail.get();
        orderEntitiys.forEach(orderEntity -> orderEntity.changUserEmail(newEmail));
        return "변경완료";
    }
}
