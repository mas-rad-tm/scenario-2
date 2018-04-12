package ch.globaz.tmmas.personnesservice.application.api.web.resources;

import ch.globaz.tmmas.personnesservice.application.api.web.resources.common.ResourceAttributes;
import ch.globaz.tmmas.personnesservice.application.api.web.resources.common.ResourceObject;
import ch.globaz.tmmas.personnesservice.application.api.web.resources.localdate.ZonedDateDeserializer;
import ch.globaz.tmmas.personnesservice.application.api.web.resources.localdate.ZonedDateSerializer;
import ch.globaz.tmmas.personnesservice.domain.model.Adresse;
import ch.globaz.tmmas.personnesservice.domain.model.Localite;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static ch.globaz.tmmas.personnesservice.application.api.web.resources.localdate.DateFormatter.DATE_FORMAT;


/**
 * Ressources REST pour les dossiers
 */
@Getter
public class AdresseResourceAttributes implements ResourceAttributes {

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

	private Localite localite;

	private String rue;
	private Integer numero;
	private String complement;

	@JsonProperty("id")
	private Long technicalId;

	@JsonDeserialize(using = ZonedDateDeserializer.class)
	@JsonSerialize(using = ZonedDateSerializer.class)
	private ZonedDateTime dateDebutValidite;

	@JsonDeserialize(using = ZonedDateDeserializer.class)
	@JsonSerialize(using = ZonedDateSerializer.class)
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private ZonedDateTime dateFinValidite;



	private Boolean isActive;

	public AdresseResourceAttributes(){}


	public AdresseResourceAttributes(Adresse adresse){


		this.dateDebutValidite = adresse.getDateDebutValidite();
		this.dateFinValidite = adresse.getDateFinvalidite();
		this.technicalId = adresse.getId();
		this.rue = adresse.getRue();
		this.isActive= adresse.getIsActive();
		this.numero = adresse.getNumero();
		this.complement = adresse.getComplement();
		this.localite = adresse.getLocalite();

	}

	public ResourceObject buildResourceObject () {
	    return new ResourceObject(this.getTechnicalId(),"adresse",this);
    }

}
