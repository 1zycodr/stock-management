package kz.iitu.itse1909r.mukhitdinov.core.controller;

import kz.iitu.itse1909r.mukhitdinov.core.controller.models.product.ProductCreateRequestModel;
import kz.iitu.itse1909r.mukhitdinov.core.controller.models.product.ProductResponseBody;
import kz.iitu.itse1909r.mukhitdinov.core.controller.models.product.ProductUpdateRequestModel;
import kz.iitu.itse1909r.mukhitdinov.core.controller.models.stock.StockCreateRequestModel;
import kz.iitu.itse1909r.mukhitdinov.core.controller.models.stock.StockResponseBody;
import kz.iitu.itse1909r.mukhitdinov.core.controller.models.stock.StockUpdateRequestModel;
import kz.iitu.itse1909r.mukhitdinov.core.entity.Product;
import kz.iitu.itse1909r.mukhitdinov.core.entity.Stock;
import kz.iitu.itse1909r.mukhitdinov.core.service.ProductService;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Lazy
@RestController
@RequestMapping("api/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostConstruct
    public void logPostConstruct() {
        System.out.println("ProductController controller created!");
    }

    @PreDestroy
    public void logPreDestroy() {
        System.out.println("ProductController destroyed!");
    }

    @PostMapping
    @ResponseBody
    public ProductResponseBody createStock(@RequestBody ProductCreateRequestModel product) {
        Product newProduct = productService.create(product);
        return new ProductResponseBody(newProduct.getId(), newProduct.getTitle(),
                newProduct.getDescription(), newProduct.getPrice());
    }

    @GetMapping
    public ResponseEntity<Object> getProducts() {
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{id}")
    public ProductResponseBody getStock(@PathVariable Long id) {
        Product product = productService.getById(id);
        return new ProductResponseBody(product.getId(), product.getTitle(),
                product.getDescription(), product.getPrice());
    }

    @PutMapping("/{id}")
    public ProductResponseBody updateProduct(@PathVariable Long id,
                                             @RequestBody ProductUpdateRequestModel product) {
        Product updatedProduct = productService.update(id, product);
        return new ProductResponseBody(updatedProduct.getId(), updatedProduct.getTitle(),
                updatedProduct.getDescription(), updatedProduct.getPrice());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.ok().build();
    }
}
