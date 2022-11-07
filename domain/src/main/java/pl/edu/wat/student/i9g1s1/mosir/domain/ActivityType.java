package pl.edu.wat.student.i9g1s1.mosir.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "activity_type")
@Data
public class ActivityType {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(name = "name", length = 50, nullable = false, unique = true)
    private String name;

    @Column(name = "slug", length = 60, nullable = false, unique = true)
    private String slug;

    @Column(name = "description", length = 200)
    private String description;

    @ManyToMany
    private List<Coach> coaches;

    @OneToMany(mappedBy = "activityType")
    private List<Hall> halls;

    @OneToMany(mappedBy = "activityType")
    private List<SportsEquipment> sportsEquipments;

    @OneToMany(mappedBy = "activityType")
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "activityType")
    private List<MembershipCard> membershipCards;

    @OneToMany(mappedBy = "activityType")
    private List<RosterEntry> entries;
}
