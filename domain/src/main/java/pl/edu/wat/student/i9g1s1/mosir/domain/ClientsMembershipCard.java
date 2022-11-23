package pl.edu.wat.student.i9g1s1.mosir.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "client_membership_card")
@Data
public class ClientsMembershipCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "membership_card_id")
    private MembershipCard membershipCard;

    @ManyToOne
    private DiscountType discountType;

    @Column(name = "purchased_at")
    private LocalDateTime purchasedAt;

    @Column(name = "valid_till")
    private LocalDateTime validTill;

    @Column(name = "number_of_usages", nullable = false)
    private Long numberOfUsages;
}
