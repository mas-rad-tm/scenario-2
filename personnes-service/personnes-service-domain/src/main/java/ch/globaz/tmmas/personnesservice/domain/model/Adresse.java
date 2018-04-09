package ch.globaz.tmmas.personnesservice.domain.model;

import ch.globaz.tmmas.personnesservice.domain.common.ValueObject;

import java.time.Period;
import java.time.ZonedDateTime;

public class Adresse implements ValueObject<Adresse> {

	Localite localite;
	String rue;
	Integer numero;
	String complement;
	ZonedDateTime dateDebutValidite;
	ZonedDateTime dateFinvalidite;
	Boolean isActive;

	public Adresse(Localite localite, String rue, Integer numero, String complement, ZonedDateTime dateValidite) {
		this.localite = localite;
		this.rue = rue;
		this.complement = complement;
		this.dateDebutValidite = dateValidite;

	}


	@Override
	public boolean sameValueAs(Adresse other) {
		return false;
	}
}
