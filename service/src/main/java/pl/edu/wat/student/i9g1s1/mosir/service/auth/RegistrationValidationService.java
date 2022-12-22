package pl.edu.wat.student.i9g1s1.mosir.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wat.student.i9g1s1.mosir.UserRepository;
import pl.edu.wat.student.i9g1s1.mosir.dto.auth.RegistrationRequestDTO;
import pl.edu.wat.student.i9g1s1.mosir.validator.PersonalIDConstraint;
import pl.unak7.peselvalidator.GenderEnum;
import pl.unak7.peselvalidator.PeselValidator;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RegistrationValidationService {

    private final static String UNIQUE_FAILURE_MESSAGE = "Field must be unique. Value %s already exist";
    private final static String PESEL_FAILURE_MESSAGE = "Field nationalRegistryNumber doesn't match dateOfBirth and gender fields.";
    private final static String PARSE_FAILURE_MESSAGE = "Field is not a valid %s";

    private final UserRepository userRepository;
    private final PeselValidator peselValidator;

    public Map<String, String> validate(RegistrationRequestDTO registrationRequestDTO) {
        Map<String, String> ret = new HashMap<>();

        ret.putAll(validateUniques(registrationRequestDTO));
        if(!ret.isEmpty())
            return ret;

        ret.putAll(validatePeselWithParams(registrationRequestDTO));
        return ret;
    }

    private Map<String, String> validateUniques(RegistrationRequestDTO registrationRequestDTO) {
        Map<String, String> ret = new HashMap<>();
        if(userRepository.findByUsername(registrationRequestDTO.getUsername()).isPresent())
            ret.put("username", String.format(UNIQUE_FAILURE_MESSAGE, registrationRequestDTO.getUsername()));
        if(userRepository.findByEmail(registrationRequestDTO.getEmail()).isPresent())
            ret.put("email", String.format(UNIQUE_FAILURE_MESSAGE, registrationRequestDTO.getEmail()));
        if(userRepository.findByNationalRegistryNumber(registrationRequestDTO.getNationalRegistryNumber()).isPresent())
            ret.put("nationalRegistryNumber", String.format(UNIQUE_FAILURE_MESSAGE, registrationRequestDTO.getNationalRegistryNumber()));
        if(userRepository.findByPhoneNumber(registrationRequestDTO.getPhoneNumber()).isPresent())
            ret.put("phoneNumber", String.format(UNIQUE_FAILURE_MESSAGE, registrationRequestDTO.getPhoneNumber()));
        return ret;
    }

    private Map<String, String> validatePeselWithParams(RegistrationRequestDTO registrationRequestDTO) {
        Map<String, String> ret = new HashMap<>();
        try {
            if(!peselValidator.validate(registrationRequestDTO.getNationalRegistryNumber(),
                    LocalDate.parse(registrationRequestDTO.getDateOfBirth()),
                    decodeGender(registrationRequestDTO.getGender()))) {
                ret.put("nationalRegistryNumber", PESEL_FAILURE_MESSAGE);
            }
        } catch (IllegalStateException e) {
            ret.put("gender", String.format(PARSE_FAILURE_MESSAGE, "gender"));
        } catch (DateTimeParseException e) {
            ret.put("dateOfBirth", String.format(PARSE_FAILURE_MESSAGE, "date"));
        }
        return ret;
    }

    private GenderEnum decodeGender(String genderString) {
        if(genderString.equalsIgnoreCase("m") || genderString.equalsIgnoreCase("male")
            || genderString.equalsIgnoreCase("man")
        ) {
            return GenderEnum.MALE;
        } else if(genderString.equalsIgnoreCase("f") || genderString.equalsIgnoreCase("female")
            || genderString.equalsIgnoreCase("w") || genderString.equalsIgnoreCase("woman")
        ) {
            return GenderEnum.FEMALE;
        } else if(genderString.equalsIgnoreCase("o") || genderString.equalsIgnoreCase("other")) {
            return GenderEnum.OTHER;
        }
        throw new IllegalStateException();
    }
}
