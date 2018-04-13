package ch.globaz.tmmas.personnesservice.application.api.web.controller;

import ch.globaz.tmmas.personnesservice.application.api.web.resources.AdresseResourceAttributes;
import ch.globaz.tmmas.personnesservice.application.api.web.resources.common.ErrorResponseResource;
import ch.globaz.tmmas.personnesservice.application.api.web.resources.common.ResourceObject;
import ch.globaz.tmmas.personnesservice.application.api.web.resources.common.ResponseResource;
import ch.globaz.tmmas.personnesservice.application.service.AdressesService;
import ch.globaz.tmmas.personnesservice.domain.command.CreerAdresseCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriTemplate;

import javax.validation.Valid;

@RestController
@RequestMapping("/adresses")
public class AdressesController {

    private static final String ADRESSES = "/personnes/{id}/adresses/{id}";

    private static final Logger LOGGER = LoggerFactory.getLogger(AdressesController.class);

    @Autowired
    AdressesService adressesService;

    /**
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity creerDossier(@Valid @RequestBody CreerAdresseCommand command){
        LOGGER.info("creerAdresses(): {}", command);

        return adressesService.createAdresse(command).map(adresse -> {
            ResourceObject adresseResource = new AdresseResourceAttributes(
                    adresse)
                    .buildResourceObject();


            return new ResponseEntity<>(new ResponseResource(adresseResource), putLocationHeader(adresseResource), HttpStatus.CREATED);

        }).orElseGet(()->
            new ResponseEntity(new ErrorResponseResource(HttpStatus.NOT_FOUND,"No localite found with id "
                    + command.getLocaliteId()),HttpStatus.NOT_FOUND));

    }*/

    private HttpHeaders putLocationHeader(ResourceObject adresseResource) {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new UriTemplate(ADRESSES).expand(adresseResource.getTechnicalId()));
        return headers;
    }
}
