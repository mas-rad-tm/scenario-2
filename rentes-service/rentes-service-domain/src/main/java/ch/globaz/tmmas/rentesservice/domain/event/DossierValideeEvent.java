package ch.globaz.tmmas.rentesservice.domain.event;

import ch.globaz.tmmas.rentesservice.domain.common.GlobalParams;
import ch.globaz.tmmas.rentesservice.domain.model.dossier.Dossier;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.format.DateTimeFormatter;


@EqualsAndHashCode
@ToString
@Getter
public class DossierValideeEvent implements DomainEvent {


    private String identifiant;
    private String dateValidation;
    private Long requerantId;
    private String status;
    private Long id;

    public DossierValideeEvent(Long id, String identifiant, String dateValidation, Long requerantId, String status) {
        this.identifiant = identifiant;
        this.dateValidation = dateValidation;
        this.requerantId = requerantId;
        this.status = status;
        this.id = id;
    }

    public DossierValideeEvent(){}



    public static DossierValideeEvent fromEntity(Dossier dossier) {
        return new DossierValideeEvent(dossier.getId(),
                dossier.getIdentifiant().identifiant(),
                dossier.getDateValidation().format(formatter),
                dossier.requerantId(),
                dossier.status().toString());
    }

}
