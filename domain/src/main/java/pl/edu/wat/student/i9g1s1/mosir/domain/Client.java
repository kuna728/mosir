package pl.edu.wat.student.i9g1s1.mosir.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "client")
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "client")
    private List<ClientsTicket> clientsTickets;

    @OneToMany(mappedBy = "client")
    private List<ClientsMembershipCard> clientsMembershipCards;

    @OneToMany(mappedBy = "client")
    private List<Opinion> opinions;

    @OneToMany(mappedBy = "client")
    private List<RosterEntry> entries;

}
