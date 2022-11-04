package pl.edu.wat.student.i9g1s1.mosir.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "coach")
@Data
public class Coach {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "specialization", length = 100, nullable = false)
    private String specialization;

    @Column(name = "is_manager")
    private Boolean isManager;

    @OneToMany(mappedBy = "coach")
    private Set<Opinion> opinions;

    @OneToMany(mappedBy = "coach")
    private Set<RosterEntry> entries;
}
