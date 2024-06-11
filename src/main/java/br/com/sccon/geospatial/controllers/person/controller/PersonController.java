package br.com.sccon.geospatial.controllers.person.controller;

import br.com.sccon.geospatial.controllers.person.controller.contracts.PersonRequest;
import br.com.sccon.geospatial.controllers.person.controller.contracts.PersonResponse;
import br.com.sccon.geospatial.domain.person.services.PersonService;
import jakarta.validation.constraints.NotNull;
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
    public ResponseEntity<List<PersonResponse>> list() {

        List<PersonResponse> response = service.getAllPersonSortedByName();

        if (response == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping(value ="/{personId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonResponse> getById(@PathVariable @NotNull(message = "O campo 'personId' é obrigatorio.") Integer personId) {

        PersonResponse response = service.getById(personId);

        return new ResponseEntity(response, HttpStatus.OK);
    }
    @PutMapping(value ="/{personId}")
    public ResponseEntity<PersonResponse> update(@PathVariable @NotNull(message = "O campo 'personId' é obrigatorio.") Integer personId,
                                                 @RequestBody @Valid PersonRequest request) {
        PersonResponse response = this.service.updatePerson(request, personId);
        return ResponseEntity.ok().body(response);
    }
    @PatchMapping(value ="/{personId}")
    public ResponseEntity<PersonResponse> updateName(@PathVariable @NotNull(message = "O campo 'personId' é obrigatorio.") Integer personId,
                                                     @RequestParam(required = false) String name) {
        PersonResponse response = this.service.updateNamePerson(personId, name);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping(value = "/{personId}")
    public ResponseEntity<Void> delete(@PathVariable @NotNull(message = "O campo 'personId' é obrigatorio.") Integer personId) {
        this.service.removePerson(personId);
        return ResponseEntity.noContent().build();
    }


}
