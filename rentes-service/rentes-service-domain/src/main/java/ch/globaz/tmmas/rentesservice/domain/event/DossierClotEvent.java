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
public class DossierClotEvent implements DomainEvent {



    private String identifiant;
    private String dateEnregistrement;
    private Long requerantId;
    private String status;
    private Long id;

    DossierClotEvent(Long id, String identifiant, String dateEnregistrement, Long requerantId, String status) {
        this.identifiant = identifiant;
        this.dateEnregistrement = dateEnregistrement;
        this.requerantId = requerantId;
        this.status = status;
        this.id = id;
    }

    public DossierClotEvent(){}



    public static DossierClotEvent fromEntity(Dossier dossier) {
        return new DossierClotEvent(dossier.getId(),
                dossier.getIdentifiant().identifiant(),
                dossier.getDateCloture().format(formatter),
                dossier
                .requerantId(),
                dossier.status().toString());
    }

}
