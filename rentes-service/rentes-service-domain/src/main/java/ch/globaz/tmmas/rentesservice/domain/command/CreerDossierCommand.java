package ch.globaz.tmmas.rentesservice.domain.command;


import ch.globaz.tmmas.rentesservice.domain.common.ValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Optional;

@EqualsAndHashCode
@Getter
@ToString
public class CreerDossierCommand implements DomainCommand {


	private ZonedDateTime dateEnregistrement;
	@NotNull
	private Long requerantId;

	CreerDossierCommand () {}


}
