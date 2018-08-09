package ch.globaz.tmmas.personnesservice.application.service;

import ch.globaz.tmmas.personnesservice.domain.command.CreerPersonneMoraleCommand;
import ch.globaz.tmmas.personnesservice.domain.exception.PersonnesIncoherenceException;
import ch.globaz.tmmas.personnesservice.domain.model.PersonnePhysique;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PersonneService {

	PersonnePhysique creerPersonneMorale(CreerPersonneMoraleCommand command) throws PersonnesIncoherenceException;

	@Transactional
	Boolean checkifPersonneExist(Long personneId);

	Optional<PersonnePhysique> getPersonneById(Long id);

	PersonnePhysique mettreAJour(PersonnePhysique personnePhysique);


    @Transactional
	List<PersonnePhysique> getAllPersonnes();
}
