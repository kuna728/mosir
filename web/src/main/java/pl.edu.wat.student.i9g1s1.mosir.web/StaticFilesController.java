package pl.edu.wat.student.i9g1s1.mosir.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.wat.student.i9g1s1.mosir.service.StaticFilesService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@RestController
@RequiredArgsConstructor
@RequestMapping("${mosir.static-files-url}")
public class StaticFilesController {

    private final StaticFilesService staticFilesService;

    @Operation(summary = "Get image related to entity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Image has been found",
                    content = {@Content(mediaType = "image/jpeg")}),
            @ApiResponse(responseCode = "404", description = "Image doesn't exist", content = @Content)
    })
    @GetMapping(value = "/images/{entityName}/{fileName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(
            @Parameter(description = "Type of entity") @PathVariable String entityName,
            @Parameter(description = "Entity slug") @PathVariable String fileName
    ) throws IOException {
        try {
            return ResponseEntity.ok().cacheControl(CacheControl.maxAge(60, TimeUnit.MINUTES)).body(staticFilesService.getImage(entityName, fileName));
        } catch (NullPointerException | FileNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
