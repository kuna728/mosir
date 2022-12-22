package pl.edu.wat.student.i9g1s1.mosir.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import pl.edu.wat.student.i9g1s1.mosir.AccountOperationTokenRepository;
import pl.edu.wat.student.i9g1s1.mosir.domain.AccountOperationToken;
import pl.edu.wat.student.i9g1s1.mosir.domain.MosirUser;
import pl.edu.wat.student.i9g1s1.mosir.service.auth.exception.*;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountOperationTokenService {

    private final AccountOperationTokenRepository tokenRepository;

    public AccountOperationToken generate(MosirUser user, AccountOperationToken.OperationType operationType) {
        AccountOperationToken token = new AccountOperationToken();
        token.setUser(user);
        token.setStatus(AccountOperationToken.TokenStatus.PENDING);
        token.setOperationType(operationType);
        token.setCreated_at(LocalDateTime.now());
        while(true) {
            try {
                token.setToken(UUID.randomUUID().toString());
                tokenRepository.save(token);
                return token;
            } catch (DataIntegrityViolationException ignored) {}
        }
    }

    public AccountOperationToken validate(String tokenString, AccountOperationToken.OperationType operationType) throws OperationTokenException {
        Optional<AccountOperationToken> token = tokenRepository.findByTokenAndOperationType(tokenString, operationType);
        if(token.isEmpty())
            throw new InvalidOperationTokenException();
        if(token.get().isExpired())
            throw new ExpiredOperationTokenException();
        if(token.get().getStatus() == AccountOperationToken.TokenStatus.USED)
            throw new UsedOperationTokenException();
        if(token.get().getStatus() == AccountOperationToken.TokenStatus.DROPPED)
            throw new DroppedOperationTokenException();

        token.get().setStatus(AccountOperationToken.TokenStatus.USED);
        tokenRepository.save(token.get());
        return token.get();
    }

}
