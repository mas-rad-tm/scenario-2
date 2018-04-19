package ch.globaz.tmmas.rentesservice.domain.service;

import ch.globaz.tmmas.rentesservice.domain.command.CreerDossierWithPersonneCommand;
import ch.globaz.tmmas.rentesservice.domain.model.personne.DossierRequerant;
import ch.globaz.tmmas.rentesservice.domain.model.personne.Requerant;

import java.io.IOException;

public interface DossierPersonneService {


	Requerant getPersonneById(Long personneId) throws IOException;

	Requerant createDossierwithPersonne(CreerDossierWithPersonneCommand command) throws IOException;
}
