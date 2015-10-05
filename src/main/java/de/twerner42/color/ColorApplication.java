package de.twerner42.color;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class ColorApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(ColorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        final ObjectMapper jsonMapper = new ObjectMapper();
        final OutputStream outputStream = new FileOutputStream("/tmp/color.json");
        final List<Integer> angles = AngleSet.generate().boxed().collect(Collectors.toList());
        jsonMapper.writeValue(outputStream, angles);
    }
}
