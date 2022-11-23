package pl.edu.wat.student.i9g1s1.mosir.dto.user;

import lombok.Getter;
import lombok.Setter;
import pl.edu.wat.student.i9g1s1.mosir.domain.ClientsMembershipCard;
import pl.edu.wat.student.i9g1s1.mosir.domain.ClientsTicket;
import pl.edu.wat.student.i9g1s1.mosir.dto.DiscountTypeDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class MultiTicketDTO {

    private final long id;
    private final BigDecimal price;
    private final String activityType;
    private final DiscountTypeDTO discountType;
    private final LocalDateTime purchasedAt;
    private final LocalDateTime validTill;
    private final Long totalUsages;
    private final Long numberOfUsages;

    public MultiTicketDTO(ClientsMembershipCard clientsMembershipCard) {
        this.id = clientsMembershipCard.getId();
        this.price = clientsMembershipCard.getMembershipCard().getPrice().multiply(clientsMembershipCard.getDiscountType().getValue());
        this.discountType = new DiscountTypeDTO(clientsMembershipCard.getDiscountType());
        this.activityType = clientsMembershipCard.getMembershipCard().getActivityType().getName();
        this.purchasedAt = clientsMembershipCard.getPurchasedAt();
        this.validTill = clientsMembershipCard.getValidTill();
        this.totalUsages = clientsMembershipCard.getMembershipCard().getTotalUsages();
        this.numberOfUsages = clientsMembershipCard.getNumberOfUsages();
    }
}
