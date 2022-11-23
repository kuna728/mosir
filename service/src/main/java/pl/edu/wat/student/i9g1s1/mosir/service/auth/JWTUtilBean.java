package pl.edu.wat.student.i9g1s1.mosir.service.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtilBean {

    private static final String TOKEN_ISSUER = "mosir";

    @Value("${mosir.jwt.secret}")
    private String secret;

    public String generateToken(String username) {
        return JWT.create()
                .withClaim("username", username)
                .withIssuedAt(new Date())
                .withIssuer(TOKEN_ISSUER)
                .sign(Algorithm.HMAC256(secret));

    }

    public String getUsernameFromToken(String token) throws JWTVerificationException {
        return JWT.require(Algorithm.HMAC256(secret))
                .withIssuer(TOKEN_ISSUER)
                .build().verify(token)
                .getClaim("username").asString();
    }
}
