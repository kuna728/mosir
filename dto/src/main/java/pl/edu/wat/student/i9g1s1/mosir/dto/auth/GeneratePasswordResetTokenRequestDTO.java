package pl.edu.wat.student.i9g1s1.mosir.dto.auth;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class GeneratePasswordResetTokenRequestDTO {

    @Email
    @NotBlank
    private String email;
}
