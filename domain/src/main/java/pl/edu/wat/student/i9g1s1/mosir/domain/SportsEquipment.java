package pl.edu.wat.student.i9g1s1.mosir.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "sports_equipment")
@Data
public class SportsEquipment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "model", length = 50, nullable = false)
    private String model;

    @Column(name = "serial_number", length = 20, unique = true)
    private String serialNumber;

    @ManyToMany(mappedBy = "sportsEquipments")
    private Set<RosterEntry> entries;
}
