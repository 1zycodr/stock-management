package kz.iitu.itse1909r.mukhitdinov.core.service.impl;

import kz.iitu.itse1909r.mukhitdinov.core.controller.models.stock.StockCreateRequestModel;
import kz.iitu.itse1909r.mukhitdinov.core.controller.models.stock.StockProductRequestModel;
import kz.iitu.itse1909r.mukhitdinov.core.controller.models.stock.StockUpdateRequestModel;
import kz.iitu.itse1909r.mukhitdinov.core.entity.Product;
import kz.iitu.itse1909r.mukhitdinov.core.entity.Stock;
import kz.iitu.itse1909r.mukhitdinov.core.entity.StockProducts;
import kz.iitu.itse1909r.mukhitdinov.core.repository.StockProductsRepository;
import kz.iitu.itse1909r.mukhitdinov.core.repository.StockRepository;
import kz.iitu.itse1909r.mukhitdinov.core.service.ProductService;
import kz.iitu.itse1909r.mukhitdinov.core.service.StockProductsService;
import kz.iitu.itse1909r.mukhitdinov.core.service.StockService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;
    private final StockProductsService stockProductsService;

    public StockServiceImpl(StockRepository stockRepository,
                            StockProductsService stockProductsService) {
        this.stockRepository = stockRepository;
        this.stockProductsService = stockProductsService;
    }

    @Override
    public List<Stock> getAll() {
        return stockRepository.findAll();
    }

    @Override
    public Stock getById(Long id) {
        Stock stock = stockRepository.findById(id);
        if (stock == null) {
            throw new RuntimeException("Stock not found");
        }
        return stock;
    }

    @Override
    public Stock create(StockCreateRequestModel stock) {
        Stock newStock = Stock.builder()
                        .address(stock.getAddress())
                        .title(stock.getTitle())
                        .build();
        stockRepository.save(newStock);
        return newStock;
    }

    @Override
    public Stock update(Long id, StockUpdateRequestModel stock) {
        Stock updateStock = getById(id);
        updateStock.setAddress(stock.getAddress());
        updateStock.setTitle(stock.getTitle());
        return stockRepository.update(updateStock);
    }

    @Override
    public void delete(Long id) {
        Stock stock = getById(id);
        stockRepository.delete(stock);
    }

    @Override
    public void addProductToStock(Long stockId, StockProductRequestModel stockProduct) {
        Stock stock = getById(stockId);
        stockProductsService.addProductToStock(stock, stockProduct);
    }

    @Override
    public void removeProductFromStock(Long stockId, Long productId) {
        Stock stock = getById(stockId);
        stockProductsService.removeProductFromStock(stock, productId);
    }
}
