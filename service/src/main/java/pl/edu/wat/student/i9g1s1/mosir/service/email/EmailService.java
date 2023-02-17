package pl.edu.wat.student.i9g1s1.mosir.service.email;

import com.google.zxing.WriterException;
import com.lowagie.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import pl.edu.wat.student.i9g1s1.mosir.domain.AccountOperationToken;
import pl.edu.wat.student.i9g1s1.mosir.domain.ClientsMembershipCard;
import pl.edu.wat.student.i9g1s1.mosir.domain.ClientsTicket;
import pl.edu.wat.student.i9g1s1.mosir.domain.MosirUser;
import pl.edu.wat.student.i9g1s1.mosir.service.generators.GeneratorUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final GeneratorUtils generatorUtils;
    private final EmailSenderService emailSenderService;

    @Value("${mosir.public-url}")
    private String publicUrl;

    @Async
    public void sendNewAccountMail(MosirUser user, AccountOperationToken token) throws IOException, MessagingException {
        String actionUrl = publicUrl + "/aktywuj-konto?token=" + token.getToken();
        emailSenderService.sendEmail(user.getEmail(), "Witaj w mosirze", generatorUtils.generateHtmlFromTemplate("mail/new_account.html", buildCommonContext(user, token, actionUrl)), null);
    }

    @Async
    public void sendAccountActivationMail(MosirUser user, AccountOperationToken token) throws IOException, MessagingException {
        String actionUrl = publicUrl + "/aktywuj-konto?token=" + token.getToken();
        emailSenderService.sendEmail(user.getEmail(), "Aktywacja konta", generatorUtils.generateHtmlFromTemplate("mail/activate_account.html", buildCommonContext(user, token, actionUrl)), null);
    }

    @Async
    public void sendPasswordResetMail(MosirUser user, AccountOperationToken token) throws IOException, MessagingException {
        String actionUrl = publicUrl + "/zmien-haslo?token=" + token.getToken();
        emailSenderService.sendEmail(user.getEmail(), "Resetowanie hasła", generatorUtils.generateHtmlFromTemplate("mail/password_reset.html", buildCommonContext(user, token, actionUrl)), null);
    }

    @Async
    public void sendPurchaseTicketMail(MosirUser user, ClientsTicket ticket, Map<String, byte[]> attachments) throws IOException, MessagingException, DocumentException, WriterException {;
        emailSenderService.sendEmail(user.getEmail(), "Zakup biletu na zajęcia", generatorUtils.generateHtmlFromTemplate("mail/purchase_ticket.html",
                buildPurchaseTicketCommonContext(user, "SINGLE", ticket.getTicket().getActivityType().getName(), ticket.getTicket().getPrice(), ticket.getPurchasedAt(), ticket.getValidTill())), attachments);
    }

    @Async
    public void sendPurchaseMembershipCardMail(MosirUser user, ClientsMembershipCard membershipCard, Map<String, byte[]> attachments) throws IOException, MessagingException, DocumentException, WriterException {;
        Context context = buildPurchaseTicketCommonContext(user, "MULTI", membershipCard.getMembershipCard().getActivityType().getName(),
                membershipCard.getMembershipCard().getPrice(), membershipCard.getPurchasedAt(), membershipCard.getValidTill());
        context.setVariable("totalUsages", membershipCard.getMembershipCard().getTotalUsages());
        emailSenderService.sendEmail(user.getEmail(), "Zakup karnetu na zajęcia", generatorUtils.generateHtmlFromTemplate("mail/purchase_ticket.html", context), attachments);
    }

    private Context buildCommonContext(MosirUser user, AccountOperationToken token, String actionUrl) throws IOException {
        Context context = new Context();
        context.setVariable("legalName", user.getLegalName());
        context.setVariable("banner", generatorUtils.getEncodedBanner());
        context.setVariable("actionUrl", actionUrl);
        context.setVariable("expirationDate", token.getExpirationDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        return context;
    }

    private Context buildPurchaseTicketCommonContext(MosirUser user, String ticketType, String activityType, BigDecimal price, LocalDateTime purchasedAt, LocalDateTime validTill) throws IOException {
        Context context = new Context();
        context.setVariable("ticketType", ticketType);
        context.setVariable("legalName", user.getLegalName());
        context.setVariable("banner", generatorUtils.getEncodedBanner());
        context.setVariable("activityType", activityType);
        context.setVariable("price", price);
        context.setVariable("purchasedAtDate", purchasedAt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        context.setVariable("purchasedAtTime", purchasedAt.format(DateTimeFormatter.ofPattern("HH:mm")));
        context.setVariable("validTill", validTill.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        return context;
    }
}
