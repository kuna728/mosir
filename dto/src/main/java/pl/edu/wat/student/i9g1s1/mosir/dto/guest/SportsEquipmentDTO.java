package pl.edu.wat.student.i9g1s1.mosir.dto.guest;

import lombok.Getter;
import lombok.Setter;
import pl.edu.wat.student.i9g1s1.mosir.domain.SportsEquipment;

@Getter
@Setter
public class SportsEquipmentDTO {

    private final String name;
    private final String model;
    private final String activityType;

    public SportsEquipmentDTO(SportsEquipment sportsEquipment) {
        this.name = sportsEquipment.getName();
        this.model = sportsEquipment.getModel();
        this.activityType = sportsEquipment.getActivityType().getName();
    }
}
