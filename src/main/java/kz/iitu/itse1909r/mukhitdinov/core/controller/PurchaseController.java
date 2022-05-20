package kz.iitu.itse1909r.mukhitdinov.core.controller;

import kz.iitu.itse1909r.mukhitdinov.core.controller.models.purchase.PurchaseCreateRequestModel;
import kz.iitu.itse1909r.mukhitdinov.core.controller.models.purchase.PurchaseProductRequestModel;
import kz.iitu.itse1909r.mukhitdinov.core.controller.models.purchase.PurchaseResponseBody;
import kz.iitu.itse1909r.mukhitdinov.core.controller.models.purchase.PurchaseUpdateRequestModel;
import kz.iitu.itse1909r.mukhitdinov.core.entity.Purchase;
import kz.iitu.itse1909r.mukhitdinov.core.service.PurchaseService;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Lazy
@RestController
@RequestMapping("api/purchase")
public class PurchaseController {
    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostConstruct
    public void logPostConstruct() {
        System.out.println("PurchaseController controller created!");
    }

    @PreDestroy
    public void logPreDestroy() {
        System.out.println("PurchaseController destroyed!");
    }

    @PostMapping
    @ResponseBody
    public PurchaseResponseBody createPurchase(@RequestBody PurchaseCreateRequestModel purchase) {
        Purchase newPurchase = purchaseService.create(purchase);
        return new PurchaseResponseBody(newPurchase.getId(), newPurchase.getUser().getId(),
                newPurchase.getStock().getId(), newPurchase.getAddress(),
                newPurchase.getStatus(), newPurchase.getType());
    }

    @GetMapping
    public ResponseEntity<Object> getPurchases() {
        return ResponseEntity.ok(purchaseService.getAll());
    }

    @GetMapping("/{id}")
    public PurchaseResponseBody getPurchase(@PathVariable Long id) {
        Purchase purchase = purchaseService.getById(id);
        return new PurchaseResponseBody(purchase.getId(), purchase.getUser().getId(),
                purchase.getStock().getId(), purchase.getAddress(),
                purchase.getStatus(), purchase.getType());
    }

    @PutMapping("/{id}")
    public PurchaseResponseBody updatePurchase(@PathVariable Long id,
                                            @RequestBody PurchaseUpdateRequestModel purchase) {
        Purchase updatedPurchase = purchaseService.update(id, purchase);
        return new PurchaseResponseBody(updatedPurchase.getId(), updatedPurchase.getUser().getId(),
                updatedPurchase.getStock().getId(), updatedPurchase.getAddress(),
                updatedPurchase.getStatus(), updatedPurchase.getType());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePurchase(@PathVariable Long id) {
        purchaseService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/product")
    public ResponseEntity<?> addProductToPurchase(@PathVariable Long id,
                                                  @RequestBody PurchaseProductRequestModel purchaseModel
    ) {
        purchaseService.addProductToPurchase(id, purchaseModel);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{purchaseId}/product/{productId}")
    public ResponseEntity<?> removeProductFromPurchase(@PathVariable Long purchaseId,
                                                       @PathVariable Long productId
    ) {
        purchaseService.removeProductFromPurchase(purchaseId, productId);
        return ResponseEntity.ok().build();
    }
}
