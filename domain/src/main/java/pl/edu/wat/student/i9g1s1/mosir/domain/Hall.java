package pl.edu.wat.student.i9g1s1.mosir.domain;

import lombok.Data;
import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "hall")
public class Hall {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "hall_number", length = 10, nullable = false, unique = true)
    private String number;

    @Column(name = "description", length = 500)
    private String description;

    @ManyToOne
    private HallType hallType;

    @OneToMany(mappedBy = "hall")
    private Set<RosterEntry> entries;

}
