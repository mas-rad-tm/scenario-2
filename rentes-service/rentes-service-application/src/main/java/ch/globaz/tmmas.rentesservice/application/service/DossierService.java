package ch.globaz.tmmas.rentesservice.application.service;


import ch.globaz.tmmas.rentesservice.application.api.web.resources.DossierResourceAttributes;
import ch.globaz.tmmas.rentesservice.domain.command.CloreDossierCommand;
import ch.globaz.tmmas.rentesservice.domain.command.CreerDossierCommand;
import ch.globaz.tmmas.rentesservice.domain.command.MiseAJourDossierCommand;
import ch.globaz.tmmas.rentesservice.domain.command.ValiderDossierCommand;
import ch.globaz.tmmas.rentesservice.domain.model.dossier.Dossier;


import java.util.List;
import java.util.Optional;

public interface DossierService {


	List<DossierResourceAttributes> getAll();

    Optional<Dossier> getById(Long id);

	Dossier creerDossier(CreerDossierCommand command);



	Optional<DossierResourceAttributes> miseAJourDossier(MiseAJourDossierCommand command, Long dossierId);


}
