package kz.iitu.itse1909r.mukhitdinov.core.service;

import kz.iitu.itse1909r.mukhitdinov.core.controller.models.purchase.PurchaseProductRequestModel;
import kz.iitu.itse1909r.mukhitdinov.core.entity.Purchase;

public interface PurchaseProductsService {
    void addProductToPurchase(Purchase purchase, PurchaseProductRequestModel purchaseProduct);
    void removeProductFromPurchase(Purchase purchase, Long productId);
}
