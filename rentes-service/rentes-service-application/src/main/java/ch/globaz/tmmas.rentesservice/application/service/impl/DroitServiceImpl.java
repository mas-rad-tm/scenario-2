package ch.globaz.tmmas.rentesservice.application.service.impl;

import ch.globaz.tmmas.rentesservice.application.api.web.resources.DroitResource;
import ch.globaz.tmmas.rentesservice.application.service.DroitService;
import ch.globaz.tmmas.rentesservice.domain.command.CreerDroitCommand;
import ch.globaz.tmmas.rentesservice.domain.model.dossier.Dossier;
import ch.globaz.tmmas.rentesservice.domain.model.droit.Droit;
import ch.globaz.tmmas.rentesservice.domain.repository.DossierRepository;
import ch.globaz.tmmas.rentesservice.domain.repository.DroitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DroitServiceImpl implements DroitService{

    @Autowired
    private DroitRepository droitRepository;

    @Autowired
    private DossierRepository dossierRepository;

    @Transactional
    @Override
    public List<DroitResource> getByIdDossier(Long id) {

        return droitRepository.getDroitByIdDossier(id).stream().map(droit -> {

            return new DroitResource.DroitResourceBuilder(droit).build();
        })
                .collect(Collectors.toList());



    }

    @Transactional
    @Override
    public DroitResource creerDroit(Long dossierId, CreerDroitCommand command) {

        Optional<Dossier> optionalFossier = dossierRepository.dossierById(dossierId);
        Dossier dossier = optionalFossier.get();

        Droit droit = Droit.builder(command,dossier);

        droitRepository.initieDroit(droit);


        return new DroitResource.DroitResourceBuilder(droit).build();

    }

    @Transactional
    @Override
    public Optional<DroitResource> getById(Long dossierId, Long droitId) {

        Optional<Dossier> optionalFossier = dossierRepository.dossierById(dossierId);
        Dossier dossier = optionalFossier.get();

        return droitRepository.getDroitById(droitId).map(droit -> {

            DroitResource res =  new DroitResource.DroitResourceBuilder(droit)
                   .build();

            return Optional.of(res);

        }).orElseGet(Optional::empty);


    }
}
