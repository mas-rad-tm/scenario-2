package ch.globaz.tmmas.personnesservice.domain.factory;

import ch.globaz.tmmas.personnesservice.domain.model.PersonneId;
import ch.globaz.tmmas.personnesservice.domain.model.PersonneMorale;
import ch.globaz.tmmas.personnesservice.domain.model.Sexe;

import java.time.ZonedDateTime;

public class PersonneMoraleFactory {
	public static PersonneMorale creerPersonne(String nom, String prenom, ZonedDateTime dateNaissance, String nss, Sexe sexe) {
		return new PersonneMorale(new PersonneId(nss),nom, prenom,dateNaissance, sexe);
	}
}
