package com.example.crud_product_for_datasite.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Basket {

    private String name;
    private int quantity;


}
