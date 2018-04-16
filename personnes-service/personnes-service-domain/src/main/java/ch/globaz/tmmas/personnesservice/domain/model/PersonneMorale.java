package ch.globaz.tmmas.personnesservice.domain.model;

import ch.globaz.tmmas.personnesservice.domain.common.Entity;
import ch.globaz.tmmas.personnesservice.domain.common.GlobalParams;
import ch.globaz.tmmas.personnesservice.domain.exception.AdresseIncoherenceException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.time.Period;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAmount;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Getter
@EqualsAndHashCode(of = "identifiant")
@ToString(exclude = "adresses")
public class PersonneMorale implements Entity<PersonneMorale>{


	private PersonneId identifiant;
	private String nom;
	private String prenom;
	private ZonedDateTime dateNaissance;
	private Sexe sexe;
	@Setter
	private Adresse adresseActive;




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

	/**
	private Adresse getAdresseActive(){
		return getAdresses().stream().filter(adresse -> adresse.getIsActive()).findFirst().get();
	}
*/
	public boolean hasAdressesActiveDefinies(){
		return this.adresseActive != null;
	}




	private Long id;

	PersonneMorale(){};
}
