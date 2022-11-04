package pl.edu.wat.student.i9g1s1.mosir.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="client_ticket")
@Data
public class ClientsTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    @Column(name = "purchased_at")
    private LocalDateTime purchasedAt;

    @Column(name = "valid_till")
    private LocalDateTime validTill;

    @Column(name = "used")
    private Boolean used;
}
