package pl.edu.wat.student.i9g1s1.mosir.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "roster_entry")
@Data
public class RosterEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Coach coach;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Hall hall;

    @ManyToMany
    private List<SportsEquipment> sportsEquipments;

    @ManyToOne
    private ActivityType activityType;

    @Enumerated(EnumType.ORDINAL)
    private RosterEntryStatusEnum status;

    @Column(name = "date_of_activity")
    private LocalDateTime dateOfActivity;

}
