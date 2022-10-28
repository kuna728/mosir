package pl.edu.wat.student.i9g1s1.mosir.domain;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "hall_type")
@Data
public class HallType {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(name = "name", length = 50, nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "hallType")
    private List<Hall> halls;

}
