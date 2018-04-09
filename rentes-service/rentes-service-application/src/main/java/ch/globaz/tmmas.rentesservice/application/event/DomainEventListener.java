package ch.globaz.tmmas.rentesservice.application.event;



import ch.globaz.tmmas.rentesservice.domain.event.DomainEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
class DomainEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(DomainEventListener.class);


    @EventListener
    void onDomainEvent(DomainEvent event) throws JsonProcessingException {

        LOGGER.info("onDomainEvent {}",event);

    }

}
