package notification.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class
})
public class ProxyNodeApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProxyNodeApplication.class, args);
    }
}
