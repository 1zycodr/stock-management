package kz.iitu.itse1909r.mukhitdinov.core.controller;

import kz.iitu.itse1909r.mukhitdinov.core.controller.models.stock.StockCreateRequestModel;
import kz.iitu.itse1909r.mukhitdinov.core.controller.models.stock.StockProductRequestModel;
import kz.iitu.itse1909r.mukhitdinov.core.controller.models.stock.StockResponseBody;
import kz.iitu.itse1909r.mukhitdinov.core.controller.models.stock.StockUpdateRequestModel;
import kz.iitu.itse1909r.mukhitdinov.core.entity.Stock;
import kz.iitu.itse1909r.mukhitdinov.core.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Lazy
@RestController
@RequestMapping("api/stock")
public class StockController {
    private StockService stockService;

    @Autowired
    public void setStockService(StockService stockService) {
        this.stockService = stockService;
    }

    @PostConstruct
    public void logPostConstruct() {
        System.out.println("StockController controller created!");
    }

    @PreDestroy
    public void logPreDestroy() {
        System.out.println("StockController destroyed!");
    }

    @PostMapping
    @ResponseBody
    public StockResponseBody createStock(@RequestBody StockCreateRequestModel stock) {
        Stock newStock = stockService.create(stock);
        return new StockResponseBody(newStock.getId(), newStock.getAddress(), newStock.getTitle());
    }

    @GetMapping
    public ResponseEntity<Object> getStocks() {
        return ResponseEntity.ok(stockService.getAll());
    }

    @GetMapping("/{id}")
    public StockResponseBody getStock(@PathVariable Long id) {
        Stock stock = stockService.getById(id);
        return new StockResponseBody(stock.getId(), stock.getAddress(), stock.getTitle());
    }

    @PutMapping("/{id}")
    public StockResponseBody updateStock(@PathVariable Long id, @RequestBody StockUpdateRequestModel stock) {
        Stock updatedStock = stockService.update(id, stock);
        return new StockResponseBody(updatedStock.getId(), updatedStock.getAddress(), updatedStock.getTitle());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStock(@PathVariable Long id) {
        stockService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/product")
    public ResponseEntity<?> addProductToStock(@PathVariable Long id,
                                               @RequestBody StockProductRequestModel stockProduct) {
        stockService.addProductToStock(id, stockProduct);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{stockId}/product/{productId}")
    public ResponseEntity<?> removeProductFromStock(@PathVariable Long stockId,
                                                    @PathVariable Long productId) {
        stockService.removeProductFromStock(stockId, productId);
        return ResponseEntity.ok().build();
    }
}
