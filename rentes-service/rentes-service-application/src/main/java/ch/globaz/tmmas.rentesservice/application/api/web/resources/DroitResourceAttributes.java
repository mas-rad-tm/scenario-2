package ch.globaz.tmmas.rentesservice.application.api.web.resources;

import ch.globaz.tmmas.rentesservice.application.api.web.resources.common.ResourceAttributes;
import ch.globaz.tmmas.rentesservice.application.api.web.resources.common.ResourceObject;
import ch.globaz.tmmas.rentesservice.application.api.web.resources.localdate.ZonedDateDeserializer;
import ch.globaz.tmmas.rentesservice.application.api.web.resources.localdate.ZonedDateSerializer;
import ch.globaz.tmmas.rentesservice.domain.model.droit.DonneesFinancieres;
import ch.globaz.tmmas.rentesservice.domain.model.droit.Droit;
import ch.globaz.tmmas.rentesservice.domain.model.droit.DroitStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static ch.globaz.tmmas.rentesservice.application.api.web.resources.localdate.DateFormatter.DATE_FORMAT;

/**
 * Ressources REST pour les dossiers
 */
@Getter
public class DroitResourceAttributes implements ResourceAttributes{

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

	private List<DonneesFinancieres> donnesFinancieres;

	private DroitStatus status;

	private String identifiant;

	@JsonProperty("id")
	private Long technicalId;

	private Long dossierId;

	@JsonDeserialize(using = ZonedDateDeserializer.class)
	@JsonSerialize(using = ZonedDateSerializer.class)
	private ZonedDateTime dateDebutDroit;

	@JsonDeserialize(using = ZonedDateDeserializer.class)
	@JsonSerialize(using = ZonedDateSerializer.class)
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private ZonedDateTime dateFinDroit;



	public DroitResourceAttributes(){}


	public DroitResourceAttributes(Droit droit){

		this.identifiant = droit.getIdentifiant().identifiant();
		this.technicalId = droit.getId();
		this.status = droit.getStatus();
		this.dateDebutDroit = droit.getDateDebutDroit();
		this.dateFinDroit = droit.getDateFinDroit();
		this.dossierId = droit.getDossier().getId();
	}

	public ResourceObject buildResourceObject () {
		return new ResourceObject(this.getTechnicalId(),"droit",this);
	}


}
