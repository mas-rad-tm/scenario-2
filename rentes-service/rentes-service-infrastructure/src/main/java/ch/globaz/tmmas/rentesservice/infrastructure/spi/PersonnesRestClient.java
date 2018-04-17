package ch.globaz.tmmas.rentesservice.infrastructure.spi;

import ch.globaz.tmmas.rentesservice.domain.command.CreerDossierWithPersonneCommand;
import ch.globaz.tmmas.rentesservice.domain.model.personne.DossierRequerant;
import ch.globaz.tmmas.rentesservice.domain.model.personne.Requerant;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface PersonnesRestClient {



	@GET("/personnes/{personneId}")
	Requerant getPersonneById(@Path("personneId") Long personneId);

	@POST("/personnes")
	Requerant createPersonne(@Body CreerDossierWithPersonneCommand.Personne command);



}
