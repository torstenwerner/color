package de.twerner42.color;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ColorApplication {
    public static void main(String[] args) {
        if (args.length != 1) {
            SpringApplication.run(ColorApplication.class, args);
            return;
        }

        final Integer threadCount = Integer.valueOf(args[0]);
        final Performance performance = new Performance(threadCount);
        performance.test();
        System.exit(0);
    }
}
