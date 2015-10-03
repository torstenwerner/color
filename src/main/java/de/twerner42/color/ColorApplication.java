package de.twerner42.color;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.PrintWriter;

@SpringBootApplication
public class ColorApplication implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public static void main(String[] args) {
        SpringApplication.run(ColorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try (final PrintWriter writer = new PrintWriter("/tmp/color.csv")) {
            AngleSet.generate().forEach(writer::println);
        }
    }
}
