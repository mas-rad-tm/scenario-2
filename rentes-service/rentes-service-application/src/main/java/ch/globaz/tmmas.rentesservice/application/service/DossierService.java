package ch.globaz.tmmas.rentesservice.application.service;


import ch.globaz.tmmas.rentesservice.application.api.web.resources.DossierResourceAttributes;
import ch.globaz.tmmas.rentesservice.domain.command.*;
import ch.globaz.tmmas.rentesservice.domain.model.dossier.Dossier;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

public interface DossierService {


	List<DossierResourceAttributes> getAll();

    Optional<Dossier> getById(Long id);

	Dossier creerDossier(CreerDossierCommand command);


	@Transactional
	Dossier creerDossierWithPersonne(CreerDossierWithPersonneCommand command);

	Optional<DossierResourceAttributes> miseAJourDossier(MiseAJourDossierCommand command, Long dossierId);


}
