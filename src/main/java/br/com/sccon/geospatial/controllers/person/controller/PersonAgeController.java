package br.com.sccon.geospatial.controllers.person.controller;

import br.com.sccon.geospatial.domain.person.services.PersonAgeService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
@Validated
public class PersonAgeController {

    @Autowired
    private PersonAgeService service;
    @GetMapping("/{id}/age")
    public ResponseEntity<String> getPersonAge(@PathVariable @NotNull(message = "O campo 'id' é obrigatorio.") Integer id,
                                               @RequestParam @NotNull(message = "O campo 'output' é obrigatorio.") String output) {
        String response = service.calculatePersonAge(id, output);
        return ResponseEntity.ok(response);
    }
}
