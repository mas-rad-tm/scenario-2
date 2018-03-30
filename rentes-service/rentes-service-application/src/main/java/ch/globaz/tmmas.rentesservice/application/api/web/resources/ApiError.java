package ch.globaz.tmmas.rentesservice.application.api.web.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Ressources utilisées pour toutes les exceptions générés au niveau des requêtes REST
 */
@Getter
public class ApiError {

	private HttpStatus status;
	private String message;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<String> erreurs;

	public ApiError(HttpStatus status, String message, List<String> erreurs) {
		this.erreurs = erreurs;
		this.message = message;
		this.status = status;
	}

	public ApiError(HttpStatus status, String message, String erreur) {
		this(status,message, Arrays.asList(erreur));
	}

	public ApiError(HttpStatus status, String message) {
		this.message = message;
		this.status = status;
	}

}
