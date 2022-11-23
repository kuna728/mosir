package pl.edu.wat.student.i9g1s1.mosir.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TicketListDTO {

    private final List<SingleTicketDTO> tickets;
    private final List<MultiTicketDTO> membershipCards;

    public TicketListDTO(List<SingleTicketDTO> tickets, List<MultiTicketDTO> membershipCards) {
        this.tickets = tickets;
        this.membershipCards = membershipCards;
    }
}
