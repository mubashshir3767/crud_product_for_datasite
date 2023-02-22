package com.example.crud_product_for_datasite.repository;

import com.example.crud_product_for_datasite.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<ProductEntity,Integer> {
    Optional<ProductEntity> findByName(String name);
}
