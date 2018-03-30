package ch.globaz.tmmas.rentesservice.domain.model.dossier;

import ch.globaz.tmmas.rentesservice.domain.common.ValueObject;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@ToString
@Getter
/**
 * Identifiant métier du dossier Ne coreespond pas à un identifant technique.
 * Sert de valeur de base afin de comprarer l'égalité entre deux dossiers
 */
public class DossierId implements ValueObject<DossierId>{

    @NotNull(message = "L'identifiant ne peut pas être null")
    private String identifiant;

    private DossierId(String identifiant) {
        this.identifiant = identifiant;
    }

    public static DossierId aleatoire () {
        return new DossierId(UUID.randomUUID().toString());
    }

    public String identifiant() {
        return identifiant;
    }

    @Override
    public boolean sameValueAs(DossierId other) {
        return this.equals(other);
    }

    public DossierId(){}
}
