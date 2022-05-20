package kz.iitu.itse1909r.mukhitdinov.core.service.impl;

import kz.iitu.itse1909r.mukhitdinov.core.controller.models.purchase.PurchaseCreateRequestModel;
import kz.iitu.itse1909r.mukhitdinov.core.controller.models.purchase.PurchaseProductRequestModel;
import kz.iitu.itse1909r.mukhitdinov.core.controller.models.purchase.PurchaseUpdateRequestModel;
import kz.iitu.itse1909r.mukhitdinov.core.controller.models.stock.StockProductRequestModel;
import kz.iitu.itse1909r.mukhitdinov.core.entity.AppUser;
import kz.iitu.itse1909r.mukhitdinov.core.entity.Purchase;
import kz.iitu.itse1909r.mukhitdinov.core.entity.PurchaseProducts;
import kz.iitu.itse1909r.mukhitdinov.core.entity.Stock;
import kz.iitu.itse1909r.mukhitdinov.core.repository.PurchaseRepository;
import kz.iitu.itse1909r.mukhitdinov.core.service.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final AppUserService userService;
    private final StockService stockService;
    private final PurchaseProductsService purchaseProductsService;
    private final StockProductsService stockProductsService;

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository, AppUserService userService, StockService stockService, PurchaseProductsService purchaseProductsService, StockProductsService stockProductsService) {
        this.purchaseRepository = purchaseRepository;
        this.userService = userService;
        this.stockService = stockService;
        this.purchaseProductsService = purchaseProductsService;
        this.stockProductsService = stockProductsService;
    }

    @Override
    public List<Purchase> getAll() {
        return purchaseRepository.findAll();
    }

    @Override
    public Purchase getById(Long id) {
        Purchase purchase = purchaseRepository.findById(id);
        if (purchase == null) {
            throw new RuntimeException("Purchase not found");
        }
        return purchase;
    }

    @Override
    public Purchase create(PurchaseCreateRequestModel purchase) {
        AppUser user = userService.getById(purchase.getUserId());
        Stock stock = stockService.getById(purchase.getStockId());
        Purchase newPurchase = Purchase.builder()
                .address(purchase.getAddress())
                .status(purchase.getStatus())
                .type(purchase.getType())
                .user(user)
                .stock(stock)
                .build();
        purchaseRepository.save(newPurchase);
        return newPurchase;
    }

    @Override
    public Purchase update(Long id, PurchaseUpdateRequestModel purchase) {
        Purchase updatePurchase = getById(id);
        String oldStatus = updatePurchase.getStatus();
        String newStatus = purchase.getStatus();
        if (oldStatus.equals(Purchase.Status.FINISHED) || oldStatus.equals(Purchase.Status.CANCELED)) {
            if (!newStatus.equals(oldStatus)) {
                throw new RuntimeException("You cannot change purchase status " +
                        "because it was finished or canceled!");
            }
        }
        if (oldStatus.equals(Purchase.Status.DELIVERY) && newStatus.equals(Purchase.Status.PAYMENT)) {
            throw new RuntimeException("You cannot change purchase status " +
                    "back to payment, because it is already in delivery");
        }
        if (oldStatus.equals(Purchase.Status.DELIVERY) || oldStatus.equals(Purchase.Status.PAYMENT)) {
            if (newStatus.equals(Purchase.Status.FINISHED)) {
                processPurchase(updatePurchase);
            }
        }
        updatePurchase.setStatus(purchase.getStatus());
        updatePurchase.setAddress(purchase.getAddress());
        updatePurchase.setType(purchase.getType());
        return purchaseRepository.update(updatePurchase);
    }

    @Override
    public void processPurchase(Purchase purchase) {
        if (purchase.getType().equals(Purchase.Type.INCOME)) {
            for(PurchaseProducts product: purchase.getProducts()) {
                stockProductsService.addProductToStock(
                        product.getStock(),
                        new StockProductRequestModel(
                                product.getProduct().getId(),
                                product.getQuantity()
                        )
                );
            }
        } else {
            for(PurchaseProducts product: purchase.getProducts()) {
                if (!stockProductsService.takeProductFromStock(
                    product.getStock(),
                    product.getProduct(),
                    product.getQuantity(),
                    true
                )) {
                    throw new RuntimeException("Cannot change status to finished, because they are " +
                            "not all products available on stock");
                }
            }
            for(PurchaseProducts product: purchase.getProducts()) {
                stockProductsService.takeProductFromStock(
                        product.getStock(),
                        product.getProduct(),
                        product.getQuantity(),
                        false
                );
            }
        }
    }

    @Override
    public void delete(Long id) {
        Purchase purchase = getById(id);
        purchaseRepository.delete(purchase);
    }

    @Override
    public void addProductToPurchase(Long purchaseId,
                                     PurchaseProductRequestModel purchaseProduct) {
        Purchase purchase = getById(purchaseId);
        purchaseProductsService.addProductToPurchase(purchase, purchaseProduct);
    }

    @Override
    public void removeProductFromPurchase(Long purchaseId, Long productId) {
        Purchase purchase = getById(purchaseId);
        purchaseProductsService.removeProductFromPurchase(purchase, productId);
    }
}
