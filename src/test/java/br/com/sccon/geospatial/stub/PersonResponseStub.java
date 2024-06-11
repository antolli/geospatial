package br.com.sccon.geospatial.stub;

import br.com.sccon.geospatial.controllers.person.controller.contracts.PersonRequest;
import br.com.sccon.geospatial.controllers.person.controller.contracts.PersonResponse;

import java.time.LocalDate;

public class PersonResponseStub {

    public static PersonResponse getPersonResponse(){
        return PersonResponse.builder()
                .nome("José Silva")
                .dataNascimento(LocalDate.of(1985, 5, 15))
                .dataAdmissao(LocalDate.of(2010, 3, 10))
                .build();
    }
}
