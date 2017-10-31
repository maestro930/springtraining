package priv.rdo.sdaspring.task06;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import priv.rdo.sdaspring.task02.Animal;

import java.util.List;

@Repository
public interface AnimalsRepository extends JpaRepository<Animal, Long> {
    List<Animal> findByName(String name);

    List<Animal> findByAge(int age);

    List<Animal> findByNameAndAge(String name, int age);

    List<Animal> findFirst3ByOrderByNameDesc();

    @Query("select animal from Animal animal where animal.name = ?1")
    List<Animal> findByNameWithCustomQuery(String name);
}
