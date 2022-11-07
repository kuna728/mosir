package pl.edu.wat.student.i9g1s1.mosir.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "coach")
@Data
public class Coach {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "slug", length = 130, nullable = false, unique = true)
    private String slug;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "is_manager")
    private Boolean isManager;

    @ManyToMany(mappedBy = "coaches")
    private List<ActivityType> activityTypes;

    @OneToMany(mappedBy = "coach")
    private List<Opinion> opinions;

    @OneToMany(mappedBy = "coach")
    private List<RosterEntry> entries;
}
