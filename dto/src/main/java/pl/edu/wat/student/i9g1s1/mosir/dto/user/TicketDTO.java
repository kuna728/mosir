package pl.edu.wat.student.i9g1s1.mosir.dto.user;

import lombok.Getter;
import lombok.Setter;
import pl.edu.wat.student.i9g1s1.mosir.domain.ClientsTicket;
import pl.edu.wat.student.i9g1s1.mosir.dto.DiscountTypeDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class TicketDTO {

    private final long id;
    private final BigDecimal price;
    private final String activityType;
    private final DiscountTypeDTO discountType;
    private final LocalDateTime purchasedAt;
    private final LocalDateTime validTill;
    private final Boolean used;

    public TicketDTO(ClientsTicket clientsTicket) {
        this.id = clientsTicket.getId();
        this.price = clientsTicket.getTicket().getPrice().multiply(clientsTicket.getDiscountType().getValue());
        this.discountType = new DiscountTypeDTO(clientsTicket.getDiscountType());
        this.activityType = clientsTicket.getTicket().getActivityType().getName();
        this.purchasedAt = clientsTicket.getPurchasedAt();
        this.validTill = clientsTicket.getValidTill();
        this.used = clientsTicket.getUsed();
    }
}
