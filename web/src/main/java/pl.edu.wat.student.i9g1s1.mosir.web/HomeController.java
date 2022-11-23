package pl.edu.wat.student.i9g1s1.mosir.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.student.i9g1s1.mosir.dto.guest.*;
import pl.edu.wat.student.i9g1s1.mosir.service.HomeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/home")
public class HomeController {

    private final HomeService homeService;

    @GetMapping("/activity-types")
    public List<ActivityTypeDTO> getActivityTypes() {
        return homeService.getActivityTypes();
    }

    @GetMapping("/coaches")
    public List<CoachDTO> getCoaches() {
        return homeService.getCoaches();
    }

    @GetMapping("/tickets")
    public List<TicketDTO> getTickets() {
        return homeService.getTickets();
    }

    @GetMapping("/membership-cards")
    public List<MembershipCardDTO> getMembershipCards() {
        return homeService.getMembershipCards();
    }

    @GetMapping("/halls")
    public List<HallDTO> getHalls() {
        return homeService.getHalls();
    }

    @GetMapping("/sports-equipments")
    public List<SportsEquipmentDTO> getSportsEquipments() {
        return homeService.getSportsEquipments();
    }

}
