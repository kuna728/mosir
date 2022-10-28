package pl.edu.wat.student.i9g1s1.mosir.dto.visitor;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class HallDTO {

    private final String name;
    private final String type;
    private final String number;
    private final String description;
}
