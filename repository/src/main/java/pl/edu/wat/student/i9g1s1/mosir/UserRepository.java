package pl.edu.wat.student.i9g1s1.mosir;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.student.i9g1s1.mosir.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
