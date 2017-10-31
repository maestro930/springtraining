package priv.rdo.sdaspring.task04;

import org.springframework.stereotype.Service;
import priv.rdo.sdaspring.task02.Animal;
import priv.rdo.sdaspring.task06.AnimalsRepository;

@Service
public class AnimalService {
    private final AnimalsRepository animalsRepository;

    public AnimalService(AnimalsRepository animalsRepository) {
        this.animalsRepository = animalsRepository;
    }

    public Animal findOne(Long id) {
        return animalsRepository.findOne(id);
    }
}
