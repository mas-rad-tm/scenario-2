package ch.globaz.tmmas.rentesservice.application.api.messaging.listener;



import ch.globaz.tmmas.rentesservice.application.event.InternalEventPublisher;
import ch.globaz.tmmas.rentesservice.application.service.DossierService;
import ch.globaz.tmmas.rentesservice.infrastructure.messaging.event.PersonnePhysiqueVerificationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessagingListener {


    @Autowired
    DossierService dossierService;

    @Autowired
    InternalEventPublisher eventPublisher;

    @KafkaListener(topics = "personne-verifie",containerFactory = "jsonKafkaListenerContainerFactory")
    public void listen(PersonnePhysiqueVerificationEvent message) {
        System.out.println("Received Messasge in group foo: " + message);


    }
}
