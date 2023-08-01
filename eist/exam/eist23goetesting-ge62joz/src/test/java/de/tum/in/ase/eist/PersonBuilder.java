package de.tum.in.ase.eist;

import de.tum.in.ase.eist.model.Person;
import de.tum.in.ase.eist.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonBuilder {
    @Autowired
    private PersonService personService;
    private String firstName = "Max";
    private String lastName = "Mustermann";
    private Long id;
    private boolean isInstructor = false;

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isInstructor() {
        return isInstructor;
    }

    public void setInstructor(boolean instructor) {
        isInstructor = instructor;
    }

    public PersonBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PersonBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public PersonBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public PersonBuilder withIsInstructor(boolean isInstructor) {
        this.isInstructor = isInstructor;
        return this;
    }

    public Person build() {
        Person person = new Person();
        person.setFirstName(this.firstName);
        person.setLastName(this.lastName);
        person.setId(this.id);
        person.setInstructor(this.isInstructor);
        return personService.save(person);
    }
}
