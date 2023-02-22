package com.example.crud_product_for_datasite.controller;

import com.example.crud_product_for_datasite.dto.request.BasketRequestDTO;
import com.example.crud_product_for_datasite.entity.ReportEntity;
import com.example.crud_product_for_datasite.service.ReportService;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/report/")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("add")
    public List<String> add(
            @RequestBody BasketRequestDTO basketRequestDTO
    ) {
        return reportService.addReport(basketRequestDTO);
    }

    @GetMapping("getList")
    public List<String> get() {
        return reportService.getReportList();
    }

    @GetMapping("getListBySellDate/{date}")
    public List<String> getReportListBySellDate(
            @PathVariable("date") String date
    ) {
        return reportService.getReportListBySellDate(date);
    }

    @GetMapping("getListBySellerId/{id}")
    public List<String> getReportListBySellerId(
            @PathVariable("id") int id
    ) {
        return reportService.getReportListBySellerId(id);
    }

    @GetMapping("getListBySellerIdAndSellDate/{id}/{date}")
    public List<String> getReportListBySellerIdAndSellDate(
            @PathVariable("id") int id,
            @PathVariable("date") String date
    ) {
        return reportService.getReportListBySellerIdAndSellDate(id,date);
    }
}
