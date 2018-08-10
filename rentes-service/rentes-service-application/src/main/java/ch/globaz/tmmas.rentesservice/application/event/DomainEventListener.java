package ch.globaz.tmmas.rentesservice.application.event;



import ch.globaz.tmmas.rentesservice.domain.event.DomainEvent;
import ch.globaz.tmmas.rentesservice.domain.event.DossierCreeEvent;
import ch.globaz.tmmas.rentesservice.infrastructure.messaging.MessagingService;
import ch.globaz.tmmas.rentesservice.infrastructure.messaging.kafka.KafkaTopics;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
class DomainEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(DomainEventListener.class);



    @Autowired
    private MessagingService messagingService;

    @Async
    @EventListener
    void onDomainEvent(DomainEvent event) throws JsonProcessingException {

        LOGGER.info("onDomainEvent {}",event);

    }

    @Async
    @EventListener
    void onDomainEvent(DossierCreeEvent event) throws JsonProcessingException {

        LOGGER.info("onDossierCreerEvent {}",event);

        messagingService.sendForTopics(KafkaTopics.DOSSIER_CREE,event);

    }

}
