package ch.globaz.tmmas.rentesservice.infrastructure.spi;

import ch.globaz.tmmas.rentesservice.domain.command.CreerDossierWithPersonneCommand;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PersonnesRestClient {



	@GET("personnes/{personneId}")
	Call<PersonnesServiceResponse> getPersonneById(@Path("personneId") Long personneId);

	@POST("personnes")
	Call<PersonnesServiceResponse> createPersonne(@Body CreerDossierWithPersonneCommand.PersonneCommand command);



}
