package ch.globaz.tmmas.rentesservice.domain.command;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@EqualsAndHashCode
@Getter
@ToString
public class MiseAJourDossierCommand implements DomainCommand{

	@NotNull
	private MiseAjourDossierAction action;

	private MiseAjourDossierData data;


	MiseAJourDossierCommand(){}

	public static DomainCommand  resolveCommandByAction(MiseAJourDossierCommand command){

		switch (command.action){

			case VALIDER:
				return new ValiderDossierCommand(command.getData().getDateValidation());
			case CLORE:
				return new CloreDossierCommand(command.getData().getDateCloture(),
						command.getData().getRaisonCloture());
			default:
				throw new RuntimeException("not a valid action");
		}
	}

}
