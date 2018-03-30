package ch.globaz.tmmas.rentesservice.application.service;

import ch.globaz.tmmas.rentesservice.application.api.web.resources.DroitResource;
import ch.globaz.tmmas.rentesservice.domain.command.CreerDroitCommand;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface DroitService {
    @Transactional
    List<DroitResource> getByIdDossier(Long id);

    @Transactional
    DroitResource creerDroit(Long dossierId, CreerDroitCommand command);

    @Transactional
    Optional<DroitResource> getById(Long dossierId, Long droitId);


}
