package pl.edu.wat.student.i9g1s1.mosir.dto.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class AccountActivationResponse {
    public enum AccountActivationStatus {SUCCESS, EXPIRED_TOKEN, INVALID_TOKEN, USED_TOKEN, DROPPED_TOKEN}
    private final AccountActivationStatus status;
}
