package ch.globaz.tmmas.rentesservice.domain.command;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@EqualsAndHashCode
@Getter
@ToString
public class CreerDossierWithPersonneCommand implements DomainCommand {


	private CreerDossierCommand dossier = new CreerDossierCommand();

	private Personne personne = new Personne();

	CreerDossierWithPersonneCommand() {}



	@EqualsAndHashCode
	@Getter
	@ToString
	public class Personne{
		@NotNull
		private String nss;
		@NotNull
		private String nom;
		@NotNull
		private String prenom;
		@NotNull
		private ZonedDateTime dateNaissance;
		@NotNull
		private String sexe;
	}
}
