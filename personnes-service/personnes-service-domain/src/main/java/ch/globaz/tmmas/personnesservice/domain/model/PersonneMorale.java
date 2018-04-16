package ch.globaz.tmmas.personnesservice.domain.model;

import ch.globaz.tmmas.personnesservice.domain.common.Entity;
import ch.globaz.tmmas.personnesservice.domain.common.GlobalParams;
import ch.globaz.tmmas.personnesservice.domain.exception.AdresseIncoherenceException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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
	private Set adresses;




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

	public void addAdresseActive(Adresse nouvelleAdresse) throws AdresseIncoherenceException {

		this.getAdresses();

		if(getAdresses().size() > 0){

			Adresse adresseActiveActuelle = (Adresse) adresses.toArray()[0];

			if(nouvelleAdresse.getDateDebutValidite().isBefore(adresseActiveActuelle.getDateDebutValidite())){
				throw new AdresseIncoherenceException(String.format("La date de but de la nouvelle adresse [%s] "
						+ "doit être apprès la date de l'adresse active actuelle %s",nouvelleAdresse.getDateDebutValidite()
						.format(DateTimeFormatter.ofPattern(GlobalParams.DATE_FORMATTER_PATTER.value))));
			}
			this.adresses.add(nouvelleAdresse);
		}else{
			this.adresses.add(nouvelleAdresse);
		}



	}
	private Long id;

	PersonneMorale(){};
}
