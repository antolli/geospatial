package br.com.sccon.geospatial.domain.person.services;

import br.com.sccon.geospatial.enums.OutputSalaryEnum;
import br.com.sccon.geospatial.domain.person.entities.Person;
import br.com.sccon.geospatial.exceptions.Response4xxException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.stream.IntStream;

@Service
public class PersonSalaryService extends AbstractPersonService{
    private static final double SALARIO_INICIAL = 1558.00;
    private static final double PERCENTUAL_INCREMENTO = 0.18;
    private static final double INCREMENTO_ANUAL = 500.00;
    private static final double SALARIO_MINIMO = 1302.00;
    public Double calcularSalario(final Integer id, final String output){
        if(!OutputSalaryEnum.fromValue(output)){
            throw new Response4xxException("Formato de output invalido: "+ output, HttpStatus.BAD_REQUEST);
        }
        super.verifyExistence(id);

        final Integer anosTrabalhados = getAnosTrabalhadosPorId(id);
        Double response = 0.00;

        if (OutputSalaryEnum.FULL.getValue().equalsIgnoreCase(output)) {
            response =  calcularSalarioFull(anosTrabalhados);
        }

        if (OutputSalaryEnum.MIN.getValue().equalsIgnoreCase(output)) {
            response = calcularSalarioPorSalariosMinimos(anosTrabalhados);
        }

        return response;
    }
    private Double calcularSalarioFull(Integer anosTrabalhados) {

        Double salarioFinal = IntStream.range(0, anosTrabalhados)
                .mapToDouble(year -> PERCENTUAL_INCREMENTO * SALARIO_INICIAL + INCREMENTO_ANUAL)
                .reduce(SALARIO_INICIAL, (salary, increment) -> salary + increment);

        return Math.ceil(salarioFinal * 100.0) / 100.0;
    }

    private Double calcularSalarioPorSalariosMinimos(Integer anosTrabalhados) {
        Double salary = calcularSalarioFull(anosTrabalhados);
        return Math.ceil((salary / SALARIO_MINIMO) * 100.0) / 100.0;
    }

    private Integer getAnosTrabalhadosPorId(Integer id){
        final Person person = super.personMap.get(id);
        final LocalDate dataAtual = LocalDate.now();
        return Period.between(person.getDataAdmissao(), dataAtual).getYears();
    }
}
