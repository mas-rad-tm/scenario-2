package ch.globaz.tmmas.personnesservice.application.exception;

import ch.globaz.tmmas.personnesservice.application.api.web.resources.common.ErrorResponseResource;
import ch.globaz.tmmas.personnesservice.domain.exception.AdresseIncoherenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Classe gérant les diverses exceptions pouvant être généré lors du traitement de la requête REST
 */
@ControllerAdvice
class RestControllerBusinessRulesExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestControllerBusinessRulesExceptionHandler.class);





    @ExceptionHandler(AdresseIncoherenceException.class)
    protected ResponseEntity<Object> handleAdresseIncoherenceException(AdresseIncoherenceException ex){

        ErrorResponseResource errors = new ErrorResponseResource(HttpStatus.CONFLICT,ex.getMessage(),ex.getMessage());

        return ResponseEntity
                .badRequest()
                .body(errors);
    }

}
