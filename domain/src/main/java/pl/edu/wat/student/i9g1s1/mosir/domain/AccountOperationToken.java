package pl.edu.wat.student.i9g1s1.mosir.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "account_operation_token")
@Data
public class AccountOperationToken {

    private static final int ACTIVATION_DEFAULT_EXPIRATION_MINUTES = 6*60;
    private static final int PASSWORD_RESET_DEFAULT_EXPIRATION_MINUTES = 20;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public enum OperationType {ACTIVATION, PASSWORD_RESET}

    @Column(name = "operation_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private OperationType operationType;

    @Column(name = "token", length = 128, nullable = false, unique = true)
    private String token;

    public enum TokenStatus {PENDING, DROPPED, USED}

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TokenStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private MosirUser user;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    public LocalDateTime getExpirationDate() {
        return created_at.plusMinutes(operationType == OperationType.ACTIVATION
                ? ACTIVATION_DEFAULT_EXPIRATION_MINUTES : PASSWORD_RESET_DEFAULT_EXPIRATION_MINUTES);
    }

    public boolean isExpired() {
        return getExpirationDate().isBefore(LocalDateTime.now());
    }
}
