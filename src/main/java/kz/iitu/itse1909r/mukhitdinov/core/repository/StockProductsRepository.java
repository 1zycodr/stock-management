package kz.iitu.itse1909r.mukhitdinov.core.repository;

import kz.iitu.itse1909r.mukhitdinov.core.entity.StockProducts;

public interface StockProductsRepository {
    StockProducts findByStockProduct(Long stockId, Long productId);
    void save(StockProducts stockProduct);
    void update(StockProducts stockProduct);
    void delete(StockProducts stockProduct);
}
