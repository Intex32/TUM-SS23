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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class PersonServiceTest {
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonRepository personRepository;

    @Test
    void testSavePerson() {
        var person = new Person();
        person.setFirstName("Max");
        person.setLastName("Mustermann");
        person.setInstructor(true);

        personService.save(person);

        assertEquals(1, personRepository.findAll().size());
    }

    @Test
    void testDeletePerson() {
        var person = new Person();
        person.setFirstName("Max");
        person.setLastName("Mustermann");
        person.setInstructor(false);

        person = personRepository.save(person);

        personService.delete(person);

        assertTrue(personRepository.findAll().isEmpty());
    }

    @Test
    void testGetInstructors() {
        Person student1 = new Person();
        student1.setFirstName("Max");
        student1.setLastName("Mustermann");
        student1.setInstructor(false);

        Person student2 = new Person();
        student2.setFirstName("Alice");
        student2.setLastName("Mustermann");
        student2.setInstructor(false);

        Person instructor = new Person();
        instructor.setFirstName("Alice");
        instructor.setLastName("Mustermann");
        instructor.setInstructor(true);

        personService.save(student1);
        personService.save(student2);
        personService.save(instructor);

        List<Person> instructors = personService.getInstructors();

        assertEquals(1, instructors.size());
        assertEquals(instructor, instructors.get(0));
    }
}
