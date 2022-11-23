package pl.edu.wat.student.i9g1s1.mosir.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import pl.edu.wat.student.i9g1s1.mosir.dto.auth.AuthResponseDTO;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JWTUtilBean jwtUtilBean;

    public AuthResponseDTO authenticate(String username, String password) throws BadCredentialsException{
        MosirUserPrincipal user = (MosirUserPrincipal) authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password)).getPrincipal();
        return new AuthResponseDTO(true, jwtUtilBean.generateToken(username),
                user.getAuthorities().iterator().next().getAuthority(),
                user.getUser().getFirstName(), user.getUser().getLastName()
        );
    }

    public MosirUserPrincipal getCurrentUser() {
        return (MosirUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
