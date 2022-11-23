package pl.edu.wat.student.i9g1s1.mosir.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuyTicketDTO {
    private String ticketType;
    private long ticketId;
    private long discountId;
}
