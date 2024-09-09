package com.example.miniporject.admin.service;

import com.example.miniporject.admin.model.dto.Coffee;
import com.example.miniporject.admin.model.dto.RequestUpdateCoffe;
import com.example.miniporject.admin.model.dto.ResponseCoffee;
import com.example.miniporject.admin.model.entitiy.ProductEntity;
import com.example.miniporject.admin.repository.ProductRepository;
import com.example.miniporject.util.OrderMapTo;
import com.example.miniporject.util.ProductMapTo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    public ResponseCoffee saveProduct(Coffee coffee) {
        ProductEntity save = productRepository.save(ProductMapTo.mapToProductEntity(coffee));
        return OrderMapTo.mapToProductDto(save);
    }

    @Transactional
    public RequestUpdateCoffe updateProduct(String id, RequestUpdateCoffe requestUpdateCoffe) {
        Optional<ProductEntity> byId = productRepository.findById(Long.parseLong(id));
        byId.orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
        byId.get().updateProduct(requestUpdateCoffe);
        return requestUpdateCoffe;
    }

    @Transactional
    public String deleteProduct(String id) {
        productRepository.deleteById(Long.parseLong(id));
        return id;
    }

    public List<ResponseCoffee> getProductList() {
        return OrderMapTo.getProductList(productRepository.findAll());
    }

}
