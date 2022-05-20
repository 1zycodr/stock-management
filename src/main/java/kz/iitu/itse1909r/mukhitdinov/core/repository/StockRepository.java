package kz.iitu.itse1909r.mukhitdinov.core.repository;

import kz.iitu.itse1909r.mukhitdinov.core.entity.Stock;

import java.util.List;

public interface StockRepository {
    List<Stock> findAll();
    Stock findById(Long id);
    Stock save(Stock stock);
    Stock update(Stock stock);
    void delete(Stock stock);
}
