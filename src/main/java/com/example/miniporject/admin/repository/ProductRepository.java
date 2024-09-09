package com.example.miniporject.admin.repository;

import com.example.miniporject.admin.model.entitiy.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    Optional<ProductEntity> findByProductName(String productName);

}
