package pl.edu.wat.student.i9g1s1.mosir.service.generators;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.edu.wat.student.i9g1s1.mosir.domain.ClientsMembershipCard;
import pl.edu.wat.student.i9g1s1.mosir.domain.ClientsTicket;
import pl.edu.wat.student.i9g1s1.mosir.domain.MosirUser;
import pl.edu.wat.student.i9g1s1.mosir.dto.coach.TicketQRCodeDTO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;

@Component
@RequiredArgsConstructor
public class QRCodeGenerator {

    private final ObjectMapper objectMapper;

    public byte[] generateTicketQRCode(MosirUser user, ClientsTicket clientsTicket) throws IOException, WriterException {
        return generateTicketQRCode(new TicketQRCodeDTO(user, clientsTicket));
    }

    public byte[] generateTicketQRCode(MosirUser user, ClientsMembershipCard clientsMembershipCard) throws IOException, WriterException {
        return generateTicketQRCode(new TicketQRCodeDTO(user, clientsMembershipCard));
    }

    private byte[] generateTicketQRCode(TicketQRCodeDTO ticketQRCodeDTO) throws IOException, WriterException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix bitMatrix = qrCodeWriter.encode(objectMapper.writeValueAsString(ticketQRCodeDTO), BarcodeFormat.QR_CODE, 250, 250, hints);

        ByteArrayOutputStream imageOutputStream = new ByteArrayOutputStream();
        MatrixToImageConfig con = new MatrixToImageConfig() ;

        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", imageOutputStream,con);
        return imageOutputStream.toByteArray();
    }
}
