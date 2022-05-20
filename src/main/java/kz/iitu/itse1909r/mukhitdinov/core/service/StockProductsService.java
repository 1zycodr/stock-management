package kz.iitu.itse1909r.mukhitdinov.core.service;

import kz.iitu.itse1909r.mukhitdinov.core.controller.models.stock.StockProductRequestModel;
import kz.iitu.itse1909r.mukhitdinov.core.entity.Product;
import kz.iitu.itse1909r.mukhitdinov.core.entity.Stock;

public interface StockProductsService {
    void addProductToStock(Stock stock, StockProductRequestModel stockProduct);
    void removeProductFromStock(Stock stock, Long productId);
    boolean takeProductFromStock(Stock stock, Product product, Long quantity, boolean check);
}
