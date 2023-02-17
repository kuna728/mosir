package pl.edu.wat.student.i9g1s1.mosir.dto.auth;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountActivationResponseDTO {
    private boolean success;
    private TokenVerificationResponseDTO.TokenStatus status;
}
