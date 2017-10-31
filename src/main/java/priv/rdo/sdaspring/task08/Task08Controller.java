package priv.rdo.sdaspring.task08;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import priv.rdo.sdaspring.task02.Animal;
import priv.rdo.sdaspring.task06.AnimalsRepository;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("task08_animals")
public class Task08Controller {
    private final AnimalsRepository animalsRepository;

    @Autowired
    public Task08Controller(AnimalsRepository animalsRepository) {
        this.animalsRepository = animalsRepository;
    }

    @PostMapping
    public ResponseEntity<Void> task08_create(@Valid @RequestBody Animal animal) {
        System.out.println(animal);
        Animal save = animalsRepository.save(animal);
        System.out.println(save);

        return ResponseEntity.created(URI.create("localhost:8080/task08_animals/" + save.getId())).build();
        //we do not build location like that normally, will get to that later on
    }

    @PostMapping("batch")
    public ResponseEntity<Void> task08_create_many(@RequestBody List<Animal> animals) {
        List<Animal> savedAnimals = animalsRepository.save(animals);

        return ResponseEntity.created(URI.create("bla")).build();
        //this gets weird, usually we don't create multiple entries
    }

    @GetMapping("{id}")
    public ResponseEntity<Animal> task08_animals_find(@PathVariable long id) {
        Animal animal = animalsRepository.findOne(id);
        if (animal == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(animal);
    }

    @GetMapping
    @ApiOperation(value = "find all animals")
    public List<Animal> task08_findAll() {
        return animalsRepository.findAll();
    }

    @GetMapping("findByName")
    public List<Animal> task08_findByName(@RequestParam String name) {
        return animalsRepository.findByName(name);
    }

    @GetMapping("findByNameCustomQuery")
    public ResponseEntity<List<Animal>> task08_findByNameCustomQuery(@RequestParam String name) {
        List<Animal> animals = animalsRepository.findByNameWithCustomQuery(name);
        if (animals.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(animals);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> task08_animals_delete(@PathVariable long id) {
        Animal animal = animalsRepository.findOne(id);
        if (animal == null) {
            return ResponseEntity.notFound().build();
        }

        animalsRepository.delete(id);
        return ResponseEntity.noContent().build();
    }
}
