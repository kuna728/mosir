package pl.edu.wat.student.i9g1s1.mosir.dto.coach;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UseTicketResponseDTO {
    private boolean success;
    private String message;

    public UseTicketResponseDTO(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
