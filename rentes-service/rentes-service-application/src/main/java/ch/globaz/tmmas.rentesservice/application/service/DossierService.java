package ch.globaz.tmmas.rentesservice.application.service;


import ch.globaz.tmmas.rentesservice.application.api.web.resources.DossierResourceAttributes;
import ch.globaz.tmmas.rentesservice.domain.command.*;
import ch.globaz.tmmas.rentesservice.domain.model.dossier.Dossier;
import ch.globaz.tmmas.rentesservice.infrastructure.spi.PersonnesServiceResponseException;
import org.springframework.transaction.annotation.Transactional;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface DossierService {


	List<DossierResourceAttributes> getAll();

    Optional<Dossier> getById(Long id);

	Dossier creerDossier(CreerDossierCommand command) throws IOException, PersonnesServiceResponseException;


	@Transactional
	Dossier creerDossierWithPersonne(CreerDossierWithPersonneCommand command) throws IOException, PersonnesServiceResponseException;

	Optional<DossierResourceAttributes> miseAJourDossier(MiseAJourDossierCommand command, Long dossierId);


}
