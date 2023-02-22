package com.example.crud_product_for_datasite.service;

import com.example.crud_product_for_datasite.ApiResponse;
import com.example.crud_product_for_datasite.dto.request.ProductRequestDTO;
import com.example.crud_product_for_datasite.dto.response.ProductResponseDTO;
import com.example.crud_product_for_datasite.entity.ProductEntity;
import com.example.crud_product_for_datasite.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final UserService userService;
    public ProductService(ProductRepository productRepository, UserService userService) {
        this.productRepository = productRepository;
        this.userService = userService;
    }
    public ApiResponse addProduct(ProductRequestDTO productRequestDTO) {
        Optional<ProductEntity> productEntity = productRepository.findByName(productRequestDTO.getName());

        if (productEntity.isPresent()) {
            productEntity.get().setQuantity(productEntity.get().getQuantity() + productRequestDTO.getQuantity());
            ProductEntity save = productRepository.save(productEntity.get());
            return new ApiResponse(200, "product successfully added", getProductResponse(save));
        }

        ProductEntity save = productRepository.save(getProductEntity(productRequestDTO));
        return new ApiResponse(200, "product successfully added",save);
    }


    public ApiResponse updateProduct(int id,ProductRequestDTO p) {

        Optional<ProductEntity> productEntity = productRepository.findById(id);
        if (productEntity.isPresent()) {
            ProductEntity save = productRepository.save(extracted(p, productEntity));
            return new ApiResponse(200, "product successfully updated", getProductResponse(save));
        }

        return new ApiResponse(200, "product not found");
    }
    public ApiResponse deleteProduct(int id) {
        Optional<ProductEntity> productEntity = productRepository.findById(id);

        if (productEntity.isPresent()) {
            productRepository.delete(productEntity.get());
            return new ApiResponse(200, "product successfully delete");
        }

        return new ApiResponse(403, "product not found");
    }
    public ApiResponse getProductList() {

        List<ProductEntity> productEntityList = productRepository.findAll();
        if (productEntityList.isEmpty())
            return new ApiResponse(400, "products not found");

        return new ApiResponse(200, "product List ", productEntityList);
    }
    private ProductEntity extracted(ProductRequestDTO p, Optional<ProductEntity> productEntity) {
        productEntity.get().setName(p.getName());
        productEntity.get().setPrice(p.getPrice());
        productEntity.get().setQuantity(p.getQuantity());
        return productEntity.get();
    }
    private ProductResponseDTO getProductResponse(ProductEntity p) {
        return ProductResponseDTO
                .builder()
                .id(p.getId())
                .name(p.getName())
                .price(p.getPrice())
                .quantity(p.getQuantity())
                .build();
    }
    private ProductEntity getProductEntity(ProductRequestDTO productRequestDTO) {
        return ProductEntity.builder()
                .price(productRequestDTO.getPrice())
                .name(productRequestDTO.getName())
                .quantity(productRequestDTO.getQuantity())
                .build();
    }
}
