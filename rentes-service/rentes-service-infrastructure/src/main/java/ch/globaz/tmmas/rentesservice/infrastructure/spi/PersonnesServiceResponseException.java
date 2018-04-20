package ch.globaz.tmmas.rentesservice.infrastructure.spi;

import lombok.Getter;
import okhttp3.ResponseBody;

public class PersonnesServiceResponseException extends Exception {

	@Getter
	private ResponseBody errorBody;

	public PersonnesServiceResponseException(ResponseBody msg){
		super();
		this.errorBody = msg;
	}
}
