package pl.edu.wat.student.i9g1s1.mosir.dto.guest;

import lombok.Getter;
import lombok.Setter;
import pl.edu.wat.student.i9g1s1.mosir.domain.DiscountType;

import java.math.BigDecimal;

@Getter
@Setter
public class DiscountTypeDTO {

    private final String name;
    private final BigDecimal value;

    public DiscountTypeDTO(DiscountType discountType) {
        this.name = discountType.getName();
        this.value = discountType.getValue();
    }
}
