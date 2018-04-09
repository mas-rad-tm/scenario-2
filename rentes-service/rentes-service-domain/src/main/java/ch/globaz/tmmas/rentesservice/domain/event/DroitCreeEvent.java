package ch.globaz.tmmas.rentesservice.domain.event;

import ch.globaz.tmmas.rentesservice.domain.model.dossier.Dossier;
import ch.globaz.tmmas.rentesservice.domain.model.droit.DonneesFinancieres;
import ch.globaz.tmmas.rentesservice.domain.model.droit.Droit;
import ch.globaz.tmmas.rentesservice.domain.model.droit.DroitId;
import ch.globaz.tmmas.rentesservice.domain.model.droit.DroitStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.ZonedDateTime;
import java.util.List;

@EqualsAndHashCode
@ToString
@Getter
public class DroitCreeEvent implements DomainEvent {

    private List<DonneesFinancieres> donnesFinancieres;
    private String identifiant;
    private Long id;
    private Long dossierId;
    private ZonedDateTime dateDebutDroit;
    private ZonedDateTime dateFinDroit;
    private String status;

    private DroitCreeEvent(String identifiant, Long id, Long dossierId, ZonedDateTime dateDebutDroit, String status){
        this.identifiant = identifiant;
        this.id = id;
        this.dossierId = dossierId;
        this.dateDebutDroit = dateDebutDroit;
        this.status = status;
    }

    public static DroitCreeEvent fromEntity(Droit droit) {
        return new DroitCreeEvent(droit.getIdentifiant().identifiant(),
                droit.getId(),
                droit.getDossier().getId(),
                droit.getDateDebutDroit(),
                droit.getStatus().toString());
    }
}
