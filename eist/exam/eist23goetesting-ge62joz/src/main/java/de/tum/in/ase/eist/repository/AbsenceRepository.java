package de.tum.in.ase.eist.repository;

import de.tum.in.ase.eist.model.Absence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long> {
    List<Absence> findAbsencesByPersonId(Long personId);
}
