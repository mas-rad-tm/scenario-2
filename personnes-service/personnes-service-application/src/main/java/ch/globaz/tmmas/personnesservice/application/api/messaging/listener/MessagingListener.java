package ch.globaz.tmmas.personnesservice.application.api.messaging.listener;


import ch.globaz.tmmas.personnesservice.application.event.InternalEventPublisher;
import ch.globaz.tmmas.personnesservice.application.service.PersonneService;
import ch.globaz.tmmas.personnesservice.domain.event.PersonnePhysiqueVerificationEvent;
import ch.globaz.tmmas.personnesservice.infrastructure.messaging.event.DossierCreeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessagingListener {

    @Autowired
    PersonneService personneService;

    @Autowired
    InternalEventPublisher eventPublisher;

    @KafkaListener(topics = "dossier-cree",containerFactory = "jsonKafkaListenerContainerFactory")
    public void listen(DossierCreeEvent message) {
        System.out.println("Received Messasge in group foo: " + message);

        Boolean personneExist = personneService.checkifPersonneExist(message.getRequerantId());

        if(personneExist){
            eventPublisher.publishEvent(new PersonnePhysiqueVerificationEvent(message.getRequerantId(),Boolean.TRUE));
        }else{
            eventPublisher.publishEvent(new PersonnePhysiqueVerificationEvent(message.getRequerantId(),Boolean.FALSE));
        }


    }
}
