package kz.iitu.itse1909r.mukhitdinov.core.controller.models.product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductCreateRequestModel {
    private String title;
    private String description;
    private Integer price;
}
