package kz.iitu.itse1909r.mukhitdinov.core.service;

import kz.iitu.itse1909r.mukhitdinov.core.controller.models.purchase.PurchaseCreateRequestModel;
import kz.iitu.itse1909r.mukhitdinov.core.controller.models.purchase.PurchaseProductRequestModel;
import kz.iitu.itse1909r.mukhitdinov.core.controller.models.purchase.PurchaseUpdateRequestModel;
import kz.iitu.itse1909r.mukhitdinov.core.controller.models.stock.StockCreateRequestModel;
import kz.iitu.itse1909r.mukhitdinov.core.controller.models.stock.StockProductRequestModel;
import kz.iitu.itse1909r.mukhitdinov.core.controller.models.stock.StockUpdateRequestModel;
import kz.iitu.itse1909r.mukhitdinov.core.entity.Purchase;
import kz.iitu.itse1909r.mukhitdinov.core.entity.Stock;

import java.util.List;

public interface PurchaseService {
    List<Purchase> getAll();
    Purchase getById(Long id);
    Purchase create(PurchaseCreateRequestModel purchase);
    Purchase update(Long id, PurchaseUpdateRequestModel purchase);
    void delete(Long id);
    void addProductToPurchase(Long purchaseId, PurchaseProductRequestModel purchaseProduct);
    void removeProductFromPurchase(Long purchaseId, Long productId);
    void processPurchase(Purchase purchase);
}
