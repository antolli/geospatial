package br.com.sccon.geospatial.controllers.person.controller.contracts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class PersonResponse {

    private String nome;
    private LocalDate dataNascimento;
    private LocalDate dataAdmissao;

}
