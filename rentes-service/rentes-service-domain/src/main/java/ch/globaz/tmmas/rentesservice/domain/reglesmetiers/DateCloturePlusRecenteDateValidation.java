package ch.globaz.tmmas.rentesservice.domain.reglesmetiers;

import ch.globaz.tmmas.rentesservice.domain.common.specification.AbstractSpecification;
import ch.globaz.tmmas.rentesservice.domain.model.dossier.Dossier;
import lombok.Getter;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

@Getter
public class DateCloturePlusRecenteDateValidation extends AbstractSpecification<Dossier> {


    private ZonedDateTime dateCloture;

    public DateCloturePlusRecenteDateValidation(ZonedDateTime dateCloture) {
         this.dateCloture = dateCloture;
    }

    @Override
    public boolean isSatisfiedBy(Dossier dossier) {

        return (dossier.dateEnregistrement().isBefore(dateCloture));

    }

    @Override
    public List<String> getDescriptionReglesMetier() {
        return Arrays.asList("La date de cloture doit être plus récente que la date de validation");
    }
}
