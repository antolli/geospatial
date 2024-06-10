package br.com.sccon.geospatial.domain.person.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class Person {
    private Integer id;
    private String nome;
    private LocalDate dataNascimento;
    private LocalDate dataAdmissao;
}
