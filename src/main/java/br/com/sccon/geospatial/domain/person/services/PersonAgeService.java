package br.com.sccon.geospatial.domain.person.services;

import br.com.sccon.geospatial.domain.person.entities.Person;
import br.com.sccon.geospatial.enums.OutputAgeEnum;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Map;

@Service
public class PersonAgeService extends AbstractPersonService{

    public PersonAgeService(Map<Integer, Person> personMap) {
        super(personMap);
    }

    public String calculatePersonAge(final Integer id, String output){
        if(!OutputAgeEnum.fromValue(output)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formato de output invalido: "+ output);
        }
        super.verifyExistence(id);
        final Person person = personMap.get(id);
        final LocalDate dataNascimento = person.getDataNascimento();
        final LocalDate dataAtual = LocalDate.now();
        String idadeOutput = "";

        if(OutputAgeEnum.DAYS.getValue().equalsIgnoreCase(output)){
            final long days = ChronoUnit.DAYS.between(dataNascimento, dataAtual);
            idadeOutput = days + " days";
        }
        if(OutputAgeEnum.MONTHS.getValue().equalsIgnoreCase(output)){
            final long months = ChronoUnit.MONTHS.between(dataNascimento, dataAtual);
            idadeOutput = months + " months";
        }

        if(OutputAgeEnum.YEARS.getValue().equalsIgnoreCase(output)){
            final int years = Period.between(dataNascimento, dataAtual).getYears();
            idadeOutput = years + " years";
        }

        return idadeOutput;
    }
}
