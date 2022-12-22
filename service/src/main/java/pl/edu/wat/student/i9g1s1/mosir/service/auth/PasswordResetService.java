package pl.edu.wat.student.i9g1s1.mosir.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.edu.wat.student.i9g1s1.mosir.AccountOperationTokenRepository;
import pl.edu.wat.student.i9g1s1.mosir.UserRepository;
import pl.edu.wat.student.i9g1s1.mosir.domain.AccountOperationToken;
import pl.edu.wat.student.i9g1s1.mosir.domain.MosirUser;
import pl.edu.wat.student.i9g1s1.mosir.service.EmailService;

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordResetService {

    private final UserRepository userRepository;
    private final AccountOperationTokenRepository tokenRepository;
    private final EmailService emailService;

    public void sendPasswordResetEmail(String email) throws UsernameNotFoundException {
        Optional<MosirUser> user = userRepository.findByEmailAndIsActiveTrue(email);
        if(user.isEmpty())
            throw new UsernameNotFoundException(email);

        AccountOperationToken token = new AccountOperationToken();
        token.setUser(user.get());
        token.setStatus(AccountOperationToken.TokenStatus.PENDING);
        token.setOperationType(AccountOperationToken.OperationType.PASSWORD_RESET);
        token.setCreated_at(LocalDateTime.now());
        while(true) {
            try {
                token.setToken(UUID.randomUUID().toString());
                tokenRepository.save(token);
                break;
            } catch (DataIntegrityViolationException ignored) {}
        }

        try {
            emailService.sendPasswordResetMail(user.get(), token);
        } catch (IOException | MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
