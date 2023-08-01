package de.tum.in.ase.eist.service;

import de.tum.in.ase.eist.model.Person;
import de.tum.in.ase.eist.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person save(Person person) {
        return this.personRepository.save(person);
    }

    public void delete(Person person) {
        personRepository.delete(person);
    }

    public List<Person> getInstructors() {
        return personRepository.findByIsInstructorTrue();
    }

}
