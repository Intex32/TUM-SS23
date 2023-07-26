package de.tum.in.ase.eist;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.tum.in.ase.eist.model.Person;
import de.tum.in.ase.eist.repository.PersonRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class PersonIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private PersonRepository personRepository;

    private static ObjectMapper objectMapper;

    @BeforeAll
    static void setup() {
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
    }

    @Test
    void testAddPerson() throws Exception {
        var person = new Person();
        person.setFirstName("Max");
        person.setLastName("Mustermann");
        person.setBirthday(LocalDate.now());

        var response = this.mvc.perform(
                post("/persons")
                        .content(objectMapper.writeValueAsString(person))
                        .contentType("application/json")
        ).andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(1, personRepository.findAll().size());
    }

    @Test
    void testDeletePerson() throws Exception {
        var person = new Person();
        person.setFirstName("Max");
        person.setLastName("Mustermann");
        person.setBirthday(LocalDate.now());

        person = personRepository.save(person);

        var response = this.mvc.perform(
                delete("/persons/" + person.getId())
                        .contentType("application/json")
        ).andReturn().getResponse();

        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
        assertTrue(personRepository.findAll().isEmpty());
    }

    // TODO: Add more test cases here

    @Test
    void testAddThreeParents() throws Exception {
        var person = new Person();
        person.setFirstName("Maxxxx");
        person.setLastName("Mustermann");
        person.setBirthday(LocalDate.now());

        var response = this.mvc.perform(
                post("/persons")
                        .content(objectMapper.writeValueAsString(person))
                        .contentType("application/json")
        ).andReturn().getResponse();

        var person2 = new Person();
        person2.setFirstName("Maxxxx34343");
        person2.setLastName("Mustermann");
        person2.setBirthday(LocalDate.now());

        var response2 = this.mvc.perform(
                post("/persons")
                        .content(objectMapper.writeValueAsString(person2))
                        .contentType("application/json")
        ).andReturn().getResponse();

        var person3 = new Person();
        person3.setFirstName("Maxxxx3344343");
        person3.setLastName("Musterma324nn");
        person3.setBirthday(LocalDate.now());

        var response3 = this.mvc.perform(
                post("/persons")
                        .content(objectMapper.writeValueAsString(person3))
                        .contentType("application/json")
        ).andReturn().getResponse();

        var person4 = new Person();
        person4.setFirstName("Maxxxx343323443");
        person4.setLastName("Musterm234ann");
        person4.setBirthday(LocalDate.now());

        var response4 = this.mvc.perform(
                post("/persons")
                        .content(objectMapper.writeValueAsString(person4))
                        .contentType("application/json")
        ).andReturn().getResponse();

        var all = personRepository.findAll();

        this.mvc.perform(
                put("/persons/" + all.get(0).getId() + "/parents")
                        .content(objectMapper.writeValueAsString(all.get(1)))
                        .contentType("application/json")
        ).andReturn().getResponse();

        this.mvc.perform(
                put("/persons/" + all.get(0).getId() + "/parents")
                        .content(objectMapper.writeValueAsString(all.get(2)))
                        .contentType("application/json")
        ).andReturn().getResponse();

        var res = this.mvc.perform(
                put("/persons/" + all.get(0).getId() + "/parents")
                        .content(objectMapper.writeValueAsString(all.get(3)))
                        .contentType("application/json")
        ).andReturn().getResponse();

        assertEquals(HttpStatus.BAD_REQUEST.value(), res.getStatus());
    }
}
