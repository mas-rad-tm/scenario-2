package ch.globaz.tmmas.personnesservice.application.api.web.controller;

import ch.globaz.tmmas.personnesservice.application.service.LocaliteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/localite")
public class LocaliteController {


	private static final Logger LOGGER = LoggerFactory.getLogger(LocaliteController.class);

	@Autowired
	LocaliteService localiteService;

	/**
	 * Génération de 5 localités a but d'exemple et de données de bases
	 * @return une instance de <code>ResponseEntity</code>
	 */
	@RequestMapping(value = "/sample", method = RequestMethod.POST)
	public ResponseEntity creerDossier(){
		LOGGER.info("creerDossier()");

		localiteService.generateLocalitAsSample();

		return new ResponseEntity("Ok localite genrated", HttpStatus.OK);

	}

}
