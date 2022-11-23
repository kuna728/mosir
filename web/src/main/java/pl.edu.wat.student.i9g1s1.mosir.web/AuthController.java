package pl.edu.wat.student.i9g1s1.mosir.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.wat.student.i9g1s1.mosir.dto.auth.AuthRequestDTO;
import pl.edu.wat.student.i9g1s1.mosir.dto.auth.AuthResponseDTO;
import pl.edu.wat.student.i9g1s1.mosir.service.auth.AuthService;


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

    @GetMapping("/login")
    public String authenticate() {
        log.info("test");
        return "only POST method allowed";
    }


}
