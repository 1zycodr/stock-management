package kz.iitu.itse1909r.mukhitdinov.core.controller.models.purchase;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PurchaseResponseBody {
    private Long id;
    private Long userId;
    private Long stockId;
    private String address;
    private String status;
    private String type;
}
