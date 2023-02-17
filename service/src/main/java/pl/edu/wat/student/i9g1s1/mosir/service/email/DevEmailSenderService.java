package pl.edu.wat.student.i9g1s1.mosir.service.email;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import java.util.Map;

@Component
@Profile("dev")
@RequiredArgsConstructor
public class DevEmailSenderService implements EmailSenderService {

    private final JavaMailSender mailSender;

    @Value("${mosir.email.address}")
    private String emailSenderAddress;

    @Override
    public void sendEmail(String to, String subject, String text, Map<String, byte[]> attachments) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(emailSenderAddress);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);
        if(attachments != null && !attachments.isEmpty()) {
            for(String name : attachments.keySet()) {
                helper.addAttachment(name, new ByteArrayDataSource(attachments.get(name), "application/pdf;charset=UTF-8"));
            }
        }
        mailSender.send(message);
    }
}
