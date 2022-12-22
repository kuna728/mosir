package pl.edu.wat.student.i9g1s1.mosir.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import pl.edu.wat.student.i9g1s1.mosir.domain.AccountOperationToken;
import pl.edu.wat.student.i9g1s1.mosir.domain.MosirUser;
import pl.edu.wat.student.i9g1s1.mosir.service.generators.GeneratorUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final GeneratorUtils generatorUtils;

    @Value("${mosir.public-url}")
    private String publicUrl;

    private void sendEmail(
            String to, String subject, String text) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, false);
        helper.setFrom("noreply@mosir.pl");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);
        mailSender.send(message);
    }

    @Async
    public void sendNewAccountMail(MosirUser user, AccountOperationToken token) throws IOException, MessagingException {
        String actionUrl = publicUrl + "/api/auth/activate?token=" + token.getToken();
        sendEmail(user.getEmail(), "Witaj w mosirze", generatorUtils.generateHtmlFromTemplate("mail/new_account.html", buildCommonContext(user, token, actionUrl)));
    }

    @Async
    public void sendPasswordResetMail(MosirUser user, AccountOperationToken token) throws IOException, MessagingException {
        String actionUrl = publicUrl + "/api/auth/reset?token=" + token.getToken();
        sendEmail(user.getEmail(), "Resetowanie hasła", generatorUtils.generateHtmlFromTemplate("mail/password_reset.html", buildCommonContext(user, token, actionUrl)));
    }

    private Context buildCommonContext(MosirUser user, AccountOperationToken token, String actionUrl) throws IOException {
        Context context = new Context();
        context.setVariable("legalName", user.getLegalName());
        context.setVariable("banner", generatorUtils.getEncodedBanner());
        context.setVariable("actionUrl", actionUrl);
        context.setVariable("expirationDate", token.getExpirationDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        return context;
    }
}
