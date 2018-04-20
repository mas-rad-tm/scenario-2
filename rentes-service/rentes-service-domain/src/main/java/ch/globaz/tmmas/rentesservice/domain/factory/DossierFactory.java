package ch.globaz.tmmas.rentesservice.domain.factory;

import ch.globaz.tmmas.rentesservice.domain.command.CreerDossierCommand;
import ch.globaz.tmmas.rentesservice.domain.command.CreerDossierWithPersonneCommand;
import ch.globaz.tmmas.rentesservice.domain.model.dossier.Dossier;

import java.time.ZonedDateTime;

public class DossierFactory {

    public static Dossier create(CreerDossierCommand command){

        ZonedDateTime dateEnregistrement = (command.getDateEnregistrement() != null)
                ? command.getDateEnregistrement() : ZonedDateTime.now();


        return new Dossier(dateEnregistrement,command.getRequerantId());

    };


    public static Dossier create(CreerDossierWithPersonneCommand.DossierCommand command, Long personneId){

        ZonedDateTime dateEnregistrement = (command.getDateEnregistrement() != null)
                ? command.getDateEnregistrement() : ZonedDateTime.now();


        return new Dossier(dateEnregistrement,personneId);

    };
}
