package br.com.sccon.geospatial.domain.person.services;

import br.com.sccon.geospatial.domain.person.entities.Person;
import br.com.sccon.geospatial.util.ConstantesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

public abstract class AbstractPersonService {
    public Map<Integer, Person> personMap;

    @Autowired
    public AbstractPersonService(Map<Integer, Person> personMap) {
        this.personMap = personMap;
    }

    public void verifyExistence(Integer id){
        if(!personMap.containsKey(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ConstantesUtil.NOT_FOUND);
        }
    }
}
