package pl.edu.wat.student.i9g1s1.mosir.domain;

import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "opinion")
@Data
public class Opinion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Coach coach;

    @Column(name = "published_at")
    private LocalDateTime publishedAt;

    @Column(name = "grade")
    @NumberFormat(pattern = "0", style = NumberFormat.Style.NUMBER)
    private Integer grade;

    @Column(name = "content", length = 1000)
    private String content;

}
