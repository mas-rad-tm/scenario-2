package ch.globaz.tmmas.rentesservice.domain.model.droit;

import ch.globaz.tmmas.rentesservice.domain.common.ValueObject;
import ch.globaz.tmmas.rentesservice.domain.model.dossier.DossierId;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class DroitId implements ValueObject<DroitId>{

    @NotNull(message = "L'identifiant ne peut pas être null")
    private String identifiant;

    private DroitId(String identifiant) {
        this.identifiant = identifiant;
    }

    public static DroitId aleatoire () {
        return new DroitId(UUID.randomUUID().toString());
    }

    public String identifiant() {
        return identifiant;
    }

    @Override
    public boolean sameValueAs(DroitId other) {
        return false;
    }

    public DroitId(){};
}
