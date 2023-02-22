package com.example.crud_product_for_datasite.dto.request;

import com.example.crud_product_for_datasite.entity.Basket;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasketRequestDTO {

    private List<Basket> productBaskets;
    private int sellerId;
    private LocalDateTime sellDate = LocalDateTime.now();
}
