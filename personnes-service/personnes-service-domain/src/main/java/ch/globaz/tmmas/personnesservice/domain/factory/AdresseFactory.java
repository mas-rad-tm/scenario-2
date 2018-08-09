package ch.globaz.tmmas.personnesservice.domain.factory;

import ch.globaz.tmmas.personnesservice.domain.command.CreerAdresseCommand;
import ch.globaz.tmmas.personnesservice.domain.exception.AdresseIncoherenceException;
import ch.globaz.tmmas.personnesservice.domain.model.Adresse;
import ch.globaz.tmmas.personnesservice.domain.model.PersonnePhysique;
import ch.globaz.tmmas.personnesservice.domain.repository.LocaliteRepository;

public class AdresseFactory {


    public static Adresse create(CreerAdresseCommand command, PersonnePhysique personnePhysique, LocaliteRepository localiteRepository) throws AdresseIncoherenceException {




        //if(personnePhysique.isPresent()){
            return localiteRepository.findById(command.getLocaliteId()).map(localite -> {


                return new Adresse(localite, personnePhysique,command.getRue(),command.getNumero(),command.getComplement
                        (), command.getDateDebutValidite());

            }).orElseThrow(() -> {
                return new AdresseIncoherenceException("La localite avec l'id "  + command.getLocaliteId() + " n'existe pas");
            });
       // }else{
        //    throw new AdresseIncoherenceException("La personne avec l'id "  + personneId + " n'existe pas");
        //}


    }


}
