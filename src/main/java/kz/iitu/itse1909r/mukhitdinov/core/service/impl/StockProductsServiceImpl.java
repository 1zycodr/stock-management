package kz.iitu.itse1909r.mukhitdinov.core.service.impl;

import kz.iitu.itse1909r.mukhitdinov.core.controller.models.stock.StockProductRequestModel;
import kz.iitu.itse1909r.mukhitdinov.core.entity.Product;
import kz.iitu.itse1909r.mukhitdinov.core.entity.Stock;
import kz.iitu.itse1909r.mukhitdinov.core.entity.StockProducts;
import kz.iitu.itse1909r.mukhitdinov.core.repository.StockProductsRepository;
import kz.iitu.itse1909r.mukhitdinov.core.service.ProductService;
import kz.iitu.itse1909r.mukhitdinov.core.service.StockProductsService;
import org.springframework.stereotype.Service;

@Service
public class StockProductsServiceImpl implements StockProductsService {
    private final StockProductsRepository stockProductsRepository;
    private final ProductService productService;

    public StockProductsServiceImpl(StockProductsRepository stockProductsRepository, ProductService productService) {
        this.stockProductsRepository = stockProductsRepository;
        this.productService = productService;
    }

    @Override
    public void addProductToStock(Stock stock, StockProductRequestModel stockProductModel) {
        Product product = productService.getById(stockProductModel.getProductId());
        StockProducts stockProduct = stockProductsRepository.findByStockProduct(stock.getId(), stockProductModel.getProductId());
        if (stockProduct == null) {
            stockProduct = StockProducts.builder()
                    .product(product)
                    .stock(stock)
                    .quantity(stockProductModel.getQuantity())
                    .build();
            stockProductsRepository.save(stockProduct);
        } else {
            stockProduct.setQuantity(stockProduct.getQuantity() + stockProductModel.getQuantity());
            stockProductsRepository.update(stockProduct);
        }
    }

    @Override
    public void removeProductFromStock(Stock stock, Long productId) {
        Product product = productService.getById(productId);
        StockProducts stockProduct = stockProductsRepository.findByStockProduct(stock.getId(), productId);
        if (stockProduct != null) {
            stockProductsRepository.delete(stockProduct);
        }
    }

    @Override
    public boolean takeProductFromStock(Stock stock, Product product, Long quantity, boolean check) {
        StockProducts stockProduct = stockProductsRepository.findByStockProduct(
                stock.getId(), product.getId());
        if (stockProduct != null) {
            Long finalQuantity = stockProduct.getQuantity() - quantity;
            if (finalQuantity > 0) {
                if (check) {
                    stockProduct.setQuantity(finalQuantity);
                    stockProductsRepository.update(stockProduct);
                }
            } else if (finalQuantity == 0) {
                if (check) {
                    stockProductsRepository.delete(stockProduct);
                }
            } else {
                return false;
            }
            return true;
        }
        return false;
    }
}
