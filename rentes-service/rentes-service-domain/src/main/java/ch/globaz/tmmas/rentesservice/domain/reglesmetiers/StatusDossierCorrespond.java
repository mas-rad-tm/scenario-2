package ch.globaz.tmmas.rentesservice.domain.reglesmetiers;

import ch.globaz.tmmas.rentesservice.domain.common.specification.AbstractSpecification;
import ch.globaz.tmmas.rentesservice.domain.model.dossier.Dossier;
import ch.globaz.tmmas.rentesservice.domain.model.dossier.DossierStatus;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Getter
public class StatusDossierCorrespond extends AbstractSpecification<Dossier> {


    private DossierStatus status;

    public StatusDossierCorrespond(DossierStatus status) {

        this.status = status;
    }

    @Override
    public boolean isSatisfiedBy(Dossier dossier) {

        return dossier.status().equals(status);

    }

    @Override
    public List<String> getDescriptionReglesMetier() {
        return Arrays.asList("Le status doit être dans l'état " + status.name());
    }
}
