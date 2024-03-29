package pl.edu.wat.student.i9g1s1.mosir.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="ticket")
@Data
public class Ticket {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(name = "price", precision = 5, scale = 2, nullable = false)
    private BigDecimal price;

    @ManyToOne
    private ActivityType activityType;

    @ManyToMany
    private List<DiscountType> discountTypes;

    @OneToMany(mappedBy = "ticket")
    private List<ClientsTicket> clientsTickets;

}
