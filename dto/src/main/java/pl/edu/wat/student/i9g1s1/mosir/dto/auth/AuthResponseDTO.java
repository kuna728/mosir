package pl.edu.wat.student.i9g1s1.mosir.dto.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class AuthResponseDTO {

    public static AuthResponseDTO UNSUCCESSFUL_RESPONSE = new AuthResponseDTO(false, null, null, null, null);

    private final boolean success;
    private final String token;
    private final String role;
    private final String firstName;
    private final String lastName;

}
