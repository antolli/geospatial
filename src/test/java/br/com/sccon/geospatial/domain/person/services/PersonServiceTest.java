package br.com.sccon.geospatial.domain.person.services;

import br.com.sccon.geospatial.domain.person.mapper.PersonMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class PersonServiceTest {

    @Spy
    PersonMapper mapper = Mappers.getMapper(PersonMapper.class);

    @InjectMocks
    private PersonService personService;
    @Test
    void deveCriarPessoa(){

    }

}
