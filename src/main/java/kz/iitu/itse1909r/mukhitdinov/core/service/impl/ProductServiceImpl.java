package kz.iitu.itse1909r.mukhitdinov.core.service.impl;

import kz.iitu.itse1909r.mukhitdinov.core.controller.models.product.ProductCreateRequestModel;
import kz.iitu.itse1909r.mukhitdinov.core.controller.models.product.ProductUpdateRequestModel;
import kz.iitu.itse1909r.mukhitdinov.core.entity.Product;
import kz.iitu.itse1909r.mukhitdinov.core.repository.ProductRepository;
import kz.iitu.itse1909r.mukhitdinov.core.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(Long id) {
        Product product = productRepository.findById(id);
        if (product == null) {
            throw new RuntimeException("Product not found");
        }
        return product;
    }

    @Override
    public Product create(ProductCreateRequestModel product) {
        Product newProduct = Product.builder()
                .title(product.getTitle())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
        productRepository.save(newProduct);
        return newProduct;
    }

    @Override
    public Product update(Long id, ProductUpdateRequestModel product) {
        Product updateProduct = productRepository.findById(id);
        if (updateProduct == null) {
            throw new RuntimeException("Product not found");
        }
        updateProduct.setTitle(product.getTitle());
        updateProduct.setDescription(product.getDescription());
        updateProduct.setPrice(product.getPrice());
        return updateProduct;
    }

    @Override
    public void delete(Long id) {
        Product product = productRepository.findById(id);
        if (product == null) {
            throw new RuntimeException("Product not found");
        }
        productRepository.delete(product);
    }
}
