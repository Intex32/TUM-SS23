package de.tum.in.ase.eist;

import de.tum.in.ase.eist.model.Person;
import de.tum.in.ase.eist.repository.PersonRepository;
import de.tum.in.ase.eist.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class PersonServiceTest {
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonRepository personRepository;

    @Test
    void testAddPerson() {
        var person = new Person();
        person.setFirstName("Max");
        person.setLastName("Mustermann");
        person.setBirthday(LocalDate.now());

        personService.save(person);

        assertEquals(1, personRepository.findAll().size());
    }

    @Test
    void testDeletePerson() {
        var person = new Person();
        person.setFirstName("Max");
        person.setLastName("Mustermann");
        person.setBirthday(LocalDate.now());

        person = personRepository.save(person);

        personService.delete(person);

        assertTrue(personRepository.findAll().isEmpty());
    }

    @Test
    void testAddParent() {
        var parent = new Person();
        parent.setFirstName("Max3431");
        parent.setLastName("Mustermann");
        parent.setBirthday(LocalDate.now());
        parent = personService.save(parent);

        var child = new Person();
        child.setFirstName("Max3432");
        child.setLastName("Mustermann");
        child.setBirthday(LocalDate.now());
        child = personService.save(child);

        var px = parent;
        var cx = child;
        child = assertDoesNotThrow(() -> personService.addParent(cx, px));

        assertTrue(personRepository.findById(parent.getId()).isPresent());
        assertTrue(personRepository.findById(child.getId()).isPresent());
        assertTrue(child.getParents().contains(parent));
    }

    @Test
    void testAddThreeParents() {
        var person = new Person();
        person.setFirstName("Max1");
        person.setLastName("Mustermann");
        person.setBirthday(LocalDate.now());
        person = personService.save(person);

        var person2 = new Person();
        person2.setFirstName("Max2");
        person2.setLastName("Mustermann");
        person2.setBirthday(LocalDate.now());
        person2 = personService.save(person2);

        var person3 = new Person();
        person3.setFirstName("Max3");
        person3.setLastName("Mustermann");
        person3.setBirthday(LocalDate.now());
        person3 = personService.save(person3);

        var person4 = new Person();
        person4.setFirstName("Max4");
        person4.setLastName("Mustermann");
        person4.setBirthday(LocalDate.now());
        person4 = personService.save(person4);

        person = personService.addParent(person, person2);
        person = personService.addParent(person, person3);

        var personx = person;
        var person4x = person4;
        assertThrowsExactly(ResponseStatusException.class, () -> personService.addParent(personx, person4x));
    }
}
