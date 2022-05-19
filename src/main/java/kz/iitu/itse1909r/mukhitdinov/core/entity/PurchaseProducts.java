package kz.iitu.itse1909r.mukhitdinov.core.entity;

import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Set;

@Entity
@Table(name="purchase_products")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @Max(100000)
    private Long id;

    @Min(1)
    @Column(name = "QUANTITY", nullable = false)
    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "purchase_id")
    Purchase purchase;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    Stock stock;
}
