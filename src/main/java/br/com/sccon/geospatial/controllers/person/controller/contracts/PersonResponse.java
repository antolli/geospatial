package br.com.sccon.geospatial.controllers.person.controller.contracts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class PersonResponse {

    private String nome;
    private Date dataNascimento;
    private Date dataAdmissao;

}
