package pl.edu.wat.student.i9g1s1.mosir.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pl.edu.wat.student.i9g1s1.mosir.dto.auth.AuthResponseDTO;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;

    public AuthResponseDTO authenticate(String username, String password) throws BadCredentialsException{
        MosirUserPrincipal userPrincipal = (MosirUserPrincipal) authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password)).getPrincipal();
        return new AuthResponseDTO("token", "user", userPrincipal.getUser().getFirstName(), userPrincipal.getUser().getLastName());
    }
}
