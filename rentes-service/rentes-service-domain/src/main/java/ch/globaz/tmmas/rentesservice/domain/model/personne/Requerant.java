package ch.globaz.tmmas.rentesservice.domain.model.personne;

import ch.globaz.tmmas.rentesservice.domain.common.localdate.LocalDateDeserializer;
import ch.globaz.tmmas.rentesservice.domain.common.localdate.LocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.ZonedDateTime;

public class Requerant {
	private String sexe;
	private String nom;
	private String prenom;
	private String nss;

	@JsonProperty("id")
	private Long technicalId;

	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private ZonedDateTime dateNaissance;



	private Boolean isActive;

	public Requerant(){}

}
