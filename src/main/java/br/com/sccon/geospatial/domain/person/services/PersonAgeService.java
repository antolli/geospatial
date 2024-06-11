package br.com.sccon.geospatial.domain.person.services;

import br.com.sccon.geospatial.domain.person.entities.Person;
import br.com.sccon.geospatial.exceptions.Response4xxException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

@Service
public class PersonAgeService extends AbstractPersonService{

    public String calculatePersonAge(Integer id, String output){
        Person person = personMap.get(id);
        super.verifyExistence(id);

        LocalDate dataNascimento = person.getDataNascimento();
        LocalDate dataAtual = LocalDate.now();
        String idadeOutput;

        switch (output.toLowerCase()) {
            case "days":
                long days = ChronoUnit.DAYS.between(dataNascimento, dataAtual);
                idadeOutput = days + " days";
                break;
            case "months":
                long months = ChronoUnit.MONTHS.between(dataNascimento, dataAtual);
                idadeOutput = months + " months";
                break;
            case "years":
                int years = Period.between(dataNascimento, dataAtual).getYears();
                idadeOutput = years + " years";
                break;
            default:
                throw new Response4xxException("Formato de output invalido: "+ output, HttpStatus.BAD_REQUEST);
        }
        return idadeOutput;
    }
}
