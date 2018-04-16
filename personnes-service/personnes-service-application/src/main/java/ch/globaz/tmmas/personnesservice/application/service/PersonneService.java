package ch.globaz.tmmas.personnesservice.application.service;

import ch.globaz.tmmas.personnesservice.domain.command.CreerPersonneMoraleCommand;
import ch.globaz.tmmas.personnesservice.domain.exception.PersonnesIncoherenceException;
import ch.globaz.tmmas.personnesservice.domain.model.Adresse;
import ch.globaz.tmmas.personnesservice.domain.model.PersonneMorale;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface PersonneService {

	PersonneMorale creerPersonneMorale(CreerPersonneMoraleCommand command) throws PersonnesIncoherenceException;

	Optional<PersonneMorale> getPersonneById(Long id);

	PersonneMorale mettreAJour(PersonneMorale personneMorale);


}
