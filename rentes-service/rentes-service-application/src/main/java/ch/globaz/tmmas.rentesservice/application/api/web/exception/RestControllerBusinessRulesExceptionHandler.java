package ch.globaz.tmmas.rentesservice.application.api.web.exception;

import ch.globaz.tmmas.rentesservice.application.api.web.resources.common.ErrorResponseResource;
import ch.globaz.tmmas.rentesservice.application.service.impl.RegleMetiersNonSatisfaite;
import ch.globaz.tmmas.rentesservice.infrastructure.spi.PersonnesServiceResponseException;
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



    @ExceptionHandler(RegleMetiersNonSatisfaite.class)
    protected ResponseEntity<Object> handleReglesMetiersException(RegleMetiersNonSatisfaite ex){

        ErrorResponseResource errors = new ErrorResponseResource(HttpStatus.CONFLICT,ex.getMessage(),ex.getReglesMetiersNonStaisfaite());

        return ResponseEntity
                .badRequest()
                .body(errors);
    }

    @ExceptionHandler(PersonnesServiceResponseException.class)
    protected ResponseEntity<Object> handleReglesMetiersException(PersonnesServiceResponseException ex){

        ErrorResponseResource errors = new ErrorResponseResource(HttpStatus.CONFLICT,ex.getCause().getMessage(),ex.getMessage());

        return ResponseEntity
                .badRequest()
                .body(errors);
    }



}
