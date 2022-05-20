package kz.iitu.itse1909r.mukhitdinov.core.controller.models.purchase;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PurchaseCreateRequestModel {
    private String address;
    private String status;
    private String type;
    private Long userId;
    private Long stockId;
}
