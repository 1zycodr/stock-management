package kz.iitu.itse1909r.mukhitdinov.core.entity;

import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.util.Set;

@Entity
@Table(name="purchase")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Purchase {
    public static class Type {
        public static final String INCOME = "INCOME";
        public static final String OUTCOME = "OUTCOME";
    }
    public static class Status {
        public static final String PAYMENT = "PAYMENT";
        public static final String DELIVERY = "DELIVERY";
        public static final String FINISHED = "FINISHED";
        public static final String CANCELED = "CANCELED";
    }

    @Id
    @Max(100000)
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "STATUS", columnDefinition = "varchar(255) default 'PAYMENT'")
    private String status;

    @Column(name = "PURCHASE_TYPE")
    private String type;

    @ManyToOne
    @JoinColumn(name = "purchase_id")
    AppUser user;

    @OneToMany(mappedBy = "purchase")
    Set<PurchaseProducts> products;
}
