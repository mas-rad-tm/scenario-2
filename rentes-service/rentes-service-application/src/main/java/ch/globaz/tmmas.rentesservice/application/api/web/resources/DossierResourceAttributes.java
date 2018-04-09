package ch.globaz.tmmas.rentesservice.application.api.web.resources;

import ch.globaz.tmmas.rentesservice.application.api.web.resources.common.ResourceAttributes;
import ch.globaz.tmmas.rentesservice.application.api.web.resources.common.ResourceObject;
import ch.globaz.tmmas.rentesservice.application.api.web.resources.localdate.ZonedDateDeserializer;
import ch.globaz.tmmas.rentesservice.application.api.web.resources.localdate.ZonedDateSerializer;
import ch.globaz.tmmas.rentesservice.domain.model.dossier.Dossier;
import ch.globaz.tmmas.rentesservice.domain.model.dossier.DossierStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Getter;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static ch.globaz.tmmas.rentesservice.application.api.web.resources.localdate.DateFormatter.DATE_FORMAT;


/**
 * Ressources REST pour les dossiers
 */
@Getter
public class DossierResourceAttributes implements ResourceAttributes{

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

	private String identifiant;
	private Long requerantId;

	@JsonProperty("id")
	private Long technicalId;

	@JsonDeserialize(using = ZonedDateDeserializer.class)
	@JsonSerialize(using = ZonedDateSerializer.class)
	private ZonedDateTime dateEnregistrement;

	@JsonDeserialize(using = ZonedDateDeserializer.class)
	@JsonSerialize(using = ZonedDateSerializer.class)
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private ZonedDateTime dateValidation;

	@JsonDeserialize(using = ZonedDateDeserializer.class)
	@JsonSerialize(using = ZonedDateSerializer.class)
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private ZonedDateTime dateCloture;

	private DossierStatus status;

	public DossierResourceAttributes(){}


	public DossierResourceAttributes(Dossier dossier){


		this.identifiant = dossier.identifiant().getIdentifiant();
		this.requerantId = dossier.requerantId();
		this.technicalId = dossier.id();
		this.status = dossier.status();
		this.dateEnregistrement = dossier.dateEnregistrement();
		this.dateValidation = dossier.getDateValidation();
		this.dateCloture = dossier.getDateCloture();

	}

	public ResourceObject buildResourceObject () {
	    return new ResourceObject(this.getTechnicalId(),"dossier",this);
    }

}
