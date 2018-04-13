package ch.globaz.tmmas.personnesservice.application.service.impl;

import ch.globaz.tmmas.personnesservice.application.service.PersonneService;
import ch.globaz.tmmas.personnesservice.domain.command.CreerPersonneMoraleCommand;
import ch.globaz.tmmas.personnesservice.domain.factory.PersonneMoraleFactory;
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
	public PersonneMorale createPersonne(CreerPersonneMoraleCommand command) {

		return personneRepository.creerPersonneMorale(PersonneMoraleFactory.creerPersonne(command.getNom(),command
				.getPrenom(),command.getDateNaissance(),command.getNss(),command.getSexe()));

	}

	@Transactional
	@Override
	public Optional<PersonneMorale> findById(Long id) {
		return personneRepository.getPersonneById(id).map(Optional::of).orElseGet(Optional::empty);
	}
}
