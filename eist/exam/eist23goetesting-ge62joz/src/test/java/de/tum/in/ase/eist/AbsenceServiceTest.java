package de.tum.in.ase.eist;

import de.tum.in.ase.eist.customExceptions.InvalidDataException;
import de.tum.in.ase.eist.model.Absence;
import de.tum.in.ase.eist.model.Person;
import de.tum.in.ase.eist.repository.AbsenceRepository;
import de.tum.in.ase.eist.service.AbsenceService;
import de.tum.in.ase.eist.service.MailService;
import de.tum.in.ase.eist.service.PersonService;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AbsenceServiceTest {
    @TestSubject
    private AbsenceService absenceService;
    @Autowired
    private AbsenceRepository absenceRepository;
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonBuilder personBuilder;
    @Mock
    private MailService mailServiceMock;

    @BeforeEach
    void setUp() {
        mailServiceMock = createMock(MailService.class);
        absenceService = new AbsenceService(absenceRepository, personService, mailServiceMock);
    }

    @Test
    void testSaveAbsence() {
        // You can use the PersonBuilder to instantiate and insert a Person into the database
        Person person = personBuilder
                .withFirstName("Max")
                .withLastName("Mustermann")
                .withIsInstructor(false)
                .build();
        personService.save(person);

        /**
         * TODO: Write a test to test the saveAbsence(...) method and utilize
         * the absenceRepository to check if saved correctly
         **/
        var absence = new Absence();
        absence.setPerson(person);
        absence.setComment("test");
        absence.setDate(LocalDate.now());

        var savedAbsence = absenceService.save(absence);
        assertNotNull(savedAbsence);
        assertNotNull(absenceRepository.findById(savedAbsence.getId()));
    }

    @Test
    void testSaveAbsenceOnSameDate() {
        /**
         * TODO: Instantiate a duplicate Absence object for a Person and save it
         * Tip: use assertThrows to ensure that the InvalidDataException is thrown correctly
         */
        Person person = personBuilder
                .withFirstName("Max")
                .withLastName("Mustermann")
                .withIsInstructor(false)
                .build();
        personService.save(person);

        var date = LocalDate.now();

        var absence1 = new Absence();
        absence1.setPerson(person);
        absence1.setComment("test1");
        absence1.setDate(date);

        var absence2 = new Absence();
        absence2.setPerson(person);
        absence2.setComment("test2");
        absence2.setDate(date);

        var savedAbsence1 = absenceService.save(absence1);

        assertThrows(InvalidDataException.class, () -> {
            var savedAbsence2 = absenceService.save(absence2);
        });
    }

    @Test
    void testSubmitAbsenceAlertInstructor() {
        /**
         * TODO: Mock mailService.sendMail(...) to ensure that the method is used correctly
         * Tip: with expect(...).andReturn(...) you can record the behaviour of a mocked class
         * Use replay(...) to play the recorded behaviour
         * Use verify(...) to check if the mock is called
         */
        Person student = personBuilder
                .withFirstName("Max")
                .withLastName("Mustermannxx")
                .withIsInstructor(false)
                .build();
        personService.save(student);
        Person instr = personBuilder
                .withFirstName("Instru")
                .withLastName("ctor")
                .withIsInstructor(true)
                .build();
        personService.save(instr);

        var date = LocalDate.now();
        var absence1 = new Absence();
        absence1.setPerson(student);
        absence1.setComment("mesomerie");
        absence1.setDate(date);

        expect(mailServiceMock.sendMail(List.of(instr), absence1)).andReturn(true);
        replay(mailServiceMock);

        absenceService.submitAbsence(absence1, true);

        verify(mailServiceMock);
    }

    @Test
    void testSubmitAbsenceDoesNotAlertInstructor() {
        /**
         * TODO: Mock mailService.sendMail(...) to ensure that the instructor is not alerted
         * Tip: By not specifying the behaviour of the mock you can afterwards verify if a method was NOT called
         * Don't forget to use replay!
         */

        Person student = personBuilder
                .withFirstName("Max")
                .withLastName("Mustermannxx")
                .withIsInstructor(false)
                .build();
        personService.save(student);
        Person instr = personBuilder
                .withFirstName("Instru")
                .withLastName("ctor")
                .withIsInstructor(true)
                .build();
        personService.save(instr);

        var date = LocalDate.now();
        var absence1 = new Absence();
        absence1.setPerson(student);
        absence1.setComment("mesomerie");
        absence1.setDate(date);

        replay(mailServiceMock);

        absenceService.submitAbsence(absence1, false);

        verify(mailServiceMock);
    }
}
