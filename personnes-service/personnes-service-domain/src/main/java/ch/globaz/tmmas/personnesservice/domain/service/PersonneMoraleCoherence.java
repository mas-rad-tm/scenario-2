package ch.globaz.tmmas.personnesservice.domain.service;

import ch.globaz.tmmas.personnesservice.domain.common.GlobalParams;
import ch.globaz.tmmas.personnesservice.domain.exception.AdresseIncoherenceException;
import ch.globaz.tmmas.personnesservice.domain.factory.AdresseFactory;
import ch.globaz.tmmas.personnesservice.domain.model.Adresse;
import ch.globaz.tmmas.personnesservice.domain.model.PersonneMorale;
import ch.globaz.tmmas.personnesservice.domain.repository.AdressesRepository;

import java.time.Period;
import java.time.format.DateTimeFormatter;

public class PersonneMoraleCoherence {

	public static Adresse addAdresseToPersonneMorale(PersonneMorale peronne, Adresse nouvelleAdresse, AdressesRepository
			adresseRepository) throws AdresseIncoherenceException {

		if(peronne.hasAdressesActiveDefinies()){

			Adresse adresseActive = peronne.getAdresseActive();

			if(nouvelleAdresse.getDateDebutValidite().isBefore(adresseActive.getDateDebutValidite())){
				throw new AdresseIncoherenceException(String.format("La date de but de la nouvelle adresse [%s] "
						+ "doit être apprès la date de l'adresse active actuelle %s",nouvelleAdresse.getDateDebutValidite()
						.format(DateTimeFormatter.ofPattern(GlobalParams.DATE_FORMATTER_PATTER.value)),adresseActive
						.getDateDebutValidite()));
			}

			//Désactivation ancienne adresse
			adresseActive.desactive(nouvelleAdresse.getDateDebutValidite().minus(Period.ofDays(1)));
			adresseRepository.mettreAJour(adresseActive);

			adresseRepository.creerAdresse(nouvelleAdresse);
			peronne.setAdresseActive(nouvelleAdresse);


		}else{
			adresseRepository.creerAdresse(nouvelleAdresse);
			peronne.setAdresseActive(nouvelleAdresse);
		}
		return peronne.getAdresseActive();

	}
}
