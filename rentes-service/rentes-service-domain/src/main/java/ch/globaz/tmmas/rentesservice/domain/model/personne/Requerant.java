package ch.globaz.tmmas.rentesservice.domain.model.personne;


import ch.globaz.tmmas.rentesservice.domain.common.localdate.ZonedDateDeserializer;
import ch.globaz.tmmas.rentesservice.domain.common.localdate.ZonedDateSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.ZonedDateTime;

public class Requerant {
	private Long id;
	private String sexe;
	private String nom;
	private String prenom;
	private String nss;
	private String type;


	@JsonProperty("id")
	private Long technicalId;

	@JsonDeserialize(using = ZonedDateDeserializer.class)
	@JsonSerialize(using = ZonedDateSerializer.class)
	private ZonedDateTime dateNaissance;



	private Boolean isActive;

	public Requerant(){}

}
