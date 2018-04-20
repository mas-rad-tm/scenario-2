package ch.globaz.tmmas.rentesservice.application.api.web.controller;

import ch.globaz.tmmas.rentesservice.application.api.web.resources.common.ErrorResponseResource;
import ch.globaz.tmmas.rentesservice.application.api.web.resources.DroitResourceAttributes;
import ch.globaz.tmmas.rentesservice.application.api.web.resources.common.ResourceObject;
import ch.globaz.tmmas.rentesservice.application.api.web.resources.common.ResponseCollectionResource;
import ch.globaz.tmmas.rentesservice.application.api.web.resources.common.ResponseResource;
import ch.globaz.tmmas.rentesservice.application.event.InternalCommandPublisher;
import ch.globaz.tmmas.rentesservice.application.service.DossierService;
import ch.globaz.tmmas.rentesservice.application.service.DroitService;
import ch.globaz.tmmas.rentesservice.domain.command.CreerDroitCommand;
import ch.globaz.tmmas.rentesservice.domain.model.dossier.Dossier;
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
import java.util.Optional;
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

    @RequestMapping(value = "/{dossierId}/droits", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity creerDroit(@Valid @RequestBody CreerDroitCommand command,@PathVariable Long dossierId){

        LOGGER.info("creerDroit pour dossier id:{}, command= {}",dossierId,command);

        commandPublisher.publishCommand(command);

        return droitService.creerDroit(dossierId,command)
            .map(droit -> {
                ResourceObject resourceObject = droit.buildResourceObject();

                putSelfLink(dossierId,resourceObject);
                putLocationHeader(resourceObject);

                return new ResponseEntity<>(new ResponseResource(resourceObject),  HttpStatus.CREATED);
            })
            .orElseGet(() ->  new ResponseEntity(new ErrorResponseResource(HttpStatus.NOT_FOUND,"No entity found with id "
                    + dossierId + ", for dossierId : " + dossierId), HttpStatus.NOT_FOUND));

    }

    @RequestMapping(value = "/{dossierId}/droits", method = RequestMethod.GET)
    public ResponseEntity droitsByDossierId(@PathVariable Long dossierId){

        LOGGER.debug("droitsByDossierId(), {}",dossierId);

        Optional<Dossier> optionalDossier = dossierService.getById(dossierId);


        return optionalDossier
            .map(dossier -> {
                List<DroitResourceAttributes> droitRessource = droitService.getByIdDossier(dossierId).stream().collect(Collectors.toList());

                List<ResourceObject> droitsResourceObject = droitRessource.stream().map(droitResourceAttributes -> {
                    ResourceObject resourceObject = droitResourceAttributes.buildResourceObject();
                    putSelfLink(dossierId,resourceObject);
                    return resourceObject;
                }).collect(Collectors.toList());

                return new ResponseEntity<>(new ResponseCollectionResource(droitsResourceObject), HttpStatus.OK);
            })
            .orElseGet(() ->  new ResponseEntity(new ErrorResponseResource(HttpStatus.NOT_FOUND,"No entity found with id "
                + dossierId + ", for dossierId : " + dossierId), HttpStatus.NOT_FOUND) );

        /**
        if(optionnalDossier.isPresent()){
            List<DroitResourceAttributes> droitRessource = droitService.getByIdDossier(dossierId).stream().collect(Collectors.toList());

            List<PersonneMoraleResource> droitsResourceObject = droitRessource.stream().map(droitResourceAttributes -> {
                PersonneMoraleResource resourceObject = droitResourceAttributes.buildResourceObject();
                putSelfLink(dossierId,resourceObject);
                return resourceObject;
            }).collect(Collectors.toList());

            return new ResponseEntity<>(new ResponseCollectionResource(droitsResourceObject), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new ErrorResponseResource(HttpStatus.NOT_FOUND,"No entity found with id "
                    + dossierId + ", for dossierId : " + dossierId), HttpStatus.NOT_FOUND);
        }
*/

    }

    @RequestMapping(value = "/{dossierId}/droits/{droitId}", method = RequestMethod.GET)
    public ResponseEntity droitById(@PathVariable Long dossierId,@PathVariable Long droitId){

        LOGGER.debug("droitsById(), {}",dossierId);

        Optional<Dossier> dossier = dossierService.getById(dossierId);

        if(dossier.isPresent()){
            return droitService.getById(dossierId,droitId)
                    .map(droit -> {
                        ResourceObject resourceObject = droit.buildResourceObject();

                        putSelfLink(dossierId,resourceObject);
                        LOGGER.debug("geDroitById() return  {}",droit);
                        return new ResponseEntity<>(new ResponseResource(resourceObject), HttpStatus.OK);

                    }).orElseGet(() ->

                            new ResponseEntity(new ErrorResponseResource(HttpStatus.NOT_FOUND,"No entity found with id "
                                    + droitId + ", for dossierId : " + dossierId), HttpStatus.NOT_FOUND));
        }else{
            return new ResponseEntity<>(new ErrorResponseResource(HttpStatus.NOT_FOUND,"No entity found with id "
                    + droitId + ", for dossierId : " + dossierId), HttpStatus.NOT_FOUND);
        }



    }

    private void putSelfLink(Long dossierId, ResourceObject resourceObject) {

        resourceObject.add(linkTo(methodOn(
                DroitController.class).droitById(dossierId,resourceObject.getTechnicalId()))
                .withSelfRel());

        /*
        dossierResource.add(linkTo(methodOn(
                DossiersController.class).miseAJourDossier(dossierResource.getTechnicalId(),null))
                .withRel(VALIDER_PATH));

        dossierResource.add(linkTo(methodOn(
                DossiersController.class).cloreDossier(dossierResource.getTechnicalId(),null))
                .withRel(CLORE_PATH));
        */


    }

    private HttpHeaders putLocationHeader(ResourceObject resourceObject) {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new UriTemplate(DROIT).expand(resourceObject.getTechnicalId()));
        return headers;
    }

}
