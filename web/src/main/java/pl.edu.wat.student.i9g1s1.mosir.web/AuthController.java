package pl.edu.wat.student.i9g1s1.mosir.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.student.i9g1s1.mosir.domain.AccountOperationToken;
import pl.edu.wat.student.i9g1s1.mosir.dto.CommonValidationErrorResponseDTO;
import pl.edu.wat.student.i9g1s1.mosir.dto.auth.*;
import pl.edu.wat.student.i9g1s1.mosir.service.auth.AccountOperationTokenService;
import pl.edu.wat.student.i9g1s1.mosir.service.auth.AuthService;
import pl.edu.wat.student.i9g1s1.mosir.service.auth.PasswordResetService;
import pl.edu.wat.student.i9g1s1.mosir.service.auth.exception.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

    private final AuthService authService;
    private final PasswordResetService passwordResetService;
    private final AccountOperationTokenService tokenService;

    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody AuthRequestDTO authRequestDTO) {
        try {
            return authService.authenticate(authRequestDTO.getUsername(), authRequestDTO.getPassword());
        } catch (BadCredentialsException e) {
            return AuthResponseDTO.UNSUCCESSFUL_RESPONSE;
        } catch (DisabledException e) {
            return AuthResponseDTO.INACTIVE_RESPONSE;
        }
    }

    @PostMapping("/register")
    public RegistrationResponseDTO register(@Valid @RequestBody RegistrationRequestDTO requestDTO) {
        return authService.register(requestDTO);
    }

    @PostMapping("/activate")
    public AccountActivationResponseDTO activate(@RequestBody AccountActivationRequestDTO requestDTO) {
        return authService.activateAccount(requestDTO.getToken());
    }

    @PostMapping("/activate/resend")
    public ResponseEntity<Void> resendActivationEmail(@RequestBody ResendActivationEmailRequestDTO requestDTO) {
        try {
            authService.resendActivationMail(requestDTO.getUsername());
            return ResponseEntity.ok().build();
        } catch (UsernameNotFoundException | IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/reset/generate")
    public ResponseEntity<Void> generateResetPasswordToken(@Valid @RequestBody GeneratePasswordResetTokenRequestDTO requestDTO) {
        try {
            passwordResetService.sendPasswordResetEmail(requestDTO.getEmail());
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reset/verify")
    public TokenVerificationResponseDTO verifyResetPasswordToken(@RequestBody TokenVerificationRequestDTO requestDTO) {
        try {
            AccountOperationToken token = tokenService.validate(requestDTO.getToken(), requestDTO.getTokenOperationType());
            return new TokenVerificationResponseDTO(true, TokenVerificationResponseDTO.TokenStatus.PENDING, token.getUser().getEmail());
        } catch (InvalidOperationTokenException e) {
            return new TokenVerificationResponseDTO(false, TokenVerificationResponseDTO.TokenStatus.INVALID);
        } catch (UsedOperationTokenException e) {
            return new TokenVerificationResponseDTO(false, TokenVerificationResponseDTO.TokenStatus.USED);
        } catch (DroppedOperationTokenException e) {
            return new TokenVerificationResponseDTO(false, TokenVerificationResponseDTO.TokenStatus.DROPPED);
        } catch (OperationTokenException e) {
            return new TokenVerificationResponseDTO(false, TokenVerificationResponseDTO.TokenStatus.EXPIRED);
        }
    }

    @PostMapping("/reset/finalize")
    public ResponseEntity<Void> finalizePasswordReset(@Valid @RequestBody FinalizePasswordResetRequestDTO requestDTO) {
        try {
            passwordResetService.resetPassword(requestDTO.getToken(), requestDTO.getPassword());
        } catch (OperationTokenException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok().build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonValidationErrorResponseDTO handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new CommonValidationErrorResponseDTO(false, errors);
    }

}
