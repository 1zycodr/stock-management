package kz.iitu.itse1909r.mukhitdinov.core.service;

import kz.iitu.itse1909r.mukhitdinov.core.controller.models.product.ProductCreateRequestModel;
import kz.iitu.itse1909r.mukhitdinov.core.controller.models.product.ProductUpdateRequestModel;
import kz.iitu.itse1909r.mukhitdinov.core.controller.models.stock.StockCreateRequestModel;
import kz.iitu.itse1909r.mukhitdinov.core.controller.models.stock.StockUpdateRequestModel;
import kz.iitu.itse1909r.mukhitdinov.core.entity.Product;
import kz.iitu.itse1909r.mukhitdinov.core.entity.Stock;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    Product getById(Long id);
    Product create(ProductCreateRequestModel product);
    Product update(Long id, ProductUpdateRequestModel product);
    void delete(Long id);
}
