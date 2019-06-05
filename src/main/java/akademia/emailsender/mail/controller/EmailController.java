package akademia.emailsender.mail.controller;

import akademia.emailsender.mail.EmailSender;
import akademia.emailsender.mail.model.MyEmail;
import akademia.emailsender.mail.services.EmailSenderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Controller // sluży do obsługi widoku aplikacji
public class EmailController {

    private final EmailSender emailSender;
    private final TemplateEngine templateEngine;

    public EmailController(EmailSenderService emailSender, TemplateEngine templateEngine) {
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/send")
    public String sendMail(@ModelAttribute MyEmail myEmail) {
        Context context = new Context();
        context.setVariable("body", myEmail.getBody());
        String templateEmail = templateEngine.process("template-email", context);
        //emailSender.sendEmail(myEmail.getAddress(), myEmail.getSubject(), templateEmail);
        emailSender.sendEmail(myEmail.getAddress(), myEmail.getSubject(), myEmail.getBody());
        return "index";
    }

    @GetMapping("/sender")
    public String sender() {
        return "sender";
    }
}
