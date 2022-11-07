package pl.edu.wat.student.i9g1s1.mosir.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "mosir_user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(name = "username", length = 50, nullable = false, unique = true)
    private String username;

    @Column(name = "password", length = 60, nullable = false)
    private String password;

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 70, nullable = false)
    private String lastName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "nrn", length = 11, nullable = false, unique = true)
    private String nationalRegistryNumber;

    @Column(name = "email", length = 150, nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number", length = 12, nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "address_line_1", length = 100, nullable = false)
    private String addressLine1;

    @Column(name = "address_line_2", length = 100)
    private String addressLine2;

    @Column(name = "zip_code", length = 6, nullable = false)
    private String zipCode;

    @Column(name = "city", length = 50, nullable = false)
    private String city;

}
