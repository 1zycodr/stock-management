package kz.iitu.itse1909r.mukhitdinov.core.controller.models.stock;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StockProductRequestModel {
    private Long productId;
    private Long quantity;
}
