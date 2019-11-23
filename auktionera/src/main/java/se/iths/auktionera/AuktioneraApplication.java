package se.iths.auktionera;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class AuktioneraApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuktioneraApplication.class, args);
    }

}
