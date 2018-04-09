package ch.globaz.tmmas.rentesservice.domain.factory;

import ch.globaz.tmmas.rentesservice.domain.command.CreerDossierCommand;
import ch.globaz.tmmas.rentesservice.domain.command.CreerDroitCommand;
import ch.globaz.tmmas.rentesservice.domain.model.dossier.Dossier;
import ch.globaz.tmmas.rentesservice.domain.model.droit.Droit;

import java.time.ZonedDateTime;

public class DroitFactory {

	public static Droit create(CreerDroitCommand command, Dossier dossier){

		ZonedDateTime dateDebutDroit = (command.getDateDebutDroit() != null)
				? command.getDateDebutDroit() : ZonedDateTime.now();


		return new Droit(dateDebutDroit,dossier);

	};
}
