package pl.edu.wat.student.i9g1s1.mosir;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.student.i9g1s1.mosir.domain.MembershipCard;

public interface MembershipCardRepository extends JpaRepository<MembershipCard, Long> {
}
