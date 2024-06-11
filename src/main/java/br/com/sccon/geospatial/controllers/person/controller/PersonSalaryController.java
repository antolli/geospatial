package br.com.sccon.geospatial.controllers.person.controller;

import br.com.sccon.geospatial.domain.person.services.PersonAgeService;
import br.com.sccon.geospatial.domain.person.services.PersonSalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
@Validated
public class PersonSalaryController {

    @Autowired
    private PersonSalaryService service;
    @GetMapping("/{id}/salary")
    public ResponseEntity<Double> getSalary(@PathVariable("id") int id, @RequestParam("output") String output) {
        Double salary = service.calcularSalario(id, output);
        return ResponseEntity.ok(salary);
    }
}
