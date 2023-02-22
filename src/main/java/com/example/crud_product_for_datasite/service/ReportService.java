package com.example.crud_product_for_datasite.service;

import com.example.crud_product_for_datasite.dto.request.BasketRequestDTO;
import com.example.crud_product_for_datasite.entity.Basket;
import com.example.crud_product_for_datasite.entity.ProductEntity;
import com.example.crud_product_for_datasite.entity.ReportEntity;
import com.example.crud_product_for_datasite.repository.ProductRepository;
import com.example.crud_product_for_datasite.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReportService {
    private final ReportRepository repository;
    private final ProductRepository productRepository;


    public ReportService(ReportRepository repository, ProductRepository productRepository) {
        this.repository = repository;
        this.productRepository = productRepository;
    }

    public List<String> addReport(BasketRequestDTO basketEntity) {

        ReportEntity curr = repository
                .findBySellerIdAndSellDate(basketEntity.getSellerId(), basketEntity.getSellDate().toString());
        if (curr != null) {
            return getList(basketEntity, curr);
        }
        return getList(basketEntity);
    }

    private List<String> getList(BasketRequestDTO basketEntity) {
        ReportEntity reportEntity = getReportEntity(basketEntity);
        reportEntity.setSumPrice(getPrice(reportEntity));
        ReportEntity save = repository.save(reportEntity);
        List<String> list = new ArrayList<>();
        getMethod(list,save);
        return list;
    }

    private List<String> getList(BasketRequestDTO basketEntity, ReportEntity curr) {
        List<Basket> productBaskets = basketEntity.getProductBaskets();
        curr.getProductIdList().addAll(getList(productBaskets));
        curr.setSumPrice(getPrice(curr));
        ReportEntity save = repository.save(curr);
        List<String> list = new ArrayList<>();
        getMethod(list, save);
        return list;
    }


    public List<String> getReportList() {
        List<String> list = new ArrayList<>();

        for (ReportEntity reportEntity : repository.findAll()) {
            getMethod(list, reportEntity);
        }
        return list;
    }
    public List<String> getReportListBySellDate(String date) {

        List<String> list = new ArrayList<>();
        for (ReportEntity reportEntity : repository.findAllBySellDate(date)) {
            getMethod(list, reportEntity);
        }
        return list;
    }
    public List<String> getReportListBySellerId(int id) {

        List<String> list = new ArrayList<>();
        for (ReportEntity reportEntity : repository.findAllBySellerId(id)) {
            getMethod(list, reportEntity);
        }
        return list;
    }
    public List<String> getReportListBySellerIdAndSellDate(int id, String date) {

        List<String> list = new ArrayList<>();
        for (ReportEntity reportEntity : repository.findAllBySellerIdAndSellDate(id, date)) {
            getMethod(list, reportEntity);
        }
        return list;
    }
    private void getMethod(List<String> list, ReportEntity reportEntity) {
        List<Integer> productIdList = reportEntity.getProductIdList();
        for (Integer integer : productIdList) {
            Optional<ProductEntity> byId = productRepository.findById(integer);
            list.add("" + reportEntity + "   " + byId.get().toString());
        }
    }
    private List<Integer> getList(List<Basket> productBaskets) {
        List<Integer> productEntityList = new ArrayList<>();
        for (Basket productBasket : productBaskets) {
            Integer productId = get(productBasket);
            productEntityList.add(productId);
        }
        return productEntityList;
    }
    private Integer get(Basket productBasket) {
        Optional<ProductEntity> byName = productRepository.findByName(productBasket.getName());

        if (byName.isPresent()) {
            try {
                byName.get().setQuantity(byName.get().getQuantity() - productBasket.getQuantity());
                productRepository.save(byName.get());
            } catch (Exception e) {
                throw new NotEnoufQuantity("quantity not enough", "not found enough quantity");
            }
        }
        return byName.get().getId();
    }
    private double getPrice(ReportEntity basketEntity) {
        double price = 0;
        for (Integer product : basketEntity.getProductIdList()) {
            ProductEntity product1 = productRepository.findById(product).get();
            price = (price + product1.getPrice()) * product1.getQuantity();
        }
        return price;
    }
    private ReportEntity getReportEntity(BasketRequestDTO basketEntity) {
        ReportEntity reportEntity = new ReportEntity();
        reportEntity.setSellDate(basketEntity.getSellDate().toString());
        reportEntity.setSellerId(basketEntity.getSellerId());
        reportEntity.setProductIdList(getList(basketEntity.getProductBaskets()));
        return reportEntity;
    }

}
