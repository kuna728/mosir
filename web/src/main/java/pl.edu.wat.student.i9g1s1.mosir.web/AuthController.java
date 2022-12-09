package pl.edu.wat.student.i9g1s1.mosir.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.wat.student.i9g1s1.mosir.dto.auth.AuthRequestDTO;
import pl.edu.wat.student.i9g1s1.mosir.dto.auth.AuthResponseDTO;
import pl.edu.wat.student.i9g1s1.mosir.dto.auth.RegistrationRequestDTO;
import pl.edu.wat.student.i9g1s1.mosir.dto.auth.RegistrationResponseDTO;
import pl.edu.wat.student.i9g1s1.mosir.service.auth.AuthService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody AuthRequestDTO authRequestDTO) {
        try {
            return authService.authenticate(authRequestDTO.getUsername(), authRequestDTO.getPassword());
        } catch (Exception e) {
            log.debug(e.getMessage());
            return AuthResponseDTO.UNSUCCESSFUL_RESPONSE;
        }
    }

    @PostMapping("/registration")
    public RegistrationResponseDTO register(@Valid @RequestBody RegistrationRequestDTO registrationRequestDTO) {
        log.info(registrationRequestDTO.getDateOfBirth().toString());
        return new RegistrationResponseDTO();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
