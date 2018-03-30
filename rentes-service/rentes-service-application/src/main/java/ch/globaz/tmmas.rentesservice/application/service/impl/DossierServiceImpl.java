package ch.globaz.tmmas.rentesservice.application.service.impl;

import ch.globaz.tmmas.rentesservice.application.api.web.resources.DossierResource;
import ch.globaz.tmmas.rentesservice.application.api.web.resources.DroitResource;
import ch.globaz.tmmas.rentesservice.application.event.impl.DomainEventPublisher;
import ch.globaz.tmmas.rentesservice.application.service.DossierService;
import ch.globaz.tmmas.rentesservice.domain.command.CloreDossierCommand;
import ch.globaz.tmmas.rentesservice.domain.command.CreerDossierCommand;
import ch.globaz.tmmas.rentesservice.domain.command.CreerDroitCommand;
import ch.globaz.tmmas.rentesservice.domain.command.ValiderDossierCommand;
import ch.globaz.tmmas.rentesservice.domain.common.specification.Specification;
import ch.globaz.tmmas.rentesservice.domain.event.DossierClotEvent;
import ch.globaz.tmmas.rentesservice.domain.event.DossierCreeEvent;
import ch.globaz.tmmas.rentesservice.domain.event.DossierValideeEvent;
import ch.globaz.tmmas.rentesservice.domain.model.dossier.Dossier;
import ch.globaz.tmmas.rentesservice.domain.model.dossier.DossierStatus;
import ch.globaz.tmmas.rentesservice.domain.model.droit.Droit;
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
	public List<DossierResource> getAll() {
		List<Dossier> dossiers =  repository.allDossiers();

		return dossiers.stream().map(dossier -> new DossierResource.DossierResourceBuilder(dossier)
                .dateValidation(dossier.getDateValidation())
                .dateCloture(dossier.getDateCloture()).build()).collect(Collectors.toList());

	}

	@Transactional
	@Override
	public Optional<DossierResource> getById(Long id) {

		 return repository.dossierById(id).map(dossier -> {

		 	DossierResource res =  new DossierResource.DossierResourceBuilder(dossier)
					.dateValidation(dossier.getDateValidation())
					.dateCloture(dossier.getDateCloture()).build();

		 	return Optional.of(res);

		}).orElseGet(Optional::empty);

	}

	@Transactional
	@Override
	public DossierResource creerDossier(CreerDossierCommand command) {

		Dossier dossier = Dossier.builder(command);

		dossier =  repository.initieDossier(dossier);

		eventPublisher.publishEvent(DossierCreeEvent.fromEntity(dossier));

		return new DossierResource.DossierResourceBuilder(dossier).build();

	}

	@Transactional
	@Override
	public Optional<DossierResource> validerDossier(ValiderDossierCommand command, Long dossierId) {

		Specification spec = new DateValidationPlusRecenteDateEnregistrement(command.getDateValidation())
				.and(new StatusDossierCorrespond(DossierStatus.INITIE));

		return repository.dossierById(dossierId).map(dossier -> {

			if(!spec.isSatisfiedBy(dossier)){
				throw new RegleMetiersNonSatisfaite(spec);
			}

			dossier.validerDossier(command.getDateValidation());

			repository.validerDossier(dossier);

			eventPublisher.publishEvent(DossierValideeEvent.fromEntity(dossier));

			DossierResource dto = new DossierResource.DossierResourceBuilder(dossier)
					.dateValidation(command.getDateValidation()).build();

			return Optional.of(dto);

		}).orElseGet(Optional::empty);


	}

	@Transactional
	@Override
	public Optional<DossierResource> cloreDossier(CloreDossierCommand command, Long dossierId) {

		Specification spec = new DateCloturePlusRecenteDateValidation(command.getDateCloture())
				.and(new StatusDossierCorrespond(DossierStatus.VALIDE));


		return repository.dossierById(dossierId).map(dossier -> {

			if(!spec.isSatisfiedBy(dossier)){
				throw new RegleMetiersNonSatisfaite(spec);
			}

			dossier.cloreDossier(command.getDateCloture());

			repository.cloreDossier(dossier);

			eventPublisher.publishEvent(DossierClotEvent.fromEntity(dossier));

			DossierResource dto = new DossierResource.DossierResourceBuilder(dossier)
					.dateValidation(dossier.getDateValidation())
					.dateCloture(command.getDateCloture()).build();

			return Optional.of(dto);

		}).orElseGet(Optional::empty);

	}




}
