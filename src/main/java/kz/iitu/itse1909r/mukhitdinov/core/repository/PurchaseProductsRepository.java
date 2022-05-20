package kz.iitu.itse1909r.mukhitdinov.core.repository;

import kz.iitu.itse1909r.mukhitdinov.core.entity.PurchaseProducts;

public interface PurchaseProductsRepository {
    PurchaseProducts findByPurchaseProduct(Long purchaseId, Long productId);
    void update(PurchaseProducts purchaseProducts);
    void save(PurchaseProducts purchaseProducts);
    void delete(PurchaseProducts purchaseProducts);
}
