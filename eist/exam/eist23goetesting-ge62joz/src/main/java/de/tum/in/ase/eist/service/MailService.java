package de.tum.in.ase.eist.service;

import de.tum.in.ase.eist.model.Absence;
import de.tum.in.ase.eist.model.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailService {
    public boolean sendMail(List<Person> receiver, Absence absence) {
        return false;
    }
}
