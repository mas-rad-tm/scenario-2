package ch.globaz.tmmas.personnesservice.domain.model;


import ch.globaz.tmmas.personnesservice.domain.common.ValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.constraints.NotNull;


@EqualsAndHashCode
@Getter
public class Localite implements ValueObject {

	@NotNull(message = "Le nom ne peut pas être null")
	private String nom;
	@NotNull(message = "Le npa ne peut pas être null")
	private Integer npa;

	public Localite(String nom, Integer npa){
		this.npa = npa;
		this.nom = nom;
	}

	public boolean sameValueAs(Object other) {
		return false;
	}

	Localite(){}

	private Long id;
}
