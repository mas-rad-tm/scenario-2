package ch.globaz.tmmas.rentesservice.infrastructure.spi;

import ch.globaz.tmmas.rentesservice.domain.command.CreerDossierWithPersonneCommand;
import ch.globaz.tmmas.rentesservice.domain.model.personne.Requerant;
import ch.globaz.tmmas.rentesservice.domain.service.DossierPersonneService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.stereotype.Component;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;



import java.io.IOException;
import java.util.List;


@Component
public class PersonneRestClientServiceImpl implements DossierPersonneService{

	public static final String ENDPOINT = "http://localhost:9010/personnes-service/";
	static PersonnesRestClient client;
	static Retrofit retrofit;

	static{

		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
		httpClient.addInterceptor(interceptor);

		retrofit = new Retrofit.Builder()
				//.addConverterFactory(GsonConverterfactory.create())
				//.addCallAdapterFactory(Rx.create())
				.client(httpClient.build())
				.baseUrl(ENDPOINT).build();

		client = retrofit.create(PersonnesRestClient.class);

	}


	@Override
	public Requerant getPersonneById(Long personneId) throws IOException {

		Call<Requerant> call = client.getPersonneById(personneId);
		Requerant requerant = call.execute().body();
		return requerant;

	}

	@Override
	public Requerant createDossierwithPersonne(CreerDossierWithPersonneCommand command) throws IOException {

		Call<Requerant> call = client.createPersonne(command.getPersonne());
		Requerant requerant = call.execute().body();
		return requerant;

	}


}
