package ch.globaz.tmmas.rentesservice.domain.reglesmetiers;

import ch.globaz.tmmas.rentesservice.domain.common.specification.AbstractSpecification;
import ch.globaz.tmmas.rentesservice.domain.model.dossier.Dossier;
import lombok.Getter;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class DateValidationPlusRecenteDateEnregistrement extends AbstractSpecification<Dossier> {


    private ZonedDateTime dateValidation;

    public DateValidationPlusRecenteDateEnregistrement(ZonedDateTime dateValidation) {
         this.dateValidation = dateValidation;
    }

    @Override
    public boolean isSatisfiedBy(Dossier dossier) {

        return (dossier.dateEnregistrement().isBefore(dateValidation));

    }

    @Override
    public List<String> getDescriptionReglesMetier() {
        return Arrays.asList("La date de validation doit être plus récente que la date d'enregistrement");
    }
}
