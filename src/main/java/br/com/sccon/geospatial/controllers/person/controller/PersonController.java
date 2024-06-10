package br.com.sccon.geospatial.controllers.person.controller;

import br.com.sccon.geospatial.controllers.person.controller.contracts.PersonRequest;
import br.com.sccon.geospatial.controllers.person.controller.contracts.PersonResponse;
import br.com.sccon.geospatial.domain.person.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
@Validated
public class PersonController {

    @Autowired
    private PersonService service;


    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PersonResponse> add(@RequestBody @Valid PersonRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(request));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PersonResponse>> read() {

        List<PersonResponse> response = service.getAllPersonSortedByName();

        if (response == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PutMapping(value ="/{peopleId}")
    public ResponseEntity<PersonResponse> update(@PathVariable Integer peopleId, @RequestBody PersonRequest request) {
        PersonResponse response = this.service.updatePerson(request, peopleId);
        return ResponseEntity.ok().body(response);
    }


    @DeleteMapping(value = "/{peopleId}")
    public ResponseEntity<Void> delete(@PathVariable Integer peopleId) {
        this.service.removePerson(peopleId);
        return ResponseEntity.noContent().build();
    }


}
