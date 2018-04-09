package ch.globaz.tmmas.rentesservice.domain.command;

import ch.globaz.tmmas.rentesservice.domain.common.ValueObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.ZonedDateTime;

@EqualsAndHashCode
@Getter
@ToString
public class CloreDossierCommand implements DomainCommand,ValueObject<CloreDossierCommand>{

	@NotNull
	private ZonedDateTime dateCloture;

	@NotNull
	private String raisonCloture;

	public CloreDossierCommand(){}

	public CloreDossierCommand(ZonedDateTime dateCloture, String raisonCloture){
		this.raisonCloture = raisonCloture;
		this.dateCloture = dateCloture;
	}


	@Override
	public boolean sameValueAs(CloreDossierCommand other) {
		return this.equals(other);
	}
}
