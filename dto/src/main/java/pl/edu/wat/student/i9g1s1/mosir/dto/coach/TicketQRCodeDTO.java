package pl.edu.wat.student.i9g1s1.mosir.dto.coach;

import lombok.Getter;
import lombok.Setter;
import pl.edu.wat.student.i9g1s1.mosir.domain.ClientsMembershipCard;
import pl.edu.wat.student.i9g1s1.mosir.domain.ClientsTicket;
import pl.edu.wat.student.i9g1s1.mosir.domain.MosirUser;
import pl.edu.wat.student.i9g1s1.mosir.dto.DiscountTypeDTO;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.Date;

@Getter
@Setter
public class TicketQRCodeDTO {

    private final String QRINFO = "MOSIR_TICKET_QR";
    private long id;
    private String type;
    private BigDecimal price;
    private String activityType;
    private DiscountTypeDTO discountType;
    private Long totalUsages;
    private Long numberOfUsages;
    private Date purchasedAt;
    private Date validTill;
    private String firstName;
    private String lastName;
    private String nationalRegistryNumber;
    private String email;
    private String phoneNumber;

    public TicketQRCodeDTO() { }

    public TicketQRCodeDTO(MosirUser user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.nationalRegistryNumber = user.getNationalRegistryNumber();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
    }

    public TicketQRCodeDTO(MosirUser user, ClientsTicket clientsTicket) {
        this(user);
        this.id = clientsTicket.getId();
        this.type = "SINGLE";
        this.price = clientsTicket.getTicket().getPrice().multiply(clientsTicket.getDiscountType().getValue());
        this.activityType = clientsTicket.getTicket().getActivityType().getName();
        this.discountType = new DiscountTypeDTO(clientsTicket.getDiscountType());
        this.totalUsages = 1l;
        this.numberOfUsages = clientsTicket.getUsed() ? 1l : 0l;
        this.purchasedAt = Date.from(clientsTicket.getPurchasedAt().atZone(ZoneId.systemDefault()).toInstant());
        this.validTill =  Date.from(clientsTicket.getValidTill().atZone(ZoneId.systemDefault()).toInstant());
    }

    public TicketQRCodeDTO(MosirUser user, ClientsMembershipCard clientsMembershipCard) {
        this(user);
        this.id = clientsMembershipCard.getId();
        this.type = "MULTI";
        this.price = clientsMembershipCard.getMembershipCard().getPrice().multiply(clientsMembershipCard.getDiscountType().getValue());
        this.activityType = clientsMembershipCard.getMembershipCard().getActivityType().getName();
        this.discountType = new DiscountTypeDTO(clientsMembershipCard.getDiscountType());
        this.totalUsages = clientsMembershipCard.getMembershipCard().getTotalUsages();
        this.numberOfUsages = clientsMembershipCard.getNumberOfUsages();
        this.purchasedAt =  Date.from(clientsMembershipCard.getPurchasedAt().atZone(ZoneId.systemDefault()).toInstant());
        this.validTill =  Date.from(clientsMembershipCard.getValidTill().atZone(ZoneId.systemDefault()).toInstant());
    }
}
