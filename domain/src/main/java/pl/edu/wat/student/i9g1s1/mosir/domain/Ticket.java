package pl.edu.wat.student.i9g1s1.mosir.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ticket")
public class Ticket {
    @Id
    private long id;

    
}
