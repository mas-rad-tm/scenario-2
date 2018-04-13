package ch.globaz.tmmas.personnesservice.application.api.web.controller;

import ch.globaz.tmmas.personnesservice.application.api.web.resources.AdresseResourceAttributes;
import ch.globaz.tmmas.personnesservice.application.api.web.resources.PersonneMoraleResourceAttributes;
import ch.globaz.tmmas.personnesservice.application.api.web.resources.common.ErrorResponseResource;
import ch.globaz.tmmas.personnesservice.application.api.web.resources.common.ResourceObject;
import ch.globaz.tmmas.personnesservice.application.api.web.resources.common.ResponseResource;
import ch.globaz.tmmas.personnesservice.application.service.AdressesService;
import ch.globaz.tmmas.personnesservice.application.service.PersonneService;
import ch.globaz.tmmas.personnesservice.domain.command.CreerAdresseCommand;
import ch.globaz.tmmas.personnesservice.domain.command.CreerPersonneMoraleCommand;
import ch.globaz.tmmas.personnesservice.domain.exception.AdresseIncoherenceException;
import ch.globaz.tmmas.personnesservice.domain.factory.AdresseFactory;
import ch.globaz.tmmas.personnesservice.domain.model.Adresse;
import ch.globaz.tmmas.personnesservice.domain.model.PersonneMorale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriTemplate;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/personnes")
public class PersonnesController {

    private static final String PERSONNES = "/personnes";
    private static final String PERSONNE = PERSONNES + "/{id}";

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonnesController.class);

    @Autowired
    PersonneService personneService;

    @Autowired
    AdressesService adresseService;
    /**
     * Génération de 5 localités a but d'exemple et de données de bases
     * @return une instance de <code>ResponseEntity</code>
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity creerPersonne(@Valid @RequestBody CreerPersonneMoraleCommand command){
        LOGGER.info("creerPersonneMorale(): {}", command);

        PersonneMorale personneMorale  = personneService.createPersonne(command);



         ResourceObject persoResourceObject = new PersonneMoraleResourceAttributes(personneMorale)
                .buildResourceObject();


            return new ResponseEntity<>(new ResponseResource(persoResourceObject), HttpStatus
                    .CREATED);

    }

    @RequestMapping(value="/{personneId}/adresses", method = RequestMethod.POST)
    public ResponseEntity creerAdresseForPersonne(@Valid @RequestBody CreerAdresseCommand command, @PathVariable Long
            personneId) throws AdresseIncoherenceException {
        LOGGER.info("creerAdresses() for personnid: {}, command:{}", personneId, command);

       // Optional<PersonneMorale> personneMorale = personneService.findById(personneId);

        //Adresse adresse =  AdresseFactory.create(command,personneId,localiteRepository,personneRepository);

        Adresse adresse =  adresseService.createAdresse(command,personneId);

       //personneMorale.get().addAdresse(adresse);

        ResourceObject adresseResource = new AdresseResourceAttributes(adresse)
                .buildResourceObject();

        return new ResponseEntity<>(new ResponseResource(adresseResource), putLocationHeader(adresseResource), HttpStatus.CREATED);
/*
        return personneService.findById(personneId).map(personne -> {

            return adresseService.createAdresse(command,personneId).map(adresse -> {

                ResourceObject adresseResource = new AdresseResourceAttributes(adresse)
                        .buildResourceObject();


                return new ResponseEntity<>(new ResponseResource(adresseResource), putLocationHeader(adresseResource), HttpStatus.CREATED);

            }).orElseGet(() ->
                new ResponseEntity(new ErrorResponseResource(HttpStatus.NOT_FOUND,"No localite found with id "
                        + command.getLocaliteId()),HttpStatus.NOT_FOUND)

            );

        }).orElseGet(() ->
                new ResponseEntity(HttpStatus.HTTP_VERSION_NOT_SUPPORTED));
*/

    }

    private HttpHeaders putLocationHeader(ResourceObject personneResource) {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new UriTemplate(PERSONNE).expand(personneResource.getTechnicalId()));
        return headers;
    }
}
