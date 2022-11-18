package pl.edu.wat.student.i9g1s1.mosir.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.wat.student.i9g1s1.mosir.dto.auth.AuthRequestDTO;
import pl.edu.wat.student.i9g1s1.mosir.dto.auth.AuthResponseDTO;
import pl.edu.wat.student.i9g1s1.mosir.service.auth.AuthService;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth/")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public AuthResponseDTO authenticate(@RequestBody AuthRequestDTO authRequestDTO) {
        try {
            return authService.authenticate(authRequestDTO.getUsername(), authRequestDTO.getPassword());
        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

    }
    @GetMapping("/login")
    public String authenticate() {
        return "only POST method allowed";
    }


}
