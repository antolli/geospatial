package br.com.sccon.geospatial.config;

import br.com.sccon.geospatial.domain.person.entities.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AppConfig {

    @Bean
    public Map<Integer, Person> personMap() {
        return new HashMap<>();
    }
}