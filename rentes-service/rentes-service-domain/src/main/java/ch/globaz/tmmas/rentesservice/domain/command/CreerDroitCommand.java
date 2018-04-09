package ch.globaz.tmmas.rentesservice.domain.command;

import ch.globaz.tmmas.rentesservice.domain.common.ValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.ZonedDateTime;

@EqualsAndHashCode
@Getter
@ToString
public class CreerDroitCommand implements DomainCommand,ValueObject<CreerDossierCommand> {


    private ZonedDateTime dateDebutDroit;


    @Override
    public boolean sameValueAs(CreerDossierCommand other) {
        return false;
    }
}
