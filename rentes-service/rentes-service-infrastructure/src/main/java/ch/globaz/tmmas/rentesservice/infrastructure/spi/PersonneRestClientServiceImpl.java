package ch.globaz.tmmas.rentesservice.infrastructure.spi;

import ch.globaz.tmmas.rentesservice.domain.command.CreerDossierWithPersonneCommand;
import ch.globaz.tmmas.rentesservice.domain.model.personne.DossierRequerant;
import ch.globaz.tmmas.rentesservice.domain.model.personne.Requerant;
import ch.globaz.tmmas.rentesservice.domain.service.DossierPersonneService;
import org.springframework.stereotype.Component;
import retrofit.RestAdapter;

@Component
public class PersonneRestClientServiceImpl implements DossierPersonneService{

	public static final String ENDPOINT = "http://localhost:9010/personnes-service";


	static PersonnesRestClient client = new RestAdapter.Builder().setEndpoint(ENDPOINT).build().create
			(PersonnesRestClient.class);

	@Override
	public Requerant getPersonneById(Long personneId){

		return client.getPersonneById(personneId);

	}

	@Override
	public Requerant createDossierwithPersonne(CreerDossierWithPersonneCommand command){

		return client.createPersonne(command.getPersonne());

	}
}
