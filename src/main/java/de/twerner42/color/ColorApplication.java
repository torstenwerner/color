package de.twerner42.color;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Comparator;
import java.util.Optional;

@SpringBootApplication
public class ColorApplication implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public static void main(String[] args) {
        SpringApplication.run(ColorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        final AngleSet angleSet = new AngleSet();
        angleSet.add(new Angle(0));
        final Angle nextAngle = angleSet.getHoles()
                .map(angleSet::sum)
                .max(Comparator.<AngleValue>naturalOrder())
                .get().getAngle();
        logger.info(nextAngle.toString());
        angleSet.add(nextAngle);
        final Angle nextAngle2 = angleSet.getHoles()
                .map(angleSet::sum)
                .max(Comparator.<AngleValue>naturalOrder())
                .get().getAngle();
        logger.info(nextAngle2.toString());
    }
}
