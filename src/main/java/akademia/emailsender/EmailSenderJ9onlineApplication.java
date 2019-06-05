package akademia.emailsender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:config.properties")
public class EmailSenderJ9onlineApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmailSenderJ9onlineApplication.class, args);
    }

}
