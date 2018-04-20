package ch.globaz.tmmas.rentesservice.infrastructure.spi;

import ch.globaz.tmmas.rentesservice.domain.command.CreerDossierWithPersonneCommand;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import retrofit2.Call;
import retrofit2.Response;
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

		Call<PersonnesServiceResponse> call = client.getPersonneById(personneId);
		PersonneMoraleResource requerant = call.execute().body().getData();
		return requerant;

	}

	@Override
	public PersonneMoraleResource createDossierwithPersonne(CreerDossierWithPersonneCommand command) throws IOException, PersonnesServiceResponseException {

		Call<PersonnesServiceResponse> call = client.createPersonne(command.getPersonneCommand());

		Response reponse = call.execute();

		//TODO a checker
		reponse.errorBody().string();

		if(reponse.code() != HttpStatus.OK.value()){
			throw new PersonnesServiceResponseException(reponse.errorBody());
		}

		PersonneMoraleResource requerant = call.execute().body().getData();
		return requerant;

	}


}
