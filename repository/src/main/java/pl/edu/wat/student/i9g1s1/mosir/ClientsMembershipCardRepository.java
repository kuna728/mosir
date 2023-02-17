package pl.edu.wat.student.i9g1s1.mosir;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.student.i9g1s1.mosir.domain.ClientsMembershipCard;
import pl.edu.wat.student.i9g1s1.mosir.domain.MembershipCard;

import java.util.List;

public interface ClientsMembershipCardRepository extends JpaRepository<ClientsMembershipCard, Long> {
    List<ClientsMembershipCard> getAllByClientUserUsername(String username, Pageable pageable);
    long countAllByClientUserUsername(String username);
}
