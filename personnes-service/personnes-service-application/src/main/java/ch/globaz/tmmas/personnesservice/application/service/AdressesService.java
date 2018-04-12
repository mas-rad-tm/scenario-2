package ch.globaz.tmmas.personnesservice.application.service;

import ch.globaz.tmmas.personnesservice.domain.command.CreerAdresseCommand;
import ch.globaz.tmmas.personnesservice.domain.model.Adresse;

import java.util.Optional;

public interface AdressesService {

    Optional<Adresse> createAdresse(CreerAdresseCommand adresse);


}
