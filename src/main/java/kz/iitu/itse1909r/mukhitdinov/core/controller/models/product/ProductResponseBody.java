package kz.iitu.itse1909r.mukhitdinov.core.controller.models.product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductResponseBody {
    private Long id;
    private String title;
    private String description;
    private Integer price;
}
