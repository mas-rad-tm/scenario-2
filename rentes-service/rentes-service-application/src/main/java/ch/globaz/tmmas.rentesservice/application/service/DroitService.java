package ch.globaz.tmmas.rentesservice.application.service;

import ch.globaz.tmmas.rentesservice.application.api.web.resources.DroitResourceAttributes;
import ch.globaz.tmmas.rentesservice.domain.command.CreerDroitCommand;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface DroitService {
    @Transactional
    List<DroitResourceAttributes> getByIdDossier(Long id);

    @Transactional
    DroitResourceAttributes creerDroit(Long dossierId, CreerDroitCommand command);

    @Transactional
    Optional<DroitResourceAttributes> getById(Long dossierId, Long droitId);


}
