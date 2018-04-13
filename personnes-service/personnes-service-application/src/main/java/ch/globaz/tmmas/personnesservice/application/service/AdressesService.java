package ch.globaz.tmmas.personnesservice.application.service;

import ch.globaz.tmmas.personnesservice.domain.command.CreerAdresseCommand;
import ch.globaz.tmmas.personnesservice.domain.exception.AdresseIncoherenceException;
import ch.globaz.tmmas.personnesservice.domain.model.Adresse;

public interface AdressesService {

    Adresse createAdresse(CreerAdresseCommand adresse, Long personneId) throws AdresseIncoherenceException;


}
