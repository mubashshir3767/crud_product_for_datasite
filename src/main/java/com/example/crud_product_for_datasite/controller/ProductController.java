package com.example.crud_product_for_datasite.controller;

import com.example.crud_product_for_datasite.ApiResponse;
import com.example.crud_product_for_datasite.dto.request.ProductRequestDTO;
import com.example.crud_product_for_datasite.dto.request.UserRequestDTO;
import com.example.crud_product_for_datasite.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public ApiResponse add(
            @RequestBody ProductRequestDTO productRequestDTO
    ) {
        return productService.addProduct(productRequestDTO);
    }

    @PutMapping("/update/{id}")
    public ApiResponse update(
            @PathVariable("id") int id,
            @RequestBody ProductRequestDTO productRequestDTO
    ) {
        return productService.updateProduct(id,productRequestDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse  Delete(
            @PathVariable("id") int id
    ) {
        return productService.deleteProduct(id);
    }

    @GetMapping("/getList")
    public ApiResponse getList(){
        return productService.getProductList();
    }



}
