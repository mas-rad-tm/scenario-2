package ch.globaz.tmmas.rentesservice.application.service;


import ch.globaz.tmmas.rentesservice.application.api.web.resources.DossierResourceAttributes;
import ch.globaz.tmmas.rentesservice.domain.command.*;
import ch.globaz.tmmas.rentesservice.domain.model.dossier.Dossier;
import ch.globaz.tmmas.rentesservice.infrastructure.spi.PersonnesServiceResponseException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

public interface DossierService {


	List<DossierResourceAttributes> getAll();

    Optional<Dossier> getById(Long id);

	Dossier creerDossier(CreerDossierCommand command) throws IOException, PersonnesServiceResponseException;


	@Transactional
	Dossier creerDossierWithPersonne(CreerDossierWithPersonneCommand command, CopyOnWriteArrayList<SseEmitter> consommateurs) throws IOException, PersonnesServiceResponseException;

	Optional<DossierResourceAttributes> miseAJourDossier(MiseAJourDossierCommand command, Long dossierId);


}
