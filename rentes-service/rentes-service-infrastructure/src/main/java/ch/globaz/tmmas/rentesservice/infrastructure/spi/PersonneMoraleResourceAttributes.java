package ch.globaz.tmmas.rentesservice.infrastructure.spi;


import ch.globaz.tmmas.rentesservice.infrastructure.spi.localdate.ZonedDateDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static ch.globaz.tmmas.rentesservice.domain.common.localdate.DateFormatter.DATE_FORMAT;


/**
 * Ressources REST pour les dossiers
 */
@Getter
public class PersonneMoraleResourceAttributes {

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);


	private String sexe;
	private String nom;
	private String prenom;
	private String nss;

	@JsonProperty("id")
	private Long technicalId;


	private String isoDateNaissance;
	private String formattedDateNaissance;


	public PersonneMoraleResourceAttributes(){}




}
