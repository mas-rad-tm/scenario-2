package ch.globaz.tmmas.personnesservice.infrastructure.messaging.event;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@EqualsAndHashCode
@ToString
@Getter
public class DossierCreeEvent {


    private String identifiant;
    private String dateEnregistrement;
    private Long requerantId;
    private String status;
    private Long id;

    private DossierCreeEvent(Long id, String identifiant, String dateEnregistrement, Long requerantId, String status) {
        this.identifiant = identifiant;
        this.dateEnregistrement = dateEnregistrement;
        this.requerantId = requerantId;
        this.status = status;
        this.id = id;
    }

    public DossierCreeEvent(){}





}
