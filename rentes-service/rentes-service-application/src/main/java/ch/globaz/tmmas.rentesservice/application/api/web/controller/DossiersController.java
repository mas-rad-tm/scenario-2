package ch.globaz.tmmas.rentesservice.application.api.web.controller;


import ch.globaz.tmmas.rentesservice.application.api.web.resources.ApiError;
import ch.globaz.tmmas.rentesservice.application.api.web.resources.DossierResource;
import ch.globaz.tmmas.rentesservice.application.api.web.resources.DroitResource;
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

		DossierResource dossierResource = dossierService.creerDossier(command);

		putSelfLink(dossierResource);

		return new ResponseEntity<>(dossierResource, putLocationHeader(dossierResource), HttpStatus.CREATED);

	}

	@RequestMapping(value = "/{dossierId}/valider", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity validerDossier(@PathVariable Long dossierId,@Valid @RequestBody ValiderDossierCommand
			validerDossierCommand){

		LOGGER.info("cloreDossier(), command={}",validerDossierCommand);

		commandPublisher.publishCommand(validerDossierCommand);

		Optional<DossierResource> optionnalDossier = dossierService.validerDossier(validerDossierCommand,dossierId);

		if(optionnalDossier.isPresent()){

			DossierResource dossierResource = optionnalDossier.get();
			putSelfLink(dossierResource);

			return new ResponseEntity<>(dossierResource,  HttpStatus.OK);

		}

		return new ResponseEntity<>(new ApiError(HttpStatus.NOT_FOUND,"No entity found with id " +
				dossierId), HttpStatus.NOT_FOUND);

	}


	@RequestMapping(value = "/{dossierId}/clore", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity cloreDossier(@PathVariable Long dossierId,@Valid @RequestBody CloreDossierCommand
			cloreDossierCommand){

		LOGGER.info("cloreDossier(), command={}",cloreDossierCommand);

		commandPublisher.publishCommand(cloreDossierCommand);

		Optional<DossierResource> optionnalDossier = dossierService.cloreDossier(cloreDossierCommand,dossierId);

		if(optionnalDossier.isPresent()){

			DossierResource dossierResource = optionnalDossier.get();
			putSelfLink(dossierResource);

			return new ResponseEntity<>(dossierResource,  HttpStatus.OK);

		}

		return new ResponseEntity<>(new ApiError(HttpStatus.NOT_FOUND,"No entity found with id " +
				dossierId), HttpStatus.NOT_FOUND);

	}



	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<DossierResource>> allDossiers(){

		List<DossierResource> dossiersResource = dossierService.getAll();

		dossiersResource.stream().forEach(this::putSelfLink);

		return new ResponseEntity<>(dossiersResource, HttpStatus.OK);
	}


	@RequestMapping(value = "/{dossierId}", method = RequestMethod.GET)
	public ResponseEntity dossierById(@PathVariable Long dossierId){

		LOGGER.debug("dossierById(), {}",dossierId);


		return dossierService.getById(dossierId)
				.map(dossier -> {

					putSelfLink(dossier);
					LOGGER.debug("getDossierById() return  {}",dossier);
					return new ResponseEntity<>(dossier, HttpStatus.OK);

				}).orElseGet(() -> new ResponseEntity(new ApiError(HttpStatus.NOT_FOUND,"No entity found with id " + dossierId)
                , HttpStatus.NOT_FOUND));



	}





	private DossierResource putSelfLink(DossierResource dossierResource) {

		dossierResource.add(linkTo(methodOn(
					DossiersController.class).dossierById(dossierResource.getTechnicalId()))
					.withSelfRel());

		dossierResource.add(linkTo(methodOn(
				DossiersController.class).validerDossier(dossierResource.getTechnicalId(),null))
				.withRel(VALIDER_PATH));

		dossierResource.add(linkTo(methodOn(
				DossiersController.class).cloreDossier(dossierResource.getTechnicalId(),null))
				.withRel(CLORE_PATH));


		return dossierResource;
	}

	private HttpHeaders putLocationHeader(DossierResource dossierResource) {
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(new UriTemplate(DOSSIER).expand(dossierResource.getTechnicalId()));
		return headers;
	}


}
