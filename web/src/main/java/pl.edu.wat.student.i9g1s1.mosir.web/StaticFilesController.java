package pl.edu.wat.student.i9g1s1.mosir.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.wat.student.i9g1s1.mosir.service.StaticFilesService;

import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("${mosir.static-files-url}")
public class StaticFilesController {

    private final StaticFilesService staticFilesService;

    @GetMapping(value = "/images/{entityName}/{fileName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(@PathVariable String entityName, @PathVariable String fileName) throws IOException {
        try {
            return staticFilesService.getImage(entityName, fileName);
        } catch (NullPointerException | FileNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
