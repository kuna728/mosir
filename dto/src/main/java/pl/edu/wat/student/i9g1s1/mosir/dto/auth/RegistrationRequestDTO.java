package pl.edu.wat.student.i9g1s1.mosir.dto.auth;

import lombok.Getter;
import lombok.Setter;
import pl.edu.wat.student.i9g1s1.mosir.validator.AgeConstraint;

import javax.validation.constraints.*;

@Getter
@Setter
public class RegistrationRequestDTO {

    @Email
    @NotBlank
    private String email;

    @Size(min = 4, max = 100)
    @NotBlank
    private String username;

    @Pattern(regexp = "^\\+\\d{11}$")
    @NotBlank
    private String phoneNumber;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,60}$")
    @NotBlank
    private String password;

    @Size(min = 2, max = 50)
    @NotBlank
    private String firstName;

    @Size(min = 2, max = 50)
    @NotBlank
    private String lastName;

    @Pattern(regexp = "^M|m|F|f|W|w|O|o$")
    @NotBlank
    private String gender;

    @AgeConstraint(min = 13, max = 100)
    private String dateOfBirth;

//    private String nationalRegistryNumber;
//    private String addressLine1;
//    private String addressLine2;
//    private String zipCode;
//    private String city;
}
