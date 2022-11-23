package pl.edu.wat.student.i9g1s1.mosir.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.wat.student.i9g1s1.mosir.dto.user.BuyTicketDTO;
import pl.edu.wat.student.i9g1s1.mosir.dto.user.TicketDTO;
import pl.edu.wat.student.i9g1s1.mosir.dto.user.TicketListDTO;
import pl.edu.wat.student.i9g1s1.mosir.service.TicketService;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    private final TicketService ticketService;

    @GetMapping
    public TicketListDTO getTickets() {
        return ticketService.getTickets();
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
}
