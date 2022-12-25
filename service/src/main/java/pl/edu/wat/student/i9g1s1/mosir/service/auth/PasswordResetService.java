package pl.edu.wat.student.i9g1s1.mosir.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.edu.wat.student.i9g1s1.mosir.AccountOperationTokenRepository;
import pl.edu.wat.student.i9g1s1.mosir.UserRepository;
import pl.edu.wat.student.i9g1s1.mosir.domain.AccountOperationToken;
import pl.edu.wat.student.i9g1s1.mosir.domain.MosirUser;
import pl.edu.wat.student.i9g1s1.mosir.dto.CommonStatusResponseDTO;
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
    private final AccountOperationTokenService tokenService;
    private final EmailService emailService;

    public CommonStatusResponseDTO sendPasswordResetEmail(String email) {
        Optional<MosirUser> user = userRepository.findByEmailAndIsActiveTrue(email);
        if(user.isEmpty())
            return CommonStatusResponseDTO.fromSingleError("email", "User with provided email does not exist");

        AccountOperationToken token = tokenService.generate(user.get(), AccountOperationToken.OperationType.PASSWORD_RESET);

        try {
            emailService.sendPasswordResetMail(user.get(), token);
            return new CommonStatusResponseDTO(true, null);
        } catch (IOException | MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
