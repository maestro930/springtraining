package priv.rdo.sdaspring.task06;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import priv.rdo.sdaspring.task02.Animal;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("task06_animals")
public class Task06Controller {
    private final AnimalsRepository animalsRepository;

    @Autowired
    public Task06Controller(AnimalsRepository animalsRepository) {
        this.animalsRepository = animalsRepository;
    }

    @PostMapping
    public ResponseEntity<Void> task06_create(@Valid @RequestBody Animal animal) {
        System.out.println(animal);
        Animal save = animalsRepository.save(animal);
        System.out.println(save);

        return ResponseEntity.created(URI.create("localhost:8080/task06_animals/" + save.getId())).build();
        //we do not build location like that normally, will get to that later on
    }

    @PostMapping("batch")
    public ResponseEntity<Void> task06_create_many(@RequestBody List<Animal> animals) {
        List<Animal> savedAnimals = animalsRepository.save(animals);

        return ResponseEntity.created(URI.create("bla")).build();
        //this gets weird, usually we don't create multiple entries
    }

    @GetMapping("{id}")
    public ResponseEntity<Animal> task06_animals_find(@PathVariable long id) {
        Animal animal = animalsRepository.findOne(id);
        if (animal == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(animal);
    }

    @GetMapping
    public List<Animal> task06_findAll() {
        return animalsRepository.findAll();
    }

    @GetMapping("findByName")
    public List<Animal> task06_findByName(@RequestParam String name) {
        return animalsRepository.findByName(name);
    }

    @GetMapping("findByNameCustomQuery")
    public ResponseEntity<List<Animal>> task06_findByNameCustomQuery(@RequestParam String name) {
        List<Animal> animals = animalsRepository.findByNameWithCustomQuery(name);
        if (animals.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(animals);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> task06_animals_delete(@PathVariable long id) {
        Animal animal = animalsRepository.findOne(id);
        if (animal == null) {
            return ResponseEntity.notFound().build();
        }

        animalsRepository.delete(id);
        return ResponseEntity.noContent().build();
    }
}
