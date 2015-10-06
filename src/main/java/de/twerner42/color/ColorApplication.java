package de.twerner42.color;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ColorApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(ColorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (args.length != 1) {
            return;
        }

        final Integer threadCount = Integer.valueOf(args[0]);
        final Performance performance = new Performance(threadCount);
        performance.test();
        System.exit(0);
    }
}
