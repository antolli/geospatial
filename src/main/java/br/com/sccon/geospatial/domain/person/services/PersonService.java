package br.com.sccon.geospatial.domain.person.services;

import br.com.sccon.geospatial.controllers.person.controller.contracts.PersonRequest;
import br.com.sccon.geospatial.controllers.person.controller.contracts.PersonResponse;
import br.com.sccon.geospatial.domain.person.entities.Person;
import br.com.sccon.geospatial.domain.person.mapper.PersonMapper;
import br.com.sccon.geospatial.exceptions.Response4xxException;
import br.com.sccon.geospatial.util.ConstantesUtil;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class PersonService {

    private final PersonMapper mapper = Mappers.getMapper(PersonMapper.class);
    private static final Integer ZERO = 0;

    private final Map<Integer, Person> personMap;

    public PersonService() {
        this.personMap = new HashMap<>();

    }

    public List<PersonResponse> getAllPersonSortedByName() {
        List<Person> sortedList = new ArrayList<>(personMap.values());
        sortedList.sort(Comparator.comparing(Person::getNome));
        return mapper.mapListEntityToListResponse(sortedList);
    }


    public void removePerson(Integer id) {
        if(this.personMap.containsKey(id)){
            personMap.remove(id);
        }
        throw new Response4xxException(ConstantesUtil.NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    public PersonResponse updatePerson(PersonRequest request, Integer id) {
        if(!this.personMap.containsKey(id)){
            throw new Response4xxException(ConstantesUtil.NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        Person person = mapper.mapRequestToEntity(request);
        personMap.put(id, person);
        return mapper.mapEntityToResponse(person);

    }

    public PersonResponse create(final PersonRequest request){
        if (Objects.nonNull(request.getId()) && this.personMap.containsKey(request.getId())) {
            throw new Response4xxException(ConstantesUtil.ALREADY_EXISTS, HttpStatus.CONFLICT);
        }

        if (Objects.isNull(request.getId()) || ZERO.equals(request.getId())) {
            int newId = this.personMap.keySet().stream().max(Integer::compare).orElse(ZERO) + 1;
            request.setId(newId);
        }

        Person person = addPerson(request);

        return mapper.mapEntityToResponse(person);

    }
    public Person addPerson(PersonRequest request) {
        Person person = mapper.mapRequestToEntity(request);
        personMap.put(person.getId(), person);
        return person;
    }

}
