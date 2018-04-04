package ch.globaz.tmmas.rentesservice.application.service.impl;

import ch.globaz.tmmas.rentesservice.application.api.web.resources.DossierResourceAttributes;
import ch.globaz.tmmas.rentesservice.application.event.impl.DomainEventPublisher;
import ch.globaz.tmmas.rentesservice.application.service.DossierService;
import ch.globaz.tmmas.rentesservice.domain.command.CloreDossierCommand;
import ch.globaz.tmmas.rentesservice.domain.command.CreerDossierCommand;
import ch.globaz.tmmas.rentesservice.domain.command.MiseAJourDossierCommand;
import ch.globaz.tmmas.rentesservice.domain.command.ValiderDossierCommand;
import ch.globaz.tmmas.rentesservice.domain.common.specification.Specification;
import ch.globaz.tmmas.rentesservice.domain.event.DossierClotEvent;
import ch.globaz.tmmas.rentesservice.domain.event.DossierCreeEvent;
import ch.globaz.tmmas.rentesservice.domain.event.DossierValideeEvent;
import ch.globaz.tmmas.rentesservice.domain.model.dossier.Dossier;
import ch.globaz.tmmas.rentesservice.domain.model.dossier.DossierStatus;
import ch.globaz.tmmas.rentesservice.domain.reglesmetiers.DateCloturePlusRecenteDateValidation;
import ch.globaz.tmmas.rentesservice.domain.reglesmetiers.DateValidationPlusRecenteDateEnregistrement;
import ch.globaz.tmmas.rentesservice.domain.reglesmetiers.StatusDossierCorrespond;
import ch.globaz.tmmas.rentesservice.domain.repository.DossierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DossierServiceImpl implements DossierService {

	@Autowired
	DossierRepository repository;

	@Autowired
	DomainEventPublisher eventPublisher;


	@Transactional
	@Override
	public List<DossierResourceAttributes> getAll() {
		List<Dossier> dossiers =  repository.allDossiers();

		return dossiers.stream().map(dossier -> new DossierResourceAttributes(dossier)).collect(Collectors.toList());

	}

	@Transactional
	@Override
	public Optional<Dossier> getById(Long id) {

		 return repository.dossierById(id).map(Optional::of).orElseGet(Optional::empty);

	}

	@Transactional
	@Override
	public Dossier creerDossier(CreerDossierCommand command) {

		Dossier dossier = Dossier.builder(command);

		dossier =  repository.initieDossier(dossier);

		eventPublisher.publishEvent(DossierCreeEvent.fromEntity(dossier));

		return dossier;

	}


	private  Optional<DossierResourceAttributes> validerDossier(MiseAJourDossierCommand command, Long dossierId) {


		Specification spec = new DateValidationPlusRecenteDateEnregistrement(command.getData().getDateValidation())
				.and(new StatusDossierCorrespond(DossierStatus.INITIE));

		return repository.dossierById(dossierId).map(dossier -> {

			if(!spec.isSatisfiedBy(dossier)){
				throw new RegleMetiersNonSatisfaite(spec);
			}

			dossier.validerDossier(command.getData().getDateValidation());

			repository.validerDossier(dossier);

			eventPublisher.publishEvent(DossierValideeEvent.fromEntity(dossier));

			DossierResourceAttributes resource = new DossierResourceAttributes(dossier);
			return Optional.of(resource);

		}).orElseGet(Optional::empty);


	}


	private Optional<DossierResourceAttributes> cloreDossier(MiseAJourDossierCommand command, Long dossierId) {

		Specification spec = new DateCloturePlusRecenteDateValidation(command.getData().getDateCloture())
				.and(new StatusDossierCorrespond(DossierStatus.VALIDE));


		return repository.dossierById(dossierId).map(dossier -> {

			if(!spec.isSatisfiedBy(dossier)){
				throw new RegleMetiersNonSatisfaite(spec);
			}

			dossier.cloreDossier(command.getData().getDateCloture());

			repository.cloreDossier(dossier);

			eventPublisher.publishEvent(DossierClotEvent.fromEntity(dossier));

			DossierResourceAttributes resource = new DossierResourceAttributes(dossier);//.buildResourceObject();

			return Optional.of(resource);

		}).orElseGet(Optional::empty);

	}

	@Transactional
	@Override
	public Optional<DossierResourceAttributes> miseAJourDossier(MiseAJourDossierCommand command, Long dossierId) {

		switch (command.getAction()){
			case VALIDER:
				return validerDossier(command,dossierId);


			case CLORE:
				return cloreDossier(command,dossierId);

			default:
				throw new RuntimeException();
		}

	}


}
