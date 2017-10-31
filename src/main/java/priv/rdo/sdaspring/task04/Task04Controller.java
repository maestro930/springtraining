package priv.rdo.sdaspring.task04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.rdo.sdaspring.task02.Animal;

@RestController
public class Task04Controller {
    private final AnimalService animalService;

    @Autowired
    public Task04Controller(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping(value = "task04")
    public Animal task04() {
        return animalService.findOne(1L);
    }
}
