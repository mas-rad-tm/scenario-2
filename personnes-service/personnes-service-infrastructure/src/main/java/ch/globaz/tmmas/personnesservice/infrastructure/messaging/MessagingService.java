package ch.globaz.tmmas.personnesservice.infrastructure.messaging;


import ch.globaz.tmmas.personnesservice.domain.event.PersonnePhysiqueVerifieEvent;
import ch.globaz.tmmas.personnesservice.infrastructure.messaging.kafka.KafkaTopics;

public interface MessagingService {

    void sendForTopics(KafkaTopics topic, PersonnePhysiqueVerifieEvent event);
}
