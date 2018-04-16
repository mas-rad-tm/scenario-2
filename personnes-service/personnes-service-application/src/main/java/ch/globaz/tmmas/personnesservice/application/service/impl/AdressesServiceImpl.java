package ch.globaz.tmmas.personnesservice.application.service.impl;

import ch.globaz.tmmas.personnesservice.application.service.AdressesService;
import ch.globaz.tmmas.personnesservice.domain.command.CreerAdresseCommand;
import ch.globaz.tmmas.personnesservice.domain.exception.AdresseIncoherenceException;
import ch.globaz.tmmas.personnesservice.domain.factory.AdresseFactory;
import ch.globaz.tmmas.personnesservice.domain.model.Adresse;
import ch.globaz.tmmas.personnesservice.domain.model.PersonneMorale;
import ch.globaz.tmmas.personnesservice.domain.repository.AdressesRepository;
import ch.globaz.tmmas.personnesservice.domain.repository.LocaliteRepository;
import ch.globaz.tmmas.personnesservice.domain.repository.PersonneRepository;
import ch.globaz.tmmas.personnesservice.domain.service.PersonneMoraleCoherence;
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
    public Adresse createAdresse(CreerAdresseCommand command, Long personneId) throws AdresseIncoherenceException {


        PersonneMorale personneMorale = personneRepository.getPersonneById(personneId).get();
        personneMorale.setAdresseActive(
                adressesRepository.getAdresseActiveByPersonne(personneId)
                .orElseGet(()->{
                    return null;
                })
        );

        //création de la nouvelle adresse
        Adresse nouvelleAdresse = AdresseFactory.create(command,personneMorale,localiteRepository);


/**
        try{
            System.out.println(personneMorale.getAdresses());
        }catch (Exception e ){
            e.printStackTrace();
        }
*/
        nouvelleAdresse = PersonneMoraleCoherence.addAdresseToPersonneMorale(personneMorale,nouvelleAdresse,
                adressesRepository);

        //personneRepository.mettreAJour(personneMorale);
        
        return nouvelleAdresse;

    }


}
