package ch.globaz.tmmas.rentesservice.domain.command;





import ch.globaz.tmmas.rentesservice.domain.common.localdate.ZonedDateDeserializer;
import ch.globaz.tmmas.rentesservice.domain.common.localdate.ZonedDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@EqualsAndHashCode
@Getter
@ToString
public class CreerDossierWithPersonneCommand implements DomainCommand {


	private DossierCommand dossier = new DossierCommand();

	private PersonneCommand personne = new PersonneCommand();

	CreerDossierWithPersonneCommand() {}

	@EqualsAndHashCode
	@Getter
	@ToString
	public class DossierCommand{

		@NotNull
		@JsonDeserialize(using = ZonedDateDeserializer.class)
		@JsonSerialize(using = ZonedDateSerializer.class)
		private ZonedDateTime dateEnregistrement;

	}


	@EqualsAndHashCode
	@Getter
	@ToString
	public class PersonneCommand{
		@NotNull
		private String nss;
		@NotNull
		private String nom;
		@NotNull
		private String prenom;
		@NotNull
		@JsonDeserialize(using = ZonedDateDeserializer.class)
		@JsonSerialize(using = ZonedDateSerializer.class)
		private ZonedDateTime dateNaissance;
		@NotNull
		private String sexe;
	}
}
