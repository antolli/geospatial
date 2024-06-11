package br.com.sccon.geospatial.domain.person.mapper;

import br.com.sccon.geospatial.controllers.person.controller.contracts.PersonRequest;
import br.com.sccon.geospatial.controllers.person.controller.contracts.PersonResponse;
import br.com.sccon.geospatial.domain.person.entities.Person;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PersonMapper {

    PersonResponse mapEntityToResponse(final Person employee);
    List<PersonResponse> mapListEntityToListResponse(final List<Person> employeeList);

    Person mapRequestToEntity(final PersonRequest request);

}
