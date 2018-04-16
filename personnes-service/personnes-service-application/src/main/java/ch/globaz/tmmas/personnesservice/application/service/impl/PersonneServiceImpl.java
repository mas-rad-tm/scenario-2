package ch.globaz.tmmas.personnesservice.application.service.impl;

import ch.globaz.tmmas.personnesservice.application.service.PersonneService;
import ch.globaz.tmmas.personnesservice.domain.command.CreerPersonneMoraleCommand;
import ch.globaz.tmmas.personnesservice.domain.exception.PersonnesIncoherenceException;
import ch.globaz.tmmas.personnesservice.domain.factory.PersonneMoraleFactory;
import ch.globaz.tmmas.personnesservice.domain.model.Adresse;
import ch.globaz.tmmas.personnesservice.domain.model.PersonneMorale;
import ch.globaz.tmmas.personnesservice.domain.repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class PersonneServiceImpl implements PersonneService {

	@Autowired
	PersonneRepository personneRepository;

	@Transactional
	@Override
	public PersonneMorale creerPersonneMorale(CreerPersonneMoraleCommand command) throws PersonnesIncoherenceException {

		return personneRepository.creerPersonneMorale(
				PersonneMoraleFactory.creerPersonne(command,personneRepository)
		);

	}

	@Transactional
	@Override
	public Optional<PersonneMorale> getPersonneById(Long id) {

		return personneRepository.getPersonneById(id).map(personne -> {

			personne.getAdresses();
			return Optional.of(personne);
		}).orElseGet(Optional::empty);

	}

	@Override
	public PersonneMorale mettreAJour(PersonneMorale personneMorale) {
		return personneRepository.mettreAJour(personneMorale);
	}



}
