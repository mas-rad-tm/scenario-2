package ch.globaz.tmmas.rentesservice.application.service.impl;

import ch.globaz.tmmas.rentesservice.application.api.web.resources.DroitResourceAttributes;
import ch.globaz.tmmas.rentesservice.application.event.InternalEventPublisher;
import ch.globaz.tmmas.rentesservice.application.service.DroitService;
import ch.globaz.tmmas.rentesservice.domain.command.CreerDroitCommand;
import ch.globaz.tmmas.rentesservice.domain.event.DroitCreeEvent;
import ch.globaz.tmmas.rentesservice.domain.factory.DroitFactory;
import ch.globaz.tmmas.rentesservice.domain.model.dossier.Dossier;
import ch.globaz.tmmas.rentesservice.domain.model.droit.Droit;
import ch.globaz.tmmas.rentesservice.domain.repository.DossierRepository;
import ch.globaz.tmmas.rentesservice.domain.repository.DroitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DroitServiceImpl implements DroitService{

    @Autowired
    private DroitRepository droitRepository;

    @Autowired
    private DossierRepository dossierRepository;

    @Autowired
    InternalEventPublisher eventPublisher;

    @Transactional
    @Override
    public List<DroitResourceAttributes> getByIdDossier(Long id) {

        return droitRepository.getDroitByIdDossier(id).stream().map(droit -> {

            return new DroitResourceAttributes(droit);
        })
                .collect(Collectors.toList());



    }

    @Transactional
    @Override
    public Optional<DroitResourceAttributes> creerDroit(Long dossierId, CreerDroitCommand command) {


        return dossierRepository.dossierById(dossierId)
            .map(dossier -> {
                Droit droit = DroitFactory.create(command,dossier);

                droitRepository.initieDroit(droit);

                eventPublisher.publishEvent(DroitCreeEvent.fromEntity(droit));

                DroitResourceAttributes res =  new DroitResourceAttributes(droit);

                return Optional.of(res);
            })
            .orElseGet(Optional::empty);

    }

    @Transactional
    @Override
    public Optional<DroitResourceAttributes> getById(Long dossierId, Long droitId) {

        //Optional<Dossier> optionalFossier = dossierRepository.dossierById(dossierId);
        //Dossier dossier = optionalFossier.get();

        return droitRepository.getDroitById(droitId).map(droit -> {

            DroitResourceAttributes res =  new DroitResourceAttributes(droit);

            return Optional.of(res);

        }).orElseGet(Optional::empty);


    }
}
