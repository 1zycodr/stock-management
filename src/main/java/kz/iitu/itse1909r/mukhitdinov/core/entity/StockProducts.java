package kz.iitu.itse1909r.mukhitdinov.core.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name="stock_products")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @Max(100000)
    private Long id;

    @Min(1)
    @Column(name = "QUANTITY", nullable = false)
    private Long quantity;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "stock_id")
    Stock stock;
}
