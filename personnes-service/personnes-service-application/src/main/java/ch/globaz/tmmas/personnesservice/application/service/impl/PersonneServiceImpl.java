package ch.globaz.tmmas.personnesservice.application.service.impl;

import ch.globaz.tmmas.personnesservice.application.event.InternalEventPublisher;
import ch.globaz.tmmas.personnesservice.application.service.PersonneService;
import ch.globaz.tmmas.personnesservice.domain.command.CreerPersonneMoraleCommand;
import ch.globaz.tmmas.personnesservice.domain.event.PersonneMoraleCreeEvent;
import ch.globaz.tmmas.personnesservice.domain.event.PersonnePhysiqueVerificationEvent;
import ch.globaz.tmmas.personnesservice.domain.exception.PersonnesIncoherenceException;
import ch.globaz.tmmas.personnesservice.domain.factory.PersonneMoraleFactory;
import ch.globaz.tmmas.personnesservice.domain.model.PersonnePhysique;
import ch.globaz.tmmas.personnesservice.domain.repository.PersonneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonneServiceImpl implements PersonneService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PersonneServiceImpl.class);

	@Autowired
	PersonneRepository personneRepository;

	@Autowired
	InternalEventPublisher eventPublisher;

	@Transactional
	@Override
	public PersonnePhysique creerPersonneMorale(CreerPersonneMoraleCommand command) throws PersonnesIncoherenceException {

		PersonnePhysique personnePhysique = personneRepository.creerPersonneMorale(
				PersonneMoraleFactory.creerPersonne(command,personneRepository)
		);
		eventPublisher.publishEvent(PersonneMoraleCreeEvent.fromEntity(personnePhysique));
		return personnePhysique;

	}

	@Transactional
	@Override
	public Boolean checkifPersonneExist(Long personneId){

		LOGGER.info("Check if personne exist, id {}", personneId);

		return personneRepository.personneExist(personneId);
	}

	@Transactional
	@Override
	public Optional<PersonnePhysique> getPersonneById(Long id) {

		return personneRepository.getPersonneById(id).map(personne -> {


			return Optional.of(personne);
		}).orElseGet(() -> {


			return Optional.empty();
		});

	}

	@Transactional
	@Override
	public PersonnePhysique mettreAJour(PersonnePhysique personnePhysique) {
		return personneRepository.mettreAJour(personnePhysique);
	}

	@Transactional
	@Override
	public List<PersonnePhysique> getAllPersonnes() {
		return personneRepository.listerPersonnes();
	}



}
