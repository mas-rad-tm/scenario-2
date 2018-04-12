package ch.globaz.tmmas.personnesservice.domain.command;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@EqualsAndHashCode
@Getter
@ToString
public class CreerAdresseCommand implements DomainCommand {

	@NotNull
	private Long localiteId;
	@NotNull
	private String rue;
	@NotNull
	private Integer numero;
	@NotNull
	private String complement;
	@NotNull
	private ZonedDateTime dateDebutValidite;


	CreerAdresseCommand() {}


}
