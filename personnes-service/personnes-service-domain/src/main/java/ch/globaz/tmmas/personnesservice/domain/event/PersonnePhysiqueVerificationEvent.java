package ch.globaz.tmmas.personnesservice.domain.event;

import ch.globaz.tmmas.personnesservice.domain.model.Adresse;
import ch.globaz.tmmas.personnesservice.domain.model.Localite;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class PersonnePhysiqueVerificationEvent implements DomainEvent{


    private Long id;
    private Boolean exist;


    public PersonnePhysiqueVerificationEvent(Long id, Boolean exist) {
        this.id = id;
        this.exist = exist;
    }


}
