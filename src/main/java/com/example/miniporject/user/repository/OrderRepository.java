package com.example.miniporject.user.repository;

import com.example.miniporject.user.model.entitiy.OrderEntitiy;
import org.hibernate.query.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntitiy, Long> {

    Optional<List<OrderEntitiy>> findByEmail(String email);

    void deleteByEmail(String email);

    Optional<OrderEntitiy> findByEmailAndId(String email, Long id);

}
