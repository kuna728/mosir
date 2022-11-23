package pl.edu.wat.student.i9g1s1.mosir.dto.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;
import pl.edu.wat.student.i9g1s1.mosir.domain.MosirUser;

@Getter
@Setter
public class UserDTO {

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phoneNumber;
    private final String addressLine1;
    private final String addressLine2;
    private final String zipCode;
    private final String city;

    public UserDTO(MosirUser user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.addressLine1 = user.getAddressLine1();
        this.addressLine2 = user.getAddressLine2();
        this.zipCode = user.getZipCode();
        this.city = user.getCity();
    }

}
