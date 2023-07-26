package de.tum.in.ase.eist.rest;

import de.tum.in.ase.eist.model.Person;
import de.tum.in.ase.eist.service.PersonService;
import de.tum.in.ase.eist.util.PersonSortingOptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class PersonResource {

    private final PersonService personService;

    public PersonResource(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("persons")
    public ResponseEntity<List<Person>> getAllPersons(@RequestParam("sortField") Optional<PersonSortingOptions.SortField> sortField, @RequestParam("sortingOrder") Optional<PersonSortingOptions.SortingOrder> sortingOrder) {
        var meso = sortField.isPresent() && sortingOrder.isPresent() ? new PersonSortingOptions(sortingOrder.get(), sortField.get()) : new PersonSortingOptions();
        return ResponseEntity.ok(personService.getAllPersons(meso));
    }

    @PostMapping("persons")
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        if(person.getId() != null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(personService.savePerson(person));
    }

    @PutMapping("persons/{id}")
    public ResponseEntity<Person> updatePerson(@RequestBody Person person, @PathVariable UUID id) {
        if (!person.getId().equals(id)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(personService.savePerson(person));
    }

    @DeleteMapping("persons/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable UUID id) {
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }
}
