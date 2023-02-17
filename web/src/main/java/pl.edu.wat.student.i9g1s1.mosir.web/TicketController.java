package pl.edu.wat.student.i9g1s1.mosir.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.wat.student.i9g1s1.mosir.dto.GenericListResponseDTO;
import pl.edu.wat.student.i9g1s1.mosir.dto.coach.TicketQRCodeDTO;
import pl.edu.wat.student.i9g1s1.mosir.dto.coach.UseTicketResponseDTO;
import pl.edu.wat.student.i9g1s1.mosir.dto.user.BuyTicketDTO;
import pl.edu.wat.student.i9g1s1.mosir.dto.user.MultiTicketDTO;
import pl.edu.wat.student.i9g1s1.mosir.dto.user.SingleTicketDTO;
import pl.edu.wat.student.i9g1s1.mosir.dto.user.TicketListDTO;
import pl.edu.wat.student.i9g1s1.mosir.service.TicketService;
import pl.edu.wat.student.i9g1s1.mosir.service.UseTicketService;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/ticket")
@Slf4j
public class TicketController {

    private final TicketService ticketService;
    private final UseTicketService useTicketService;

    @GetMapping("/single")
    public GenericListResponseDTO<SingleTicketDTO> getTickets(@RequestParam(defaultValue = "0") Integer page,
                                                              @RequestParam(defaultValue = "8") Integer size,
                                                              @RequestParam(defaultValue = "DESC") String order)
    {
        if(!order.equalsIgnoreCase("DESC") && !order.equalsIgnoreCase("ASC"))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        return ticketService.getTickets(PageRequest.of(page, size,
                Sort.by(order.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC, "purchasedAt")));
    }

    @GetMapping("/multi")
    public GenericListResponseDTO<MultiTicketDTO> getMembershipCards(@RequestParam(defaultValue = "0") Integer page,
                                                   @RequestParam(defaultValue = "8") Integer size,
                                                   @RequestParam(defaultValue = "DESC") String order)
    {
        if(!order.equalsIgnoreCase("DESC") && !order.equalsIgnoreCase("ASC"))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        return ticketService.getMembershipCards(PageRequest.of(page, size,
                Sort.by(order.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC, "purchasedAt")));
    }

    @PostMapping
    public TicketListDTO buyTicket(@RequestBody BuyTicketDTO buyTicketDTO) {
        try {
            if (buyTicketDTO.getTicketType().equalsIgnoreCase("SINGLE")) {
                return ticketService.addTicket(buyTicketDTO);
            } else if (buyTicketDTO.getTicketType().equalsIgnoreCase("MULTI")) {
                return ticketService.addMembershipCard(buyTicketDTO);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping
    public ResponseEntity<UseTicketResponseDTO> useTicket(@RequestBody TicketQRCodeDTO ticketQRCodeDTO) {
        try {
            return ResponseEntity.ok().body(useTicketService.useTicket(ticketQRCodeDTO));
        } catch (IllegalStateException e) {
            return ResponseEntity.ok().body(new UseTicketResponseDTO(false, "Przesłany bilet nie istnieje."));
        } catch (Exception e) {
            return ResponseEntity.ok().body(new UseTicketResponseDTO(false, "Coś poszło nie tak. Spróbuj ponownie później."));
        }

    }
}
