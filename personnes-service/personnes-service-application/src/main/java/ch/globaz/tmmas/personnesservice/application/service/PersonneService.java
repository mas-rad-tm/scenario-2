package ch.globaz.tmmas.personnesservice.application.service;

import ch.globaz.tmmas.personnesservice.domain.command.CreerPersonneMoraleCommand;
import ch.globaz.tmmas.personnesservice.domain.model.PersonneMorale;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface PersonneService {

	PersonneMorale createPersonne(CreerPersonneMoraleCommand command);

	Optional<PersonneMorale> findById(Long id);

}
