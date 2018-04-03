package ch.globaz.tmmas.rentesservice.application.api.web.controller;


import ch.globaz.tmmas.rentesservice.application.api.web.resources.*;
import ch.globaz.tmmas.rentesservice.application.api.web.resources.common.ErrorResponseResource;
import ch.globaz.tmmas.rentesservice.application.api.web.resources.common.ResourceObject;
import ch.globaz.tmmas.rentesservice.application.api.web.resources.common.ResponseCollectionResource;
import ch.globaz.tmmas.rentesservice.application.api.web.resources.common.ResponseResource;
import ch.globaz.tmmas.rentesservice.application.event.InternalCommandPublisher;
import ch.globaz.tmmas.rentesservice.application.service.DossierService;
import ch.globaz.tmmas.rentesservice.application.service.DroitService;
import ch.globaz.tmmas.rentesservice.domain.command.CloreDossierCommand;
import ch.globaz.tmmas.rentesservice.domain.command.CreerDossierCommand;
import ch.globaz.tmmas.rentesservice.domain.command.ValiderDossierCommand;
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


/**
 * Controlleur pour la gestion des dossiers
 */
@RestController
@RequestMapping("/dossiers")
class DossiersController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DossiersController.class);
	private static final String DOSSIERS = "/dossiers";
	private static final String DOSSIER = DOSSIERS + "/{id}";
	private static final String VALIDER_PATH = "valider";
	private static final String CLORE_PATH = "clore";


	@Autowired
	DossierService dossierService;

	@Autowired
	DroitService droitService;

	@Autowired
	InternalCommandPublisher commandPublisher;


	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity creerDossier(@Valid @RequestBody CreerDossierCommand command){

		LOGGER.info("creerDossier(), command= {}",command);

		commandPublisher.publishCommand(command);

		ResourceObject dossierResource = new DossierResourceAttributes(dossierService.creerDossier(command)).buildResourceObject();

		putSelfLink(dossierResource);

		return new ResponseEntity<>(new ResponseResource(dossierResource), putLocationHeader(dossierResource), HttpStatus.CREATED);

	}

	@RequestMapping(value = "/{dossierId}/valider", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity validerDossier(@PathVariable Long dossierId,@Valid @RequestBody ValiderDossierCommand
			validerDossierCommand){

		LOGGER.info("cloreDossier(), command={}",validerDossierCommand);

		commandPublisher.publishCommand(validerDossierCommand);

		Optional<DossierResourceAttributes> optionnalDossier = dossierService.validerDossier(validerDossierCommand,dossierId);

		if(optionnalDossier.isPresent()){

			DossierResourceAttributes dossierResourceAttributes = optionnalDossier.get();
			ResourceObject res = dossierResourceAttributes.buildResourceObject();
			putSelfLink(res);

			return new ResponseEntity<>(new ResponseResource(res),  HttpStatus.OK);

		}

		return new ResponseEntity<>(new ErrorResponseResource(HttpStatus.NOT_FOUND,"No entity found with id " +
				dossierId), HttpStatus.NOT_FOUND);

	}


	@RequestMapping(value = "/{dossierId}/clore", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity cloreDossier(@PathVariable Long dossierId,@Valid @RequestBody CloreDossierCommand
			cloreDossierCommand){

		LOGGER.info("cloreDossier(), command={}",cloreDossierCommand);

		commandPublisher.publishCommand(cloreDossierCommand);

		Optional<DossierResourceAttributes> optionnalDossier = dossierService.cloreDossier(cloreDossierCommand,dossierId);

		if(optionnalDossier.isPresent()){

			DossierResourceAttributes dossierResourceAttributes = optionnalDossier.get();
			ResourceObject res = dossierResourceAttributes.buildResourceObject();
			putSelfLink(res);

			return new ResponseEntity<>(new ResponseResource(res),  HttpStatus.OK);

		}

		return new ResponseEntity<>(new ErrorResponseResource(HttpStatus.NOT_FOUND,"No entity found with id " +
				dossierId), HttpStatus.NOT_FOUND);

	}



	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity allDossiers(){

		List<DossierResourceAttributes> dossiersResource = dossierService.getAll();

		List<ResourceObject> list = dossiersResource.stream().map(dossierResourceAttributes -> {
			ResourceObject resourceObject =  dossierResourceAttributes.buildResourceObject();
			putSelfLink(resourceObject);
			return resourceObject;
		}).collect(Collectors.toList());

		return new ResponseEntity<>(new ResponseCollectionResource(list), HttpStatus.OK);
	}


	@RequestMapping(value = "/{dossierId}", method = RequestMethod.GET)
	public ResponseEntity dossierById(@PathVariable Long dossierId){

		LOGGER.debug("dossierById(), {}",dossierId);


		return dossierService.getById(dossierId)
				.map(dossier -> {

					ResourceObject res = new DossierResourceAttributes(dossier).buildResourceObject();
					putSelfLink(res);


					LOGGER.debug("getDossierById() return  {}",dossier);
					return new ResponseEntity<>(new ResponseResource(res), HttpStatus.OK);

				}).orElseGet(() -> new ResponseEntity(new ErrorResponseResource(HttpStatus.NOT_FOUND,"No entity found with id " + dossierId)
                , HttpStatus.NOT_FOUND));



	}





	private void putSelfLink(ResourceObject dossierResource) {

		dossierResource.add(linkTo(methodOn(
					DossiersController.class).dossierById(dossierResource.getTechnicalId()))
					.withSelfRel());

		dossierResource.add(linkTo(methodOn(
				DossiersController.class).validerDossier(dossierResource.getTechnicalId(),null))
				.withRel(VALIDER_PATH));

		dossierResource.add(linkTo(methodOn(
				DossiersController.class).cloreDossier(dossierResource.getTechnicalId(),null))
				.withRel(CLORE_PATH));


	}

	private HttpHeaders putLocationHeader(ResourceObject dossierResource) {
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(new UriTemplate(DOSSIER).expand(dossierResource.getTechnicalId()));
		return headers;
	}


}
