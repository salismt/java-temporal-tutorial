package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneOffset;
import java.util.Locale;
import java.util.TimeZone;

@SpringBootApplication
@RestController
public class Main {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneOffset.UTC));
        Locale.setDefault(Locale.ENGLISH);
        SpringApplication.run(Main.class, args);
    }
}
