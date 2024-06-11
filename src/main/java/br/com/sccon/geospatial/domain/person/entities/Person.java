package br.com.sccon.geospatial.domain.person.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Person {
    private Integer id;
    private String nome;
    private LocalDate dataNascimento;
    private LocalDate dataAdmissao;
}
