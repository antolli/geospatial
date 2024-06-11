package br.com.sccon.geospatial.domain.person.services;

import br.com.sccon.geospatial.controllers.person.controller.contracts.PersonRequest;
import br.com.sccon.geospatial.controllers.person.controller.contracts.PersonResponse;
import br.com.sccon.geospatial.domain.person.entities.Person;
import br.com.sccon.geospatial.domain.person.mapper.PersonMapper;
import br.com.sccon.geospatial.exceptions.PersonNullException;
import br.com.sccon.geospatial.util.ConstantesUtil;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class PersonService extends AbstractPersonService {

    private final PersonMapper mapper = Mappers.getMapper(PersonMapper.class);
    private static final Integer ZERO = 0;

    public PersonService(Map<Integer, Person> personMap) {
        super(personMap);
    }

    public List<PersonResponse> getAllPersonSortedByName() {
        List<Person> sortedList = new ArrayList<>(personMap.values());
        sortedList.sort(Comparator.comparing(Person::getNome));
        return mapper.mapListEntityToListResponse(sortedList);
    }

    public PersonResponse getById(final Integer id) {
        super.verifyExistence(id);
        final Person person = personMap.get(id);
        return mapper.mapEntityToResponse(person);
    }

    public void removePerson(final Integer id) {
        super.verifyExistence(id);
        personMap.remove(id);
    }

    public PersonResponse updatePerson(final PersonRequest request, final Integer id) {
        super.verifyExistence(id);
        final Person person = mapper.mapRequestToEntity(request);
        personMap.put(id, person);
        return mapper.mapEntityToResponse(person);

    }

    public PersonResponse updateNamePerson(Integer id, String newName) {
        super.verifyExistence(id);
        final Person person = personMap.get(id);

        if(Objects.isNull(person)){
            throw new PersonNullException("A pessoa encontrada com ID " + id + "é nula.");
        }
        person.setNome(newName);
        personMap.put(id, person);
        return mapper.mapEntityToResponse(person);

    }

    public PersonResponse create(final PersonRequest request){
        if (Objects.nonNull(request.getId()) && personMap.containsKey(request.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, ConstantesUtil.ALREADY_EXISTS);

        }

        if (Objects.isNull(request.getId()) || ZERO.equals(request.getId())) {
            final int newId = personMap.keySet().stream().max(Integer::compare).orElse(ZERO) + 1;
            request.setId(newId);
        }

        final Person person = addPerson(request);

        return mapper.mapEntityToResponse(person);

    }
    public Person addPerson(final PersonRequest request) {
        final Person person = mapper.mapRequestToEntity(request);
        personMap.put(person.getId(), person);
        return person;
    }

}
