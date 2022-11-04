package pl.edu.wat.student.i9g1s1.mosir.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "activity_type")
public class ActivityType {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(name = "name", length = 50, nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "activityType")
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "activityType")
    private List<MembershipCard> membershipCards;

    @OneToMany(mappedBy = "activityType")
    private Set<RosterEntry> entries;
}
