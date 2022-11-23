package pl.edu.wat.student.i9g1s1.mosir.dto.guest;

import lombok.Getter;
import lombok.Setter;
import pl.edu.wat.student.i9g1s1.mosir.domain.MembershipCard;
import pl.edu.wat.student.i9g1s1.mosir.dto.DiscountTypeDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class MembershipCardDTO {

    private final long id;
    private final BigDecimal price;
    private final String activityType;
    private final Long totalUsages;
    private final List<DiscountTypeDTO> discountTypes;

    public MembershipCardDTO(MembershipCard membershipCard) {
        this.id = membershipCard.getId();
        this.price = membershipCard.getPrice();
        this.activityType = membershipCard.getActivityType().getName();
        this.totalUsages = membershipCard.getTotalUsages();
        this.discountTypes = membershipCard.getDiscountTypes().stream().map(discountType -> new DiscountTypeDTO(discountType)).collect(Collectors.toList());
    }
}
