package pl.edu.wat.student.i9g1s1.mosir.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenericListResponseDTO<T> {
    private long page;
    private long size;
    private long total;
    private List<T> items;
}
