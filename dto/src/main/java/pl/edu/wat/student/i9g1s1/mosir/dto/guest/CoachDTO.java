package pl.edu.wat.student.i9g1s1.mosir.dto.guest;

import lombok.Getter;
import lombok.Setter;
import pl.edu.wat.student.i9g1s1.mosir.domain.Coach;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CoachDTO {

    private final String firstName;
    private final String lastName;
    private final LocalDate dateOfBirth;
    private final String email;
    private final String phoneNumber;
    private final List<String> activityTypes;
    private final String imageUrl;

    public CoachDTO(Coach coach, String imageUrl) {
        this.firstName = coach.getUser().getFirstName();
        this.lastName = coach.getUser().getLastName();
        this.dateOfBirth = coach.getUser().getDateOfBirth();
        this.email = coach.getUser().getEmail();
        this.phoneNumber = coach.getUser().getPhoneNumber();
        this.activityTypes = coach.getActivityTypes().stream().map(activityType -> activityType.getName()).collect(Collectors.toList());
        this.imageUrl = imageUrl;
    }
}
