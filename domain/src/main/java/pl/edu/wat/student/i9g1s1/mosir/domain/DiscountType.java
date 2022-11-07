package pl.edu.wat.student.i9g1s1.mosir.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "discount_type")
public class DiscountType {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "value", precision = 3, scale = 2, nullable = false)
    private BigDecimal value;

    @ManyToMany(mappedBy = "discountTypes")
    private List<Ticket> tickets;

    @ManyToMany(mappedBy = "discountTypes")
    private List<MembershipCard> membershipCards;
}
