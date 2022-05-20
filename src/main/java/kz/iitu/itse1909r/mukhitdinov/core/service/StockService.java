package kz.iitu.itse1909r.mukhitdinov.core.service;

import kz.iitu.itse1909r.mukhitdinov.core.controller.models.stock.StockCreateRequestModel;
import kz.iitu.itse1909r.mukhitdinov.core.controller.models.stock.StockProductRequestModel;
import kz.iitu.itse1909r.mukhitdinov.core.controller.models.stock.StockResponseBody;
import kz.iitu.itse1909r.mukhitdinov.core.controller.models.stock.StockUpdateRequestModel;
import kz.iitu.itse1909r.mukhitdinov.core.entity.Stock;

import java.util.List;

public interface StockService {
    List<Stock> getAll();
    Stock getById(Long id);
    Stock create(StockCreateRequestModel stock);
    Stock update(Long id, StockUpdateRequestModel stock);
    void delete(Long id);
    void addProductToStock(Long stockId, StockProductRequestModel stockProduct);
    void removeProductFromStock(Long stockId, Long productId);
}
