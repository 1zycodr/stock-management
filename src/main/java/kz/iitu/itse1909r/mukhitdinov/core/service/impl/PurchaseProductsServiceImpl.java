package kz.iitu.itse1909r.mukhitdinov.core.service.impl;

import kz.iitu.itse1909r.mukhitdinov.core.controller.models.purchase.PurchaseProductRequestModel;
import kz.iitu.itse1909r.mukhitdinov.core.entity.Product;
import kz.iitu.itse1909r.mukhitdinov.core.entity.Purchase;
import kz.iitu.itse1909r.mukhitdinov.core.entity.PurchaseProducts;
import kz.iitu.itse1909r.mukhitdinov.core.repository.PurchaseProductsRepository;
import kz.iitu.itse1909r.mukhitdinov.core.service.ProductService;
import kz.iitu.itse1909r.mukhitdinov.core.service.PurchaseProductsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PurchaseProductsServiceImpl implements PurchaseProductsService {
    private final ProductService productService;
    private final PurchaseProductsRepository purchaseProductsRepository;


    public PurchaseProductsServiceImpl(ProductService productService,
                                       PurchaseProductsRepository purchaseProductsRepository) {
        this.productService = productService;
        this.purchaseProductsRepository = purchaseProductsRepository;
    }

    @Override
    public void addProductToPurchase(Purchase purchase, PurchaseProductRequestModel purchaseProductModel) {
        if (purchase.getStatus().equals(Purchase.Status.CANCELED)
                || purchase.getStatus().equals(Purchase.Status.FINISHED)) {
            throw new RuntimeException("You cannot change products of finished purchase!");
        }
        if (purchase.getStatus().equals(Purchase.Status.DELIVERY)) {
            throw new RuntimeException("You cannot change products in this purchase, " +
                    "because they are in delivery");
        }
        Product product = productService.getById(purchaseProductModel.getProductId());
        PurchaseProducts purchaseProduct = purchaseProductsRepository.findByPurchaseProduct(
                purchase.getId(), product.getId()
        );
        if (purchaseProduct != null) {
            purchaseProduct.setQuantity(purchaseProduct.getQuantity() + purchaseProductModel.getQuantity());
            purchaseProductsRepository.update(purchaseProduct);
        } else {
            purchaseProduct = PurchaseProducts.builder()
                    .quantity(purchaseProductModel.getQuantity())
                    .product(product)
                    .stock(purchase.getStock())
                    .purchase(purchase)
                    .build();
            purchaseProductsRepository.save(purchaseProduct);
        }
    }

    @Override
    public void removeProductFromPurchase(Purchase purchase, Long productId) {
        if (purchase.getStatus().equals(Purchase.Status.CANCELED)
                || purchase.getStatus().equals(Purchase.Status.FINISHED)) {
            throw new RuntimeException("You cannot change products of finished purchase!");
        }
        if (purchase.getStatus().equals(Purchase.Status.DELIVERY)) {
            throw new RuntimeException("You cannot change products in this purchase, " +
                    "because they are in delivery");
        }
        Product product = productService.getById(productId);
        PurchaseProducts purchaseProduct = purchaseProductsRepository.findByPurchaseProduct(
                purchase.getId(), productId);
        if (purchaseProduct != null) {
            purchaseProductsRepository.delete(purchaseProduct);
        }
    }
}
