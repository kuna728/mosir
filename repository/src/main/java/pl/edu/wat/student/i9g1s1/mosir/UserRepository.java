package pl.edu.wat.student.i9g1s1.mosir;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.student.i9g1s1.mosir.domain.MosirUser;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MosirUser, Long> {
    Optional<MosirUser> findByUsername(String username);
    Optional<MosirUser> findByEmail(String email);
}
