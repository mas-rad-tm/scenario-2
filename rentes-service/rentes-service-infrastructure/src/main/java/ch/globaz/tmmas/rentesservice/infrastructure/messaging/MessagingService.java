package ch.globaz.tmmas.rentesservice.infrastructure.messaging;

import ch.globaz.tmmas.rentesservice.domain.event.DossierCreeEvent;
import ch.globaz.tmmas.rentesservice.infrastructure.messaging.kafka.KafkaTopics;

public interface MessagingService {

    void sendForTopics(KafkaTopics topic, DossierCreeEvent event);
}
