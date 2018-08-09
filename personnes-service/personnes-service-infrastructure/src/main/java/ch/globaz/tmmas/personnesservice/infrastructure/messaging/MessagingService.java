package ch.globaz.tmmas.personnesservice.infrastructure.messaging;


import ch.globaz.tmmas.personnesservice.domain.event.PersonnePhysiqueVerificationEvent;
import ch.globaz.tmmas.personnesservice.infrastructure.messaging.event.DossierCreeEvent;
import ch.globaz.tmmas.personnesservice.infrastructure.messaging.kafka.KafkaTopics;

public interface MessagingService {

    void sendForTopics(KafkaTopics topic, PersonnePhysiqueVerificationEvent event);
}
