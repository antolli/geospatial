package br.com.sccon.geospatial.controllers.person.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
public abstract class AbstractHttpTest {


    protected String toJson(final Object obj){
        try{
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        }catch(final Exception e){
            throw new RuntimeException(e);
        }
    }
}
