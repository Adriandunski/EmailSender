package akademia.emailsender.mail.services;

import akademia.emailsender.mail.EmailSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class EmailSenderService implements EmailSender {

    private JavaMailSender javaMailSender;

    @Value("${email.reply}")
    String replyTo;

    @Value("${email.from}")
    String from;

    private static final Logger logger  = Logger.getLogger(EmailSenderService.class.getName());

    public EmailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
        logger.log(Level.INFO, "EmailSenderService run!");
    }

    @Override
    public void sendEmail(String to, String subject, String content) {
        MimeMessage mail = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(to);
            helper.setReplyTo(replyTo);
            helper.setFrom(from);
            helper.setSubject(subject);
            helper.setText(content, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(mail);
    }
}
