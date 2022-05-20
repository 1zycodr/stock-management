package kz.iitu.itse1909r.mukhitdinov.core.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.util.Set;

@Entity
@Table(name="stock")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stock {
    @Id
    @Max(100000)
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "TITLE")
    private String title;

    @OneToMany(mappedBy = "stock")
    Set<StockProducts> products;

    @JsonBackReference
    @OneToMany(mappedBy = "stock")
    Set<PurchaseProducts> purchases;
}
