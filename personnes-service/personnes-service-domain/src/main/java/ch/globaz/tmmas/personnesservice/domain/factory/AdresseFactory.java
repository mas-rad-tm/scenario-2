package ch.globaz.tmmas.personnesservice.domain.factory;

import ch.globaz.tmmas.personnesservice.domain.command.CreerAdresseCommand;
import ch.globaz.tmmas.personnesservice.domain.model.Adresse;
import ch.globaz.tmmas.personnesservice.domain.model.Localite;

import java.time.ZonedDateTime;

public class AdresseFactory {

    public static Adresse create(Localite localite, String rue, Integer numero, String complement){
        return new Adresse(localite,rue,numero,complement,ZonedDateTime.now());
    }
}
