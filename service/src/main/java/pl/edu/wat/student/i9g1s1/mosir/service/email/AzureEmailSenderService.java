package pl.edu.wat.student.i9g1s1.mosir.service.email;

import com.azure.communication.email.EmailClient;
import com.azure.communication.email.EmailClientBuilder;
import com.azure.communication.email.models.*;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.util.ByteArrayDataSource;
import java.io.IOException;
import java.util.*;

@Component
@Profile("prod")
public class AzureEmailSenderService implements EmailSenderService {

    @Value("${mosir.email.connectionString}")
    private String connectionString;

    @Value("${mosir.email.address}")
    private String emailSenderAddress;

    @Override
    public void sendEmail(String to, String subject, String text, Map<String, byte[]> attachments) throws MessagingException, IOException {
        final EmailClient emailClient = new EmailClientBuilder().connectionString(connectionString).buildClient();
        EmailContent content = new EmailContent(subject);
        content.setHtml(text);
        EmailAddress address = new EmailAddress(to);
        EmailMessage message = new EmailMessage(emailSenderAddress, content);
        message.setRecipients(new EmailRecipients(List.of(address)));
        if(attachments != null && !attachments.isEmpty()) {
            List<EmailAttachment> attachmentList = new ArrayList<>();
            for (String name : attachments.keySet()) {
                attachmentList.add(new EmailAttachment(name, EmailAttachmentType.PDF, Base64.getEncoder().encodeToString(attachments.get(name))));
            }
            message.setAttachments(attachmentList);
        }
        emailClient.send(message);
    }
}
