package pl.edu.wat.student.i9g1s1.mosir.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edu.wat.student.i9g1s1.mosir.domain.AccountOperationToken;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TokenVerificationResponseDTO {
    private boolean isValid;
    public enum TokenStatus {INVALID, PENDING, DROPPED, USED, EXPIRED}
    private TokenStatus tokenStatus;
    private String email;

    public TokenVerificationResponseDTO(boolean isValid, TokenStatus tokenStatus) {
        this.isValid = isValid;
        this.tokenStatus = tokenStatus;
    }
}
