package kz.iitu.itse1909r.mukhitdinov.core.controller.models.purchase;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PurchaseProductRequestModel {
    private Long productId;
    private Long quantity;
}
