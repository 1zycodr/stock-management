package kz.iitu.itse1909r.mukhitdinov.core.repository;

import kz.iitu.itse1909r.mukhitdinov.core.entity.Product;
import kz.iitu.itse1909r.mukhitdinov.core.entity.Stock;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();
    Product findById(Long id);
    Product save(Product product);
    Product update(Product product);
    void delete(Product product);
}
