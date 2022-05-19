package kz.iitu.itse1909r.mukhitdinov.core.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name="app_user")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {
//    public final static Integer ADMIN = 0;
//    public final static Integer CUSTOMER = 1;

    @Id
    @Max(100000)
    @Column(name = "ID")
    @OrderBy
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "USERNAME", unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Lob
    @Column(name = "FIRST_NAME")
    private String first_name;

    @Transient
    @Column(name = "LAST_NAME")
    private String last_name;

    @Version
    private Long version;

    @OneToMany(mappedBy = "user")
    Set<Purchase> purchases;

    @Column
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"
            )
    )
    private Collection<Role> roles;
}
