package com.example.crud_product_for_datasite.repository;

import com.example.crud_product_for_datasite.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<ReportEntity,Integer> {
    List<ReportEntity> findAllBySellDate(String sellDate);
    List<ReportEntity> findAllBySellerId(int sellerId);

    ReportEntity findBySellerIdAndSellDate(int sellerId, String sellDate);
    List<ReportEntity> findAllBySellerIdAndSellDate(int sellerId, String sellDate);
}
