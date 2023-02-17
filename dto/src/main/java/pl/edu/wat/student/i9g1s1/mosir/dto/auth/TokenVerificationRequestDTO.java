package pl.edu.wat.student.i9g1s1.mosir.dto.auth;

import lombok.Getter;
import lombok.Setter;
import pl.edu.wat.student.i9g1s1.mosir.domain.AccountOperationToken;

@Getter
@Setter
public class TokenVerificationRequestDTO {
    private AccountOperationToken.OperationType tokenOperationType;
    private String token;
}
