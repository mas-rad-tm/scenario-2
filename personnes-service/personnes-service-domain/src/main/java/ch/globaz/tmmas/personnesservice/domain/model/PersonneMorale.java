package ch.globaz.tmmas.personnesservice.domain.model;

import ch.globaz.tmmas.personnesservice.domain.common.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@EqualsAndHashCode
@ToString
public class PersonneMorale implements Entity<PersonneMorale>{


	private PersonneId identifiant;
	private String nom;
	private String prenom;
	private ZonedDateTime dateNaissance;
	private Sexe sexe;
	@Setter
	private Set<Adresse> adresses = new HashSet<>(0);


	@Override
	public boolean sameIdentityAs(PersonneMorale other) {
		return this.identifiant.equals(other.identifiant);
	}



	public PersonneMorale(PersonneId identifiant, String nom, String prenom, ZonedDateTime dateNaissance, Sexe sexe){
		this.identifiant = identifiant;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.sexe = sexe;
	}

	public void addAdresse(Adresse adresse){
		this.adresses.add(adresse);
	}
	private Long id;

	PersonneMorale(){};
}
