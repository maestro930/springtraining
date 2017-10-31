package priv.rdo.sdaspring.task06;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import priv.rdo.sdaspring.task02.Animal;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
public class AnimalsRepositoryTest {
    @Autowired
    private AnimalsRepository animalsRepository;

    @Before
    public void setUp() throws Exception {
        animalsRepository.save(new Animal("Piwko", 1));
        animalsRepository.save(new Animal("Piwko", 1));
        animalsRepository.save(new Animal("Winko", 3));
        animalsRepository.save(new Animal("Stefan", 3));
    }

    @Test
    public void findByName() throws Exception {
        //when
        List<Animal> piwka = animalsRepository.findByName("Piwko");

        //then
        assertThat(piwka).isNotEmpty();
        assertThat(piwka).hasSize(2);
        assertThat(piwka.get(0).getAge()).isEqualTo(1);
    }

    @Test
    public void findByNameWithCustomQuery() throws Exception {
        //when
        List<Animal> piwka = animalsRepository.findByNameWithCustomQuery("Piwko");

        //then
        assertThat(piwka).isNotEmpty();
        assertThat(piwka).hasSize(2);
        assertThat(piwka.get(0).getAge()).isEqualTo(1);
        assertThat(piwka.get(0).getName()).isEqualTo("Piwko");
    }

    @Test
    public void findByNameWithCustomQuery_negative() throws Exception {
        //when
        List<Animal> piwka = animalsRepository.findByNameWithCustomQuery("fafa");

        //then
        assertThat(piwka).isEmpty();
    }

}