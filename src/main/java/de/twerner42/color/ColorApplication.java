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
        final AngleSet angleSet = new AngleSet();
        angleSet.add(0);
        angleSet.getHoles()
                .map(angleSet::sum)
                .forEach(System.out::println);
    }
}
