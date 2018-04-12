package ch.globaz.tmmas.personnesservice.application.service.impl;

import ch.globaz.tmmas.personnesservice.application.service.AdressesService;
import ch.globaz.tmmas.personnesservice.domain.command.CreerAdresseCommand;
import ch.globaz.tmmas.personnesservice.domain.factory.AdresseFactory;
import ch.globaz.tmmas.personnesservice.domain.model.Adresse;
import ch.globaz.tmmas.personnesservice.domain.repository.AdressesRepository;
import ch.globaz.tmmas.personnesservice.domain.repository.LocaliteRepository;
import ch.globaz.tmmas.personnesservice.domain.repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class AdressesServiceImpl implements AdressesService {

    @Autowired
    AdressesRepository adressesRepository;

    @Autowired
    LocaliteRepository localiteRepository;

    @Autowired
    PersonneRepository personneRepository;

    @Transactional
    @Override
    public Optional<Adresse> createAdresse(CreerAdresseCommand command) {

        //check si localite existe
        return localiteRepository.findById(command.getLocaliteId())
            .map(localite -> {
                Adresse adresse = AdresseFactory.create(localite,command.getRue(),
                        command.getNumero(),command.getComplement());

                adressesRepository.creerAdresse(adresse);

                return Optional.of(adresse);
            }).orElseGet(Optional::empty);

    }


}
