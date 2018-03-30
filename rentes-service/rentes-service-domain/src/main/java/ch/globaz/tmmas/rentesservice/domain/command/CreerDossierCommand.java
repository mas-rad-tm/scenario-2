package ch.globaz.tmmas.rentesservice.domain.command;


import ch.globaz.tmmas.rentesservice.domain.common.ValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@EqualsAndHashCode
@Getter
@ToString
public class CreerDossierCommand implements DomainCommand,ValueObject<CreerDossierCommand> {


	@NotNull
	private LocalDate dateEnregistrement;
	@NotNull
	private Long requerantId;

	CreerDossierCommand () {}


	@Override
	public boolean sameValueAs(CreerDossierCommand other) {
		return this.equals(other);
	}
}
