package de.twerner42.color;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class WebController {
    @RequestMapping(value = "/angles", method = RequestMethod.GET)
    public List<Integer> angles() {
        return AngleSet.generate().boxed().collect(Collectors.toList());
    }
}
