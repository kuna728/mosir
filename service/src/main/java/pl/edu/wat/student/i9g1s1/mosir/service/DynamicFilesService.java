package pl.edu.wat.student.i9g1s1.mosir.service;

import com.google.zxing.WriterException;
import com.lowagie.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wat.student.i9g1s1.mosir.ClientsMembershipCardRepository;
import pl.edu.wat.student.i9g1s1.mosir.ClientsTicketRepository;
import pl.edu.wat.student.i9g1s1.mosir.domain.ClientsMembershipCard;
import pl.edu.wat.student.i9g1s1.mosir.domain.ClientsTicket;
import pl.edu.wat.student.i9g1s1.mosir.service.auth.AuthService;
import pl.edu.wat.student.i9g1s1.mosir.service.generators.InvoiceGenerator;
import pl.edu.wat.student.i9g1s1.mosir.service.generators.QRCodeGenerator;
import pl.edu.wat.student.i9g1s1.mosir.service.generators.TicketDocumentGenerator;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DynamicFilesService {
    private final ClientsTicketRepository clientsTicketRepository;
    private final ClientsMembershipCardRepository clientsMembershipCardRepository;
    private final AuthService authService;
    private final InvoiceGenerator invoiceGenerator;
    private final QRCodeGenerator qrCodeGenerator;
    private final TicketDocumentGenerator ticketDocumentGenerator;

    public byte[] generateInvoice(String type, Long id) throws DocumentException, IllegalStateException, IOException {
        if(type.equalsIgnoreCase("SINGLE")) {
            return invoiceGenerator.generateTicketInvoicePDF(authService.getCurrentUser().getUser(), getClientsTicketOrThrow(id));
        } else if(type.equalsIgnoreCase("MULTI")) {
            return invoiceGenerator.generateTicketInvoicePDF(authService.getCurrentUser().getUser(), getClientsMembershipCardOrThrow(id));
        } else {
            throw new IllegalStateException();
        }
    }

    public String getInvoiceFileName(String type, Long id) {
        LocalDateTime purchasedAt;
        if(type.equalsIgnoreCase("SINGLE")) {
            purchasedAt = getClientsTicketOrThrow(id).getPurchasedAt();
        } else if(type.equalsIgnoreCase("MULTI")) {
            purchasedAt = getClientsMembershipCardOrThrow(id).getPurchasedAt();
        } else {
            throw new IllegalStateException();
        }
        return invoiceGenerator.generateInvoiceNumber(type.toUpperCase().substring(0, 1), id, purchasedAt);
    }

    public byte[] generateTicketDocument(String type, Long id) throws DocumentException, IOException, WriterException, IllegalStateException {
        if(type.equalsIgnoreCase("SINGLE")) {
            return ticketDocumentGenerator.generateTicketDocumentPDF(authService.getCurrentUser().getUser(),
                    getClientsTicketOrThrow(id));
        } else if(type.equalsIgnoreCase("MULTI")) {
            return ticketDocumentGenerator.generateTicketDocumentPDF(authService.getCurrentUser().getUser(),
                    getClientsMembershipCardOrThrow(id));
        } else {
            throw new IllegalStateException();
        }
    }

    public byte[] generateQRCode(String type, Long id) throws IOException, WriterException, IllegalStateException {
        if(type.equalsIgnoreCase("SINGLE")) {
            return qrCodeGenerator.generateTicketQRCode(authService.getCurrentUser().getUser(), getClientsTicketOrThrow(id));
        } else if(type.equalsIgnoreCase("MULTI")) {
            return  qrCodeGenerator.generateTicketQRCode(authService.getCurrentUser().getUser(), getClientsMembershipCardOrThrow(id));
        } else {
            throw new IllegalStateException();
        }
    }

    private ClientsTicket getClientsTicketOrThrow(Long id) {
        Optional<ClientsTicket> clientsTicket = clientsTicketRepository.findById(id);
        if(clientsTicket.isEmpty() || clientsTicket.get().getClient().getUser().getId() !=
                authService.getCurrentUser().getUser().getId())
            throw new IllegalStateException();
        return clientsTicket.get();
    }

    private ClientsMembershipCard getClientsMembershipCardOrThrow(Long id) {
        Optional<ClientsMembershipCard> clientsMembershipCard = clientsMembershipCardRepository.findById(id);
        if(clientsMembershipCard.isEmpty() || clientsMembershipCard.get().getClient().getUser().getId()
                != authService.getCurrentUser().getUser().getId())
            throw new IllegalStateException();
        return clientsMembershipCard.get();
    }

}
