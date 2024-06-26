package br.com.sccon.geospatial.controllers.person.controller.contracts;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PersonRequest {

    private Integer id;
    @NotNull(message = "O campo 'nome' é obrigatorio.")
    private String nome;
    @NotNull(message = "O campo 'data_nascimento' é obrigatorio.")
    private LocalDate dataNascimento;
    @NotNull(message = "O campo 'data_admissao' é obrigatorio.")
    private LocalDate dataAdmissao;
}
