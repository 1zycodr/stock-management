package kz.iitu.itse1909r.mukhitdinov.core.controller.models.stock;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StockResponseBody {
    private Long id;
    private String address;
    private String title;
}