package pl.edu.wat.student.i9g1s1.mosir.dto;

import lombok.Getter;
import lombok.Setter;
import pl.edu.wat.student.i9g1s1.mosir.domain.DiscountType;

import java.math.BigDecimal;

@Getter
@Setter
public class DiscountTypeDTO {

    private Long id;
    private String name;
    private BigDecimal value;

    public DiscountTypeDTO() { }

    public DiscountTypeDTO(DiscountType discountType) {
        this.id = discountType.getId();
        this.name = discountType.getName();
        this.value = discountType.getValue();
    }
}
