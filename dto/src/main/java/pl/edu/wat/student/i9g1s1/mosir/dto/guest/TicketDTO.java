package pl.edu.wat.student.i9g1s1.mosir.dto.guest;

import lombok.Getter;
import lombok.Setter;
import pl.edu.wat.student.i9g1s1.mosir.domain.Ticket;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class TicketDTO {

    private final BigDecimal price;
    private final String activityType;
    private final List<DiscountTypeDTO> discountTypes;

    public TicketDTO(Ticket ticket) {
        this.price = ticket.getPrice();
        this.activityType = ticket.getActivityType().getName();
        this.discountTypes = ticket.getDiscountTypes().stream().map(discountType -> new DiscountTypeDTO(discountType)).collect(Collectors.toList());
    }
}
