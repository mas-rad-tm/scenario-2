package ch.globaz.tmmas.rentesservice.infrastructure.spi;

import lombok.Getter;
import okhttp3.ResponseBody;

public class PersonnesServiceResponseException extends Exception {



	public PersonnesServiceResponseException(String msg){
		super(msg);
	}
}
