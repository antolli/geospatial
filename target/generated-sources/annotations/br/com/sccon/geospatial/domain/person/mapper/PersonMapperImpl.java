package br.com.sccon.geospatial.domain.person.mapper;

import br.com.sccon.geospatial.controllers.person.controller.contracts.PersonRequest;
import br.com.sccon.geospatial.controllers.person.controller.contracts.PersonResponse;
import br.com.sccon.geospatial.domain.person.entities.Person;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-10T23:29:23-0300",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
public class PersonMapperImpl implements PersonMapper {

    @Override
    public PersonResponse mapEntityToResponse(Person employee) {
        if ( employee == null ) {
            return null;
        }

        PersonResponse personResponse = new PersonResponse();

        return personResponse;
    }

    @Override
    public List<PersonResponse> mapListEntityToListResponse(List<Person> employeeList) {
        if ( employeeList == null ) {
            return null;
        }

        List<PersonResponse> list = new ArrayList<PersonResponse>( employeeList.size() );
        for ( Person person : employeeList ) {
            list.add( mapEntityToResponse( person ) );
        }

        return list;
    }

    @Override
    public Person mapRequestToEntity(PersonRequest request) {
        if ( request == null ) {
            return null;
        }

        Person person = new Person();

        return person;
    }
}
