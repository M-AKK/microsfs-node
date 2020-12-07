package net.wenz.service.fsnode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication()
@EnableScheduling
public class FsDataNodeApplication {
    public static void main(String[] args) {
        SpringApplication.run(FsDataNodeApplication.class, args);
    }
}
