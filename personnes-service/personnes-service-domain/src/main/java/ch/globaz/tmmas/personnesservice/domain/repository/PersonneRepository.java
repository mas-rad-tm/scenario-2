package ch.globaz.tmmas.personnesservice.domain.repository;

import ch.globaz.tmmas.personnesservice.domain.model.PersonneMorale;

import java.util.Optional;

public interface PersonneRepository {

	PersonneMorale creerPersonneMorale(PersonneMorale personneMorale);

	Optional<PersonneMorale> getPersonneById(Long id);
}
