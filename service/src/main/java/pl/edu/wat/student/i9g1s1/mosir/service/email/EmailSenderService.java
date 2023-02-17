package pl.edu.wat.student.i9g1s1.mosir.service.email;

import javax.mail.MessagingException;
import javax.mail.util.ByteArrayDataSource;
import java.io.IOException;
import java.util.Map;

public interface EmailSenderService {
    void sendEmail(String to, String subject, String text, Map<String, byte[]> attachments) throws MessagingException, IOException;
}
