package priv.rdo.sdaspring.task04

import priv.rdo.sdaspring.task02.Animal
import priv.rdo.sdaspring.task06.AnimalsRepository
import spock.lang.Specification

class AnimalServiceSpockTest extends Specification {
    AnimalsRepository animalsRepository = Mock(AnimalsRepository.class) {
        findOne(_) >> new Animal("Azazel", 666)
    }
    AnimalService sut = new AnimalService(animalsRepository)

    def "should find Azazel"() {
        when:
            Animal found = sut.findOne(1)

        then:
            found.age == 666
            found.name == "Azazel"
    }
}
