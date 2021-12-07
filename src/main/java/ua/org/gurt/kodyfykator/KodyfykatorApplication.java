package ua.org.gurt.kodyfykator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class KodyfykatorApplication {
    public static void main(String[] args) {
        SpringApplication.run(KodyfykatorApplication.class, args);
    }
}
