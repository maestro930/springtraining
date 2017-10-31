package priv.rdo.sdaspring.task07;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.rdo.sdaspring.task02.Animal;

@RestController
public class Task07Controller {

    @GetMapping("task07_exception")
    public Animal throwException() {
        throw new AnimalException();
    }
}
