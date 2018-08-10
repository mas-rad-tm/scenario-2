package ch.globaz.tmmas.personnesservice.infrastructure.messaging.kafka;

import ch.globaz.tmmas.personnesservice.domain.event.PersonnePhysiqueVerifieEvent;
import ch.globaz.tmmas.personnesservice.infrastructure.messaging.MessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessaging implements MessagingService {

    @Autowired
    private KafkaTemplate<String, ?> kafkaTemplate;


    @Override
    public void sendForTopics(KafkaTopics topic, PersonnePhysiqueVerifieEvent event) {

        kafkaTemplate.send(new GenericMessage<Object>(event));
    }
}
