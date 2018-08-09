package ch.globaz.tmmas.personnesservice.infrastructure.messaging.kafka;

import ch.globaz.tmmas.personnesservice.domain.event.DomainEvent;
import ch.globaz.tmmas.personnesservice.domain.event.PersonnePhysiqueVerificationEvent;
import ch.globaz.tmmas.personnesservice.infrastructure.messaging.MessagingService;
import ch.globaz.tmmas.personnesservice.infrastructure.messaging.event.DossierCreeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessaging implements MessagingService {

    @Autowired
    private KafkaTemplate<String, ?> kafkaTemplate;


    @Override
    public void sendForTopics(KafkaTopics topic, PersonnePhysiqueVerificationEvent event) {

        kafkaTemplate.send(new GenericMessage<Object>(event));
    }
}
