package br.com.sccon.geospatial.domain.person.services;

import br.com.sccon.geospatial.domain.person.entities.Person;
import br.com.sccon.geospatial.exceptions.Response4xxException;
import br.com.sccon.geospatial.util.ConstantesUtil;
import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractPersonService {
    public Map<Integer, Person> personMap;

    public AbstractPersonService() {
        this.personMap = new ConcurrentHashMap<>();
    }

    public void verifyExistence(Integer id){
        if(!personMap.containsKey(id)){
            throw new Response4xxException(ConstantesUtil.NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }
}
