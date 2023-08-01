package de.tum.in.ase.eist.service;

import de.tum.in.ase.eist.customExceptions.InvalidDataException;
import de.tum.in.ase.eist.model.Absence;
import de.tum.in.ase.eist.model.Person;
import de.tum.in.ase.eist.repository.AbsenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbsenceService {
    private final AbsenceRepository absenceRepository;
    private final PersonService personService;
    private final MailService mailService;

    public AbsenceService(AbsenceRepository absenceRepository, PersonService personService, MailService mailService) {
        this.absenceRepository = absenceRepository;
        this.personService = personService;
        this.mailService = mailService;
    }

    public Absence save(Absence absence) {
        List<Absence> absencesOfSamePerson = this.getAbsencesByPersonId((absence.getPerson().getId()));
        long countAbsencesOnSameDate = absencesOfSamePerson.stream()
                .filter(a -> a.getDate().equals(absence.getDate()))
                .count();
        if (countAbsencesOnSameDate >= 1) {
            throw new InvalidDataException("Absences of the same date belonging to the same person are invalid");
        }
        return absenceRepository.save(absence);
    }

    public List<Absence> getAbsencesByPersonId(Long personId) {
        return absenceRepository.findAbsencesByPersonId(personId);
    }

    public void submitAbsence(Absence absence, boolean alertInstructor) {
        List<Person> instructors = personService.getInstructors();
        if (alertInstructor) {
            boolean sentMailSuccessfully = mailService.sendMail(instructors, absence);
            if (sentMailSuccessfully) {
                System.out.println("Sent Mail successfully");
            } else {
                throw new RuntimeException("Something went wrong while sending the mail");
            }
        }
        save(absence);
    }
}
