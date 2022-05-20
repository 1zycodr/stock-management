package kz.iitu.itse1909r.mukhitdinov.core.repository;

import kz.iitu.itse1909r.mukhitdinov.core.entity.Purchase;

import java.util.List;

public interface PurchaseRepository {
    List<Purchase> findAll();
    Purchase findById(Long id);
    Purchase save(Purchase purchase);
    Purchase update(Purchase purchase);
    void delete(Purchase purchase);
}
