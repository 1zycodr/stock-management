package kz.iitu.itse1909r.mukhitdinov.core.entity;

import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Set;

@Entity
@Table(name="product")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @Max(100000)
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Min(0)
    @Column(name = "PRICE", nullable = false)
    private Integer price;

    @OneToMany(mappedBy = "product")
    Set<StockProducts> stocks;

    @OneToMany(mappedBy = "product")
    Set<PurchaseProducts> purchases;
}
