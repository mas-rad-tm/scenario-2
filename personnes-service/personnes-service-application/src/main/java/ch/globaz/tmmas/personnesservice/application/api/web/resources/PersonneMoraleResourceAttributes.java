package ch.globaz.tmmas.personnesservice.application.api.web.resources;

import ch.globaz.tmmas.personnesservice.application.api.web.resources.common.ResourceAttributes;
import ch.globaz.tmmas.personnesservice.application.api.web.resources.common.ResourceObject;
import ch.globaz.tmmas.personnesservice.application.api.web.resources.localdate.ZonedDateDeserializer;
import ch.globaz.tmmas.personnesservice.application.api.web.resources.localdate.ZonedDateSerializer;
import ch.globaz.tmmas.personnesservice.domain.model.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static ch.globaz.tmmas.personnesservice.application.api.web.resources.localdate.DateFormatter.DATE_FORMAT;


/**
 * Ressources REST pour les dossiers
 */
@Getter
public class PersonneMoraleResourceAttributes implements ResourceAttributes {

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);


	private Sexe sexe;
	private String nom;
	private String prenom;
	private String nss;

	@JsonProperty("id")
	private Long technicalId;

	@JsonDeserialize(using = ZonedDateDeserializer.class)
	@JsonSerialize(using = ZonedDateSerializer.class)
	private ZonedDateTime formatted_dateNaissance;

	private String iso_dateNaissance;




	private Boolean isActive;

	public PersonneMoraleResourceAttributes(){}


	public PersonneMoraleResourceAttributes(PersonneMorale personneMorale){


		this.formatted_dateNaissance = personneMorale.getDateNaissance();
		this.iso_dateNaissance = personneMorale.getDateNaissance().toString();
		this.technicalId = personneMorale.getId();
		this.nom = personneMorale.getNom();
		this.prenom = personneMorale.getPrenom();
		this.sexe = personneMorale.getSexe();
		this.nss = personneMorale.getIdentifiant().getNss();

	}

	public ResourceObject buildResourceObject () {
	    return new ResourceObject(this.getTechnicalId(),"personneMorale",
			    this);
    }

}
