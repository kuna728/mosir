package pl.edu.wat.student.i9g1s1.mosir;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.student.i9g1s1.mosir.domain.ClientsTicket;

import java.util.List;

public interface ClientsTicketRepository extends JpaRepository<ClientsTicket, Long> {
    public List<ClientsTicket> getAllByClientUserUsername(String username);
}
