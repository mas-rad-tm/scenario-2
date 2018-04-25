package ch.globaz.tmmas.personnesservice.application.event;




import ch.globaz.tmmas.personnesservice.domain.event.DomainEvent;
import ch.globaz.tmmas.personnesservice.messaging.MessagingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
class DomainEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(DomainEventListener.class);


    @Autowired
    MessagingService messagingService;

    @EventListener
    void onDomainEvent(DomainEvent event) throws JsonProcessingException {

        LOGGER.info("onDomainEvent {}",event);

        messagingService.notify(event);

    }

}
