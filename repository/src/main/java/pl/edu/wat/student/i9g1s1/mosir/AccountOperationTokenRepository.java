package pl.edu.wat.student.i9g1s1.mosir;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.edu.wat.student.i9g1s1.mosir.domain.AccountOperationToken;
import pl.edu.wat.student.i9g1s1.mosir.domain.MosirUser;

import java.util.Optional;

public interface AccountOperationTokenRepository extends JpaRepository<AccountOperationToken, Long> {

    Optional<AccountOperationToken> findByTokenAndOperationType(String token, AccountOperationToken.OperationType operationType);

    @Modifying
    @Query(value = "update account_operation_token set status='DROPPED' " +
            "where user_id=(select id from mosir_user where email=:email) " +
            "and status='PENDING' and operation_type=:operationType", nativeQuery = true)
    int dropPendingTokens(@Param("email") String email, @Param("operationType") String operationType);

}
