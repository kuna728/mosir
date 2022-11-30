package pl.edu.wat.student.i9g1s1.mosir.service.generators;

import com.google.zxing.WriterException;
import com.lowagie.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import pl.edu.wat.student.i9g1s1.mosir.domain.ClientsMembershipCard;
import pl.edu.wat.student.i9g1s1.mosir.domain.ClientsTicket;
import pl.edu.wat.student.i9g1s1.mosir.domain.MosirUser;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class TicketDocumentGenerator {

    private final QRCodeGenerator qrCodeGenerator;
    private final GeneratorUtils generatorUtils;

    public byte[] generateTicketDocumentPDF(MosirUser user, ClientsTicket clientsTicket) throws DocumentException, IOException, WriterException {
        Context context = new Context();
        context.setVariable("qrcode", Base64.getEncoder().encodeToString(qrCodeGenerator.generateTicketQRCode(user, clientsTicket)));
        context.setVariable("banner", generatorUtils.getEncodedBanner());
        context.setVariable("ticketName", "Bilet jednorazowy");
        context.setVariable("ticketId", String.format("%06d", clientsTicket.getId()));
        context.setVariable("totalAmount", generatorUtils.getTotalAmount(clientsTicket));
        context.setVariable("ticketType", generatorUtils.getDiscountTypeName(clientsTicket.getDiscountType()));
        context.setVariable("user", user);
        context.setVariable("activityName", clientsTicket.getTicket().getActivityType().getName());
        context.setVariable("purchasedAt", clientsTicket.getPurchasedAt()
                .format(DateTimeFormatter.ISO_LOCAL_DATE));
        context.setVariable("validTill", clientsTicket.getValidTill()
                .format(DateTimeFormatter.ISO_LOCAL_DATE));
        return generatorUtils.generatePDFFromTemplate("ticket_document_template", context);
    }

    public byte[] generateTicketDocumentPDF(MosirUser user, ClientsMembershipCard clientsMembershipCard) throws DocumentException, IOException, WriterException {
        Context context = new Context();
        context.setVariable("qrcode", Base64.getEncoder().encodeToString(qrCodeGenerator.generateTicketQRCode(user, clientsMembershipCard)));
        context.setVariable("banner", generatorUtils.getEncodedBanner());
        context.setVariable("ticketName", "Karnet " + clientsMembershipCard.getMembershipCard().getTotalUsages() + " wejść");
        context.setVariable("ticketId", String.format("%06d", clientsMembershipCard.getId()));
        context.setVariable("totalAmount", generatorUtils.getTotalAmount(clientsMembershipCard));
        context.setVariable("ticketType", generatorUtils.getDiscountTypeName(clientsMembershipCard.getDiscountType()));
        context.setVariable("user", user);
        context.setVariable("activityName", clientsMembershipCard.getMembershipCard().getActivityType().getName());
        context.setVariable("purchasedAt", clientsMembershipCard.getPurchasedAt()
                .format(DateTimeFormatter.ISO_LOCAL_DATE));
        context.setVariable("validTill", clientsMembershipCard.getValidTill()
                .format(DateTimeFormatter.ISO_LOCAL_DATE));
        return generatorUtils.generatePDFFromTemplate("ticket_document_template", context);
    }

}
