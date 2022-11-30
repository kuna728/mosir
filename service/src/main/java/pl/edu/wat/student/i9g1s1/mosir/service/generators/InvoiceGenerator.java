package pl.edu.wat.student.i9g1s1.mosir.service.generators;

import com.lowagie.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import pl.edu.wat.student.i9g1s1.mosir.domain.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class InvoiceGenerator {

    private final GeneratorUtils generatorUtils;

    public byte[] generateTicketInvoicePDF(MosirUser user, ClientsTicket clientsTicket) throws DocumentException, IOException {
        Context context = new Context();
        context.setVariable("invoiceNumber", generateInvoiceNumber("S",
                clientsTicket.getId(), clientsTicket.getPurchasedAt()));
        context.setVariable("purchasedAt", clientsTicket.getPurchasedAt()
                .format(DateTimeFormatter.ISO_LOCAL_DATE));
        context.setVariable("ticketName", generateTicketName(clientsTicket));
        context.setVariable("totalAmount", generatorUtils.getTotalAmount(clientsTicket));
        context.setVariable("user", user);
        context.setVariable("banner", generatorUtils.getEncodedBanner());
        return generatorUtils.generatePDFFromTemplate("ticket_invoice_template", context);
    }

    public byte[] generateTicketInvoicePDF(MosirUser user, ClientsMembershipCard clientsMembershipCard) throws DocumentException, IOException {
        Context context = new Context();
        context.setVariable("invoiceNumber", generateInvoiceNumber("M",
                clientsMembershipCard.getId(), clientsMembershipCard.getPurchasedAt()));
        context.setVariable("purchasedAt", clientsMembershipCard.getPurchasedAt()
                .format(DateTimeFormatter.ISO_LOCAL_DATE));
        context.setVariable("ticketName", generateTicketName(clientsMembershipCard));
        context.setVariable("totalAmount", generatorUtils.getTotalAmount(clientsMembershipCard));
        context.setVariable("user", user);
        context.setVariable("banner", generatorUtils.getEncodedBanner());
        return generatorUtils.generatePDFFromTemplate("ticket_invoice_template", context);
    }

    public String generateInvoiceNumber(String prefix, Long ticketId, LocalDateTime purchasedAt) {
        return prefix + String.format("%06d", ticketId) + "/"
                + purchasedAt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    private String generateTicketName(ClientsTicket clientsTicket) {
        return generateCommonTicketName(clientsTicket.getTicket().getActivityType(),
                "bilet jednorazowy", clientsTicket.getDiscountType());
    }

    private String generateTicketName(ClientsMembershipCard clientsMembershipCard) {
        return generateCommonTicketName(clientsMembershipCard.getMembershipCard().getActivityType(),
                "karnet " + clientsMembershipCard.getMembershipCard().getTotalUsages().toString() + " wejść",
                clientsMembershipCard.getDiscountType());
    }

    private String generateCommonTicketName(ActivityType activityType, String ticketName, DiscountType discountType) {
        return activityType.getName() + ", " + ticketName + ", " + generatorUtils.getDiscountTypeName(discountType);
    }
}
