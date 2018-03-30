package ch.globaz.tmmas.rentesservice.application.api.web.controller;

import ch.globaz.tmmas.rentesservice.application.api.web.resources.ApiError;
import ch.globaz.tmmas.rentesservice.application.api.web.resources.DossierResource;
import ch.globaz.tmmas.rentesservice.application.api.web.resources.DroitResource;
import ch.globaz.tmmas.rentesservice.application.event.InternalCommandPublisher;
import ch.globaz.tmmas.rentesservice.application.service.DossierService;
import ch.globaz.tmmas.rentesservice.application.service.DroitService;
import ch.globaz.tmmas.rentesservice.domain.command.CreerDroitCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.UriTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/dossiers")
public class DroitController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DroitController.class);

    @Autowired
    DossierService dossierService;

    @Autowired
    DroitService droitService;

    @Autowired
    InternalCommandPublisher commandPublisher;

    private static final String DROITS = "/droits";
    private static final String DROIT = DROITS + "/{id}";

    @RequestMapping(value = "/{dossierId}/droits", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity creerDroit(@Valid @RequestBody CreerDroitCommand command,@PathVariable Long dossierId){

        LOGGER.info("creerDroit pour dossier id:{}, command= {}",dossierId,command);

        commandPublisher.publishCommand(command);

        DroitResource droitResource = droitService.creerDroit(dossierId,command);

        putSelfLink(droitResource);

        return new ResponseEntity<>(droitResource,  HttpStatus.CREATED);

    }

    @RequestMapping(value = "/{dossierId}/droits", method = RequestMethod.GET)
    public ResponseEntity droitsByDossierId(@PathVariable Long dossierId){

        LOGGER.debug("droitsByDossierId(), {}",dossierId);


        List<DroitResource> droitRessource = droitService.getByIdDossier(dossierId).stream().collect(Collectors.toList());

        droitRessource.stream().forEach(this::putSelfLink);

        return new ResponseEntity<>(droitRessource, HttpStatus.OK);

    }

    @RequestMapping(value = "/{dossierId}/droits/{droitId}", method = RequestMethod.GET)
    public ResponseEntity droitById(@PathVariable Long dossierId,@PathVariable Long droitId){

        LOGGER.debug("droitsById(), {}",dossierId);


        return droitService.getById(dossierId,droitId)
                .map(droit -> {

            putSelfLink(droit);
            LOGGER.debug("geDroitById() return  {}",droit);
            return new ResponseEntity<>(droit, HttpStatus.OK);

        }).orElseGet(() ->

            new ResponseEntity(new ApiError(HttpStatus.NOT_FOUND,"No entity found with id "
                + droitId + ", for dossierId : " + dossierId), HttpStatus.NOT_FOUND));

    }

    private DroitResource putSelfLink(DroitResource droitResource) {

        droitResource.add(linkTo(methodOn(
                DossiersController.class).dossierById(droitResource.getTechnicalId()))
                .withSelfRel());

        /*
        dossierResource.add(linkTo(methodOn(
                DossiersController.class).validerDossier(dossierResource.getTechnicalId(),null))
                .withRel(VALIDER_PATH));

        dossierResource.add(linkTo(methodOn(
                DossiersController.class).cloreDossier(dossierResource.getTechnicalId(),null))
                .withRel(CLORE_PATH));
        */

        return droitResource;
    }

    private HttpHeaders putLocationHeader(DossierResource dossierResource) {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new UriTemplate(DROIT).expand(dossierResource.getTechnicalId()));
        return headers;
    }

}
