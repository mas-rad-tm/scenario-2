package ch.globaz.tmmas.rentesservice.application.api.web.exception;

import ch.globaz.tmmas.rentesservice.application.api.web.resources.ApiError;
import ch.globaz.tmmas.rentesservice.application.service.impl.RegleMetiersNonSatisfaite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe gérant les diverses exceptions pouvant être généré lors du traitement de la requête REST
 */
@ControllerAdvice
class RestControllerBusinessRulesExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestControllerBusinessRulesExceptionHandler.class);



    @ExceptionHandler(RegleMetiersNonSatisfaite.class)
    protected ResponseEntity<Object> handleReglesMetiersException(RegleMetiersNonSatisfaite ex){

        ApiError errors = new ApiError(HttpStatus.CONFLICT,ex.getMessage(),ex.getReglesMetiersNonStaisfaite());

        return ResponseEntity
                .badRequest()
                .body(errors);
    }

}
