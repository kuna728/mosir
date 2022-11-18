package pl.edu.wat.student.i9g1s1.mosir.dto.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pl.edu.wat.student.i9g1s1.mosir.domain.User;

@Getter
@Setter
@RequiredArgsConstructor
public class AuthResponseDTO {

    private final String token;
    private final String role;
    private final String firstName;
    private final String lastName;
}
