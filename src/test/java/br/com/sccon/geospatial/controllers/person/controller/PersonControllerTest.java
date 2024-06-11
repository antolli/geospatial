package br.com.sccon.geospatial.controllers.person.controller;

import br.com.sccon.geospatial.controllers.person.controller.contracts.PersonRequest;
import br.com.sccon.geospatial.controllers.person.controller.contracts.PersonResponse;
import br.com.sccon.geospatial.domain.person.services.PersonService;
import br.com.sccon.geospatial.stub.PersonRequestStub;
import br.com.sccon.geospatial.stub.PersonResponseStub;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
class PersonControllerTest extends AbstractHttpTest{

    private final String URL_BASE = "/person";
    private MockMvc mockMvc;

    @Mock
    private PersonService personService;
    private ObjectMapper objectMapper;
    @InjectMocks
    private PersonController personController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        mockMvc = MockMvcBuilders.standaloneSetup(personController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter(objectMapper))
                .build();
    }

    @SneakyThrows
    @Test
    void testAddPerson() {
        PersonRequest request = PersonRequestStub.getPersonRequestWithIdNull();

        PersonResponse response = PersonResponseStub.getPersonResponse();

        when(personService.create(any(PersonRequest.class))).thenReturn(response);

        final MockHttpServletRequestBuilder requestBuilder =
                post(URL_BASE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request));

        final MockHttpServletResponse resultado = mockMvc.perform(requestBuilder).andReturn().getResponse();
        assertEquals(HttpStatus.CREATED.value(), resultado.getStatus());

    }

}
