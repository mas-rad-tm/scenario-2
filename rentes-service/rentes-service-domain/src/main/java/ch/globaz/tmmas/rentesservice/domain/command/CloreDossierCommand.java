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
public class CloreDossierCommand implements DomainCommand,ValueObject<CloreDossierCommand>{

	@NotNull
	private LocalDate dateCloture;

	public CloreDossierCommand(){}

	@Override
	public boolean sameValueAs(CloreDossierCommand other) {
		return this.equals(other);
	}
}
