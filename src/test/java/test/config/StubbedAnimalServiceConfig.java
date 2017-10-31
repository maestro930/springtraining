package test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import priv.rdo.sdaspring.task02.Animal;
import priv.rdo.sdaspring.task04.AnimalService;

@Configuration
public class StubbedAnimalServiceConfig {
    @Bean
    @Primary
    public AnimalService stubbedAnimalService() {
        return new AnimalService(null) {
            @Override
            public Animal findOne(Long id) {
                return new Animal("Azazel", 666);
            }
        };
    }
}
