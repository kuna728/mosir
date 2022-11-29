package pl.edu.wat.student.i9g1s1.mosir.web;

import com.google.zxing.WriterException;
import com.lowagie.text.DocumentException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.wat.student.i9g1s1.mosir.service.DynamicFilesService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/files")
@Slf4j
public class DynamicFilesController {

    private final DynamicFilesService dynamicFilesService;

    @GetMapping(value = "/invoice/{type}/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generateInvoice(@PathVariable String type, @PathVariable Long id, HttpServletResponse response) {
        try {
            byte [] invoice = dynamicFilesService.generateInvoice(type, id);
            String invoiceFileName = "faktura_" + dynamicFilesService.getInvoiceFileName(type, id) + ".pdf";
            response.addHeader(HttpHeaders.CONTENT_DISPOSITION, " attachment; filename=" + invoiceFileName);
            return ResponseEntity.ok().body(invoice);
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (DocumentException | IOException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "/ticket/{type}/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generateTicketDocument(@PathVariable String type, @PathVariable Long id, HttpServletResponse response) {
        try {
            response.addHeader(HttpHeaders.CONTENT_DISPOSITION, " attachment; filename=mosir-bilet.pdf");
            return ResponseEntity.ok(dynamicFilesService.generateTicketDocument(type, id));
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (DocumentException | IOException | WriterException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/ticket/{type}/{id}/qr", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> generateTicketQRCode(@PathVariable String type, @PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(dynamicFilesService.generateQRCode(type, id));
        } catch (IOException | WriterException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
