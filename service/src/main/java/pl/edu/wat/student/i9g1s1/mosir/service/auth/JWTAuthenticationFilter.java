package pl.edu.wat.student.i9g1s1.mosir.service.auth;

import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.edu.wat.student.i9g1s1.mosir.UserRepository;
import pl.edu.wat.student.i9g1s1.mosir.domain.MosirUser;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@RequiredArgsConstructor
@Component
@Slf4j
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JWTUtilBean jwtUtilBean;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token;
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        Optional<Cookie> authCookie = request.getCookies() == null || request.getCookies().length == 0 ? Optional.empty()
                : Arrays.stream(request.getCookies()).filter(c -> c.getName().equals("token")).findFirst();
        String authParam = request.getParameter("token");
        if(authHeader == null || authHeader.isEmpty() || !authHeader.startsWith("Bearer ")) {
            if(authCookie.isEmpty() ) {
                if(authParam == null || authParam.isEmpty()) {
                    filterChain.doFilter(request, response);
                    return;
                }
                token = authParam;
            } else {
                token = authCookie.get().getValue();
                eraseTokenCookie(authCookie.get(), response);
            }
        } else {
            token = authHeader.substring(7);
        }
        log.debug("TOKEN PRESENT");
        try {
            String username = jwtUtilBean.getUsernameFromToken(token);
            MosirUser user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
            MosirUserPrincipal userPrincipal = new MosirUserPrincipal(user);
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                    userPrincipal, null, userPrincipal.getAuthorities()
            ));
            log.debug("AUTHENTICATION SET SUCCESSFULLY");
        } catch (JWTVerificationException | UsernameNotFoundException e) { }
        finally {
            filterChain.doFilter(request, response);
        }

    }

    private void eraseTokenCookie(Cookie authCookie, HttpServletResponse response) {
        authCookie.setValue("");
        authCookie.setPath("/");
        authCookie.setMaxAge(0);
        response.addCookie(authCookie);
    }
}
