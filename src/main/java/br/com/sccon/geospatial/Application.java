package br.com.sccon.geospatial;

import br.com.sccon.geospatial.controllers.person.controller.contracts.PersonRequest;
import br.com.sccon.geospatial.domain.person.services.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.time.LocalDate;

@Slf4j
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class Application {

    private final PersonService service;

    public Application(PersonService service){

        this.service = service;
        this.service.addPerson(new PersonRequest(1, "Jose Silva", LocalDate.of(2000, 4, 06), LocalDate.of(2020, 5, 10)));
        this.service.addPerson(new PersonRequest(2, "Maria Oliveira", LocalDate.of(1990, 8, 25), LocalDate.of(2015, 6, 15)));
        this.service.addPerson(new PersonRequest(3, "Herbert Santos", LocalDate.of(1978, 12, 30), LocalDate.of(2008, 11, 10)));

    }
    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

}
