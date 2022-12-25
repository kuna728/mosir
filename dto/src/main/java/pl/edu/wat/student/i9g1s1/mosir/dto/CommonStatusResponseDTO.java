package pl.edu.wat.student.i9g1s1.mosir.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommonStatusResponseDTO {
    Boolean success;
    Map<String, String> errors;

    public static CommonStatusResponseDTO fromSingleError(String errorKey, String errorValue) {
        Map<String, String> errors = new HashMap<>();
        errors.put(errorKey, errorValue);
        return new CommonStatusResponseDTO(false, errors);
    }
}
