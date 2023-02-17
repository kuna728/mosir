package pl.edu.wat.student.i9g1s1.mosir.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.edu.wat.student.i9g1s1.mosir.UserRepository;
import pl.edu.wat.student.i9g1s1.mosir.domain.AccountOperationToken;
import pl.edu.wat.student.i9g1s1.mosir.domain.MosirUser;
import pl.edu.wat.student.i9g1s1.mosir.service.email.EmailService;
import pl.edu.wat.student.i9g1s1.mosir.service.auth.exception.OperationTokenException;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PasswordResetService {

    private final UserRepository userRepository;
    private final AccountOperationTokenService tokenService;
    private final EmailService emailService;


    public void sendPasswordResetEmail(String email) throws UsernameNotFoundException {
        Optional<MosirUser> user = userRepository.findByEmailAndIsActiveTrue(email);
        if(user.isEmpty())
            throw new UsernameNotFoundException(email);

        AccountOperationToken token = tokenService.generate(user.get(), AccountOperationToken.OperationType.PASSWORD_RESET);

        try {
            emailService.sendPasswordResetMail(user.get(), token);
        } catch (IOException | MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void resetPassword(String tokenString, String password) throws OperationTokenException {
        AccountOperationToken token = tokenService.validateAndUse(tokenString, AccountOperationToken.OperationType.PASSWORD_RESET);
        MosirUser user = token.getUser();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }
}
