package pl.edu.wat.student.i9g1s1.mosir.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.edu.wat.student.i9g1s1.mosir.ClientsMembershipCardRepository;
import pl.edu.wat.student.i9g1s1.mosir.ClientsTicketRepository;
import pl.edu.wat.student.i9g1s1.mosir.domain.ClientsMembershipCard;
import pl.edu.wat.student.i9g1s1.mosir.domain.ClientsTicket;
import pl.edu.wat.student.i9g1s1.mosir.domain.MosirUser;
import pl.edu.wat.student.i9g1s1.mosir.dto.coach.TicketQRCodeDTO;
import pl.edu.wat.student.i9g1s1.mosir.dto.coach.UseTicketResponseDTO;
import pl.edu.wat.student.i9g1s1.mosir.service.auth.AuthService;
import pl.edu.wat.student.i9g1s1.mosir.service.auth.MosirUserDetailsService;
import pl.edu.wat.student.i9g1s1.mosir.service.auth.MosirUserPrincipal;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UseTicketService {

    private final ClientsTicketRepository clientsTicketRepository;
    private final ClientsMembershipCardRepository clientsMembershipCardRepository;
    private final MosirUserDetailsService userDetailsService;

    public UseTicketResponseDTO useTicket(TicketQRCodeDTO ticketQRCodeDTO) throws IllegalStateException{
        if(!validateTicket(ticketQRCodeDTO))
            return new UseTicketResponseDTO(false, "Dane na bilecie nie zgadzają się z danymi w systemie.");
        if(ticketQRCodeDTO.getType().equalsIgnoreCase("SINGLE")) {
            ClientsTicket clientsTicket = clientsTicketRepository.findById(ticketQRCodeDTO.getId()).get();
            if(clientsTicket.getUsed())
                return new UseTicketResponseDTO(false, "Bilet został już wykorzystany.");
            clientsTicket.setUsed(true);
            clientsTicketRepository.save(clientsTicket);
        } else {
            ClientsMembershipCard clientsMembershipCard = clientsMembershipCardRepository.findById(ticketQRCodeDTO.getId()).get();
            if(clientsMembershipCard.getNumberOfUsages() >= clientsMembershipCard.getMembershipCard().getTotalUsages())
                return new UseTicketResponseDTO(false, "Karnet został już wykorzystany maksymalną liczbę razy.");
            clientsMembershipCard.setNumberOfUsages(clientsMembershipCard.getNumberOfUsages() + 1);
            clientsMembershipCardRepository.save(clientsMembershipCard);
        }
        return new UseTicketResponseDTO(true, "Bilet został pomyślnie wykorzystany.");
    }

    private boolean validateTicket(TicketQRCodeDTO ticketQRCodeDTO) throws IllegalStateException{
        MosirUser ticketsUser;
        try {
            ticketsUser = getTicketsUser(ticketQRCodeDTO);
        } catch (UsernameNotFoundException e) {
            return false;
        }
        TicketQRCodeDTO validTicket = getValidTicket(ticketsUser, ticketQRCodeDTO);
        return validTicket.getTotalUsages() == ticketQRCodeDTO.getTotalUsages() &&
                validTicket.getNumberOfUsages() == ticketQRCodeDTO.getNumberOfUsages() &&
                DateUtils.isSameDay(validTicket.getPurchasedAt(), ticketQRCodeDTO.getPurchasedAt()) &&
                DateUtils.isSameDay(validTicket.getValidTill(), ticketQRCodeDTO.getValidTill()) &&
                validTicket.getFirstName().equals(ticketQRCodeDTO.getFirstName()) &&
                validTicket.getLastName().equals(ticketQRCodeDTO.getLastName()) &&
                validTicket.getNationalRegistryNumber().equals(ticketQRCodeDTO.getNationalRegistryNumber()) &&
                validTicket.getEmail().equals(ticketQRCodeDTO.getEmail());
    }

    private TicketQRCodeDTO getValidTicket(MosirUser ticketsUser, TicketQRCodeDTO ticketQRCodeDTO) throws IllegalStateException{
        TicketQRCodeDTO validTicket;
        if(ticketQRCodeDTO.getType().equalsIgnoreCase("SINGLE")) {
            Optional<ClientsTicket> clientsTicket = clientsTicketRepository.findById(ticketQRCodeDTO.getId());
            if(clientsTicket.isEmpty())
                throw new IllegalStateException();
            validTicket = new TicketQRCodeDTO(ticketsUser, clientsTicket.get());
        } else if(ticketQRCodeDTO.getType().equalsIgnoreCase("MULTI")) {
            Optional<ClientsMembershipCard> clientsMembershipCard = clientsMembershipCardRepository.findById(ticketQRCodeDTO.getId());
            if(clientsMembershipCard.isEmpty())
                throw new IllegalStateException();
            validTicket = new TicketQRCodeDTO(ticketsUser, clientsMembershipCard.get());
        } else {
            throw new IllegalStateException();
        }
        return validTicket;
    }

    private MosirUser getTicketsUser(TicketQRCodeDTO ticketQRCodeDTO) throws UsernameNotFoundException{
        MosirUserPrincipal userPrincipal = (MosirUserPrincipal) userDetailsService.loadUserByUsername(ticketQRCodeDTO.getEmail());
        return userPrincipal.getUser();
    }

}
