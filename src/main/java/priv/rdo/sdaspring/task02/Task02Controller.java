package priv.rdo.sdaspring.task02;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

@RestController
public class Task02Controller {
    @GetMapping(value = "task02", produces = APPLICATION_JSON_VALUE)
    public Animal task02() {
        return new Animal("Azor", 666);
    }

    @GetMapping(value = "task02.1", produces = APPLICATION_XML_VALUE)
    public Animal task02_1() {
        return new Animal("Azor", 666);
    }

    @GetMapping(value = "task02_3")
    public Animal task02_2() {
        return new Animal("Azor", 666);
    }

    @GetMapping(value = "task02_bonus1")
    public Animal animal3(@RequestHeader("Accept") String acceptHeader, @RequestHeader HttpHeaders headers) {
        System.out.println("acceptHeader: " + acceptHeader);
        System.out.println("all headers: ");
        headers.forEach((key, value) -> System.out.println(key + ": " + value));
        return new Animal(acceptHeader, 3);
    }

    @GetMapping(value = "task02_bonus2")
    public List<Animal> zad4() {
        List<Animal> result = new ArrayList<>();
        result.add(new Animal("Maurycy", 3));
        result.add(new Animal("Ancymon", 15));
        return result;
    }
}
