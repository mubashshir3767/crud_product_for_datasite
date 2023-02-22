package com.example.crud_product_for_datasite.service;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@EqualsAndHashCode
@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class NotEnoufQuantity extends RuntimeException {

    private String message;
    private String type;

    public NotEnoufQuantity(String message, String type) {
        this.message = message;
        this.type = type;
    }
}
