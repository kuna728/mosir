package pl.edu.wat.student.i9g1s1.mosir.dto.guest;

import lombok.*;
import pl.edu.wat.student.i9g1s1.mosir.domain.Hall;

@Getter
@Setter
public class HallDTO {

    private final String name;
    private final String activityType;
    private final String number;
    private final String description;
    private final String imageUrl;

    public HallDTO(Hall hall, String imageUrl) {
        this.name = hall.getName();
        this.activityType = hall.getActivityType().getName();
        this.number = hall.getNumber();
        this.description = hall.getDescription();
        this.imageUrl = imageUrl;
    }
}
