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
import pl.edu.wat.student.i9g1s1.mosir.dto.CommonValidationErrorResponseDTO;
import pl.edu.wat.student.i9g1s1.mosir.dto.auth.*;
import pl.edu.wat.student.i9g1s1.mosir.service.auth.AuthService;
import pl.edu.wat.student.i9g1s1.mosir.service.auth.PasswordResetService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

    private final AuthService authService;
    private final PasswordResetService passwordResetService;

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

    @PostMapping("/registration")
    public RegistrationResponseDTO register(@Valid @RequestBody RegistrationRequestDTO registrationRequestDTO) {
        return authService.register(registrationRequestDTO);
    }

    @GetMapping("/activate")
    public AccountActivationResponse activate(@RequestParam String token) {
        return authService.activateAccount(token);
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
