package pl.edu.wat.student.i9g1s1.mosir;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.student.i9g1s1.mosir.domain.AccountOperationToken;
import pl.edu.wat.student.i9g1s1.mosir.domain.MosirUser;

import java.util.List;
import java.util.Optional;

public interface AccountOperationTokenRepository extends JpaRepository<AccountOperationToken, Long> {
    Optional<AccountOperationToken> findByTokenAndOperationType(String token, AccountOperationToken.OperationType operationType);
    List<AccountOperationToken> findAllByUserAndOperationTypeAndStatus(MosirUser user, AccountOperationToken.OperationType operationType, AccountOperationToken.TokenStatus status);
}
