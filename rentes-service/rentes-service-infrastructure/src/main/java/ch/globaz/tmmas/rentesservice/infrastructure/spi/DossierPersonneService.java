package ch.globaz.tmmas.rentesservice.infrastructure.spi;

import ch.globaz.tmmas.rentesservice.domain.command.CreerDossierWithPersonneCommand;

import java.io.IOException;

public interface DossierPersonneService {


	PersonneMoraleResource getPersonneById(Long personneId) throws IOException, PersonnesServiceResponseException;

	PersonneMoraleResource createDossierwithPersonne(CreerDossierWithPersonneCommand command) throws IOException, PersonnesServiceResponseException;
}
