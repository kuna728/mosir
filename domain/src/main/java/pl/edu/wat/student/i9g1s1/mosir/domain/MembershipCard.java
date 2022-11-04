package pl.edu.wat.student.i9g1s1.mosir.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "membership_card")
@Data
public class MembershipCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "price", precision = 5, scale = 2, nullable = false)
    private BigDecimal price;

    @Column(name = "total_usages", nullable = false)
    private Long totalUsages;

    @ManyToOne
    private ActivityType activityType;

    @ManyToMany
    private Set<DiscountType> discountTypes;

    @OneToMany(mappedBy = "membershipCard")
    private Set<ClientsMembershipCard> clientsMembershipCards;

}
