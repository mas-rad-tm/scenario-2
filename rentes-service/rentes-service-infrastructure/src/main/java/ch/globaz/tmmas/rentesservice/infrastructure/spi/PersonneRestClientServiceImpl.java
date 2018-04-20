package ch.globaz.tmmas.rentesservice.infrastructure.spi;

import ch.globaz.tmmas.rentesservice.domain.command.CreerDossierWithPersonneCommand;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.stereotype.Component;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


import java.io.IOException;


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
				.addConverterFactory(JacksonConverterFactory.create())
				//.addCallAdapterFactory(Rx.create())
				.client(httpClient.build())
				.baseUrl(ENDPOINT).build();

		client = retrofit.create(PersonnesRestClient.class);

	}


	@Override
	public PersonneMoraleResource getPersonneById(Long personneId) throws IOException {

		Call<PersonneMoraleResource> call = client.getPersonneById(personneId);
		PersonneMoraleResource requerant = call.execute().body();
		return requerant;

	}

	@Override
	public PersonneMoraleResource createDossierwithPersonne(CreerDossierWithPersonneCommand command) throws IOException {

		Call<PersonneMoraleResource> call = client.createPersonne(command.getPersonne());
		PersonneMoraleResource requerant = call.execute().body();
		return requerant;

	}


}
