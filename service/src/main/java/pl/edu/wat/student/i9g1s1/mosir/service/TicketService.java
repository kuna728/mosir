package pl.edu.wat.student.i9g1s1.mosir.service;

import com.google.zxing.WriterException;
import com.lowagie.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.edu.wat.student.i9g1s1.mosir.ClientsMembershipCardRepository;
import pl.edu.wat.student.i9g1s1.mosir.ClientsTicketRepository;
import pl.edu.wat.student.i9g1s1.mosir.MembershipCardRepository;
import pl.edu.wat.student.i9g1s1.mosir.TicketRepository;
import pl.edu.wat.student.i9g1s1.mosir.domain.*;
import pl.edu.wat.student.i9g1s1.mosir.dto.GenericListResponseDTO;
import pl.edu.wat.student.i9g1s1.mosir.dto.user.BuyTicketDTO;
import pl.edu.wat.student.i9g1s1.mosir.dto.user.MultiTicketDTO;
import pl.edu.wat.student.i9g1s1.mosir.dto.user.SingleTicketDTO;
import pl.edu.wat.student.i9g1s1.mosir.dto.user.TicketListDTO;
import pl.edu.wat.student.i9g1s1.mosir.service.auth.AuthService;
import pl.edu.wat.student.i9g1s1.mosir.service.email.EmailService;

import javax.mail.MessagingException;
import javax.mail.util.ByteArrayDataSource;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final AuthService authService;
    private final ClientsTicketRepository clientsTicketRepository;
    private final TicketRepository ticketRepository;
    private final MembershipCardRepository membershipCardRepository;
    private final ClientsMembershipCardRepository clientsMembershipCardRepository;
    private final EmailService emailService;
    private final DynamicFilesService dynamicFilesService;

    public GenericListResponseDTO<SingleTicketDTO> getTickets(Pageable pageable) {
        final String username = authService.getCurrentUser().getUser().getClient().getUser().getUsername();
        List<ClientsTicket> clientsTickets = clientsTicketRepository.getAllByClientUserUsername(username, pageable);
        List<SingleTicketDTO> items =  clientsTickets == null || clientsTickets.isEmpty() ? new ArrayList<>()
                : clientsTickets.stream().map(SingleTicketDTO::new).collect(Collectors.toList());
        return new GenericListResponseDTO<>(pageable.getPageNumber(), pageable.getPageSize(),
                clientsTicketRepository.countAllByClientUserUsername(username), items);
    }

    public GenericListResponseDTO<MultiTicketDTO> getMembershipCards(Pageable pageable) {
        final String username = authService.getCurrentUser().getUser().getClient().getUser().getUsername();
        List<ClientsMembershipCard> clientsMembershipCards = clientsMembershipCardRepository
                .getAllByClientUserUsername(username, pageable);
        List<MultiTicketDTO> items = clientsMembershipCards == null || clientsMembershipCards.isEmpty() ? new ArrayList<>()
                : clientsMembershipCards.stream().map(MultiTicketDTO::new).collect(Collectors.toList());
        return new GenericListResponseDTO<>(pageable.getPageNumber(), pageable.getPageSize(),
                clientsMembershipCardRepository.countAllByClientUserUsername(username), items);
    }


    public TicketListDTO addTicket(BuyTicketDTO buyTicketDTO) throws IllegalStateException {
        Optional<Ticket> ticketType = ticketRepository.findById(buyTicketDTO.getTicketId());
        if(ticketType.isEmpty())
            throw new IllegalStateException();
        Optional<DiscountType> discountType = ticketType.get().getDiscountTypes().stream().
                filter(d -> d.getId() == buyTicketDTO.getDiscountId()).findAny();
        if(discountType.isEmpty())
            throw new IllegalStateException();
        MosirUser user = authService.getCurrentUser().getUser();
        ClientsTicket clientsTicketToAdd = new ClientsTicket();
        clientsTicketToAdd.setClient(user.getClient());
        clientsTicketToAdd.setTicket(ticketType.get());
        clientsTicketToAdd.setDiscountType(discountType.get());
        clientsTicketToAdd.setPurchasedAt(LocalDateTime.now());
        clientsTicketToAdd.setValidTill(LocalDateTime.now().plusYears(1));
        clientsTicketToAdd.setUsed(false);
        clientsTicketRepository.save(clientsTicketToAdd);
        try {
            emailService.sendPurchaseTicketMail(user, clientsTicketToAdd, generateAttachments("SINGLE", clientsTicketToAdd.getId()));
        } catch (IOException | MessagingException | DocumentException | WriterException e) {
            throw new RuntimeException(e);
        }
        return new TicketListDTO(Arrays.asList(new SingleTicketDTO(clientsTicketToAdd)), null);
    }

    public TicketListDTO addMembershipCard(BuyTicketDTO buyTicketDTO) throws IllegalStateException {
        Optional<MembershipCard> membershipCardType = membershipCardRepository.findById(buyTicketDTO.getTicketId());
        if(membershipCardType.isEmpty())
            throw new IllegalStateException();
        Optional<DiscountType> discountType = membershipCardType.get().getDiscountTypes().stream().
                filter(d -> d.getId() == buyTicketDTO.getDiscountId()).findAny();
        if(discountType.isEmpty())
            throw new IllegalStateException();
        MosirUser user = authService.getCurrentUser().getUser();
        ClientsMembershipCard clientsMembershipCardToAdd = new ClientsMembershipCard();
        clientsMembershipCardToAdd.setClient(user.getClient());
        clientsMembershipCardToAdd.setMembershipCard(membershipCardType.get());
        clientsMembershipCardToAdd.setDiscountType(discountType.get());
        clientsMembershipCardToAdd.setPurchasedAt(LocalDateTime.now());
        clientsMembershipCardToAdd.setValidTill(LocalDateTime.now().plusYears(1));
        clientsMembershipCardToAdd.setNumberOfUsages(0l);
        clientsMembershipCardRepository.save(clientsMembershipCardToAdd);
        try {
            emailService.sendPurchaseMembershipCardMail(user, clientsMembershipCardToAdd, generateAttachments("MULTI", clientsMembershipCardToAdd.getId()));
        } catch (IOException | MessagingException | DocumentException | WriterException e) {
            throw new RuntimeException(e);
        }
        return new TicketListDTO(null, Arrays.asList(new MultiTicketDTO(clientsMembershipCardToAdd)));
    }

    public Map<String, byte[]> generateAttachments(String type, Long id) throws DocumentException, IOException, WriterException {
        Map<String, byte[]> attachments = new HashMap<>();
        attachments.put("faktura.pdf", dynamicFilesService.generateInvoice(type, id));
        attachments.put("bilet.pdf", dynamicFilesService.generateTicketDocument(type, id));
        return attachments;
    }
}
