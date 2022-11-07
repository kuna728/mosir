package pl.edu.wat.student.i9g1s1.mosir.dto.guest;

import lombok.Getter;
import lombok.Setter;
import pl.edu.wat.student.i9g1s1.mosir.domain.ActivityType;

@Getter
@Setter
public class ActivityTypeDTO {

    private final String name;
    private final String description;
    private final String imageUrl;

    public ActivityTypeDTO(ActivityType activityType, String imageUrl) {
        this.name = activityType.getName();
        this.description = activityType.getDescription();
        this.imageUrl = imageUrl;
    }
}
