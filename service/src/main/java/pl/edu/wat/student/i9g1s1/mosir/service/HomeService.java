package pl.edu.wat.student.i9g1s1.mosir.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wat.student.i9g1s1.mosir.*;
import pl.edu.wat.student.i9g1s1.mosir.domain.ActivityType;
import pl.edu.wat.student.i9g1s1.mosir.dto.guest.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HomeService {

    private final ActivityTypeRepository activityTypeRepository;
    private final CoachRepository coachRepository;
    private final TicketRepository ticketRepository;
    private final MembershipCardRepository membershipCardRepository;
    private final HallRepository hallRepository;
    private final SportsEquipmentRepository sportsEquipmentRepository;
    private final StaticFilesService staticFilesService;


    public List<ActivityTypeDTO> getActivityTypes() {
        return activityTypeRepository.findAll().stream()
                .map(activityType -> new ActivityTypeDTO(activityType,
                        staticFilesService.getImageUrl("activity_type", activityType.getSlug())))
                .collect(Collectors.toList());
    }

    public List<CoachDTO> getCoaches() {
        return coachRepository.findAll().stream()
                .map(coach -> new CoachDTO(coach, staticFilesService.getImageUrl("coach", coach.getSlug())))
                .collect(Collectors.toList());
    }

    public List<TicketDTO> getTickets() {
        return ticketRepository.findAll().stream().map(ticket -> new TicketDTO(ticket)).collect(Collectors.toList());
    }

    public List<MembershipCardDTO> getMembershipCards() {
        return membershipCardRepository.findAll().stream()
                .map(membershipCard -> new MembershipCardDTO(membershipCard)).collect(Collectors.toList());
    }

    public List<HallDTO> getHalls() {
        return hallRepository.findAll().stream()
                .map(hall -> new HallDTO(hall, staticFilesService.getImageUrl("hall", hall.getSlug())))
                .collect(Collectors.toList());
    }

    public List<SportsEquipmentDTO> getSportsEquipments() {
        return sportsEquipmentRepository.findAll().stream()
                .map(sportsEquipment -> new SportsEquipmentDTO(sportsEquipment,
                        staticFilesService.getImageUrl("sport_equipment", sportsEquipment.getSlug())))
                .collect(Collectors.toList());
    }
}
