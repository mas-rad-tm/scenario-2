package ch.globaz.tmmas.rentesservice.infrastructure.messaging.event;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class PersonnePhysiqueVerificationEvent{


    private Long id;
    private Boolean exist;

    public PersonnePhysiqueVerificationEvent(){}

    public PersonnePhysiqueVerificationEvent(Long id, Boolean exist) {
        this.id = id;
        this.exist = exist;
    }


}
