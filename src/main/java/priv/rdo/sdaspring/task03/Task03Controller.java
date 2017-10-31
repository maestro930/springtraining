package priv.rdo.sdaspring.task03;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import priv.rdo.sdaspring.task02.Animal;

import java.net.URI;
import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.BAD_GATEWAY;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
public class Task03Controller {

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(value = "task03_0")
    public void task03_0(@RequestBody Animal animal) {
        System.out.println(animal);
    }

    @PostMapping(value = "task03_1")
    public ResponseEntity<Void> task03_1(@RequestBody Animal animal) {
        System.out.println(animal);
        return ResponseEntity.created(
                URI.create("lhsgfsjd")).build();
    }

    @PostMapping(value = "task03_2")
    public ResponseEntity<Animal> task03_2(@RequestBody Animal animal) {
        System.out.println(animal);
        return ResponseEntity
                .status(CREATED)
                .header("Last-Modified", LocalDateTime.now().toString())
                .body(animal);
    }

    @PostMapping(value = "task03_bonus")
    public ResponseEntity<Animal> task03_bonus(@RequestBody Animal animal) {
        System.out.println(animal);
        if (animal.getAge() >= 5 ){
            return ResponseEntity
                    .status(CREATED)
                    .header("Last-Modified", LocalDateTime.now().toString())
                    .body(animal);
        } else {
            return ResponseEntity
                    .status(BAD_GATEWAY)
                    .header("Last-Modified", LocalDateTime.now().toString())
                    .body(animal);
        }
    }
}
