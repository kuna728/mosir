package pl.edu.wat.student.i9g1s1.mosir.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wat.student.i9g1s1.mosir.ClientsMembershipCardRepository;
import pl.edu.wat.student.i9g1s1.mosir.ClientsTicketRepository;
import pl.edu.wat.student.i9g1s1.mosir.MembershipCardRepository;
import pl.edu.wat.student.i9g1s1.mosir.TicketRepository;
import pl.edu.wat.student.i9g1s1.mosir.domain.*;
import pl.edu.wat.student.i9g1s1.mosir.dto.user.BuyTicketDTO;
import pl.edu.wat.student.i9g1s1.mosir.dto.user.MultiTicketDTO;
import pl.edu.wat.student.i9g1s1.mosir.dto.user.SingleTicketDTO;
import pl.edu.wat.student.i9g1s1.mosir.dto.user.TicketListDTO;
import pl.edu.wat.student.i9g1s1.mosir.service.auth.AuthService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final AuthService authService;
    private final ClientsTicketRepository clientsTicketRepository;
    private final TicketRepository ticketRepository;
    private final MembershipCardRepository membershipCardRepository;
    private final ClientsMembershipCardRepository clientsMembershipCardRepository;

    public TicketListDTO getTickets() {
        MosirUser currentUser = authService.getCurrentUser().getUser();
        final String username = currentUser.getClient().getUser().getUsername();
        List<ClientsTicket> clientsTickets = clientsTicketRepository.getAllByClientUserUsername(username);
        List<ClientsMembershipCard> clientsMembershipCards = clientsMembershipCardRepository
                .getAllByClientUserUsername(username);
        List<SingleTicketDTO> singleTickets = clientsTickets == null || clientsTickets.isEmpty() ? new ArrayList<>()
                : clientsTickets.stream().map(t -> new SingleTicketDTO(t)).collect(Collectors.toList());
        List<MultiTicketDTO> multiTickets = clientsMembershipCards == null || clientsMembershipCards.isEmpty() ? new ArrayList<>()
                : clientsMembershipCards.stream().map(m -> new MultiTicketDTO(m)).collect(Collectors.toList());
        return new TicketListDTO(singleTickets, multiTickets);
    }

    public TicketListDTO addTicket(BuyTicketDTO buyTicketDTO) throws IllegalStateException {
        Optional<Ticket> ticketType = ticketRepository.findById(buyTicketDTO.getTicketId());
        if(ticketType.isEmpty())
            throw new IllegalStateException();
        Optional<DiscountType> discountType = ticketType.get().getDiscountTypes().stream().
                filter(d -> d.getId() == buyTicketDTO.getDiscountId()).findAny();
        if(discountType.isEmpty())
            throw new IllegalStateException();
        ClientsTicket clientsTicketToAdd = new ClientsTicket();
        clientsTicketToAdd.setClient(authService.getCurrentUser().getUser().getClient());
        clientsTicketToAdd.setTicket(ticketType.get());
        clientsTicketToAdd.setDiscountType(discountType.get());
        clientsTicketToAdd.setPurchasedAt(LocalDateTime.now());
        clientsTicketToAdd.setValidTill(LocalDateTime.now().plusYears(1));
        clientsTicketToAdd.setUsed(false);
        clientsTicketRepository.save(clientsTicketToAdd);
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
        ClientsMembershipCard clientsMembershipCardToAdd = new ClientsMembershipCard();
        clientsMembershipCardToAdd.setClient(authService.getCurrentUser().getUser().getClient());
        clientsMembershipCardToAdd.setMembershipCard(membershipCardType.get());
        clientsMembershipCardToAdd.setDiscountType(discountType.get());
        clientsMembershipCardToAdd.setPurchasedAt(LocalDateTime.now());
        clientsMembershipCardToAdd.setValidTill(LocalDateTime.now().plusYears(1));
        clientsMembershipCardToAdd.setNumberOfUsages(0l);
        clientsMembershipCardRepository.save(clientsMembershipCardToAdd);
        return new TicketListDTO(null, Arrays.asList(new MultiTicketDTO(clientsMembershipCardToAdd)));
    }
}
