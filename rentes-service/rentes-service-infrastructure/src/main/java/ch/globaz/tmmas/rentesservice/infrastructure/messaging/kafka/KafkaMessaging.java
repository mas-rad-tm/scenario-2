package ch.globaz.tmmas.rentesservice.infrastructure.messaging.kafka;

import ch.globaz.tmmas.rentesservice.domain.event.DomainEvent;
import ch.globaz.tmmas.rentesservice.domain.event.DossierCreeEvent;
import ch.globaz.tmmas.rentesservice.infrastructure.messaging.MessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessaging implements MessagingService{

    @Autowired
    private KafkaTemplate<String, DomainEvent> kafkaTemplate;

    @Override
    public void sendForTopics(KafkaTopics topic, DossierCreeEvent event) {
        kafkaTemplate.send(topic.nom,event);
    }
}
