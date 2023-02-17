package pl.edu.wat.student.i9g1s1.mosir.dto.auth;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class FinalizePasswordResetRequestDTO {

    @NotBlank
    private String token;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,60}$")
    @NotBlank
    private String password;
}
