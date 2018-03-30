package ch.globaz.tmmas.rentesservice.application.event;



import ch.globaz.tmmas.rentesservice.domain.event.DomainEvent;
import ch.globaz.tmmas.rentesservice.domain.event.DossierCreeEvent;
import ch.globaz.tmmas.rentesservice.domain.notification.NotificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
class DossiersEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(DossiersEventListener.class);

    /**
    @Autowired
    NotificationService notificationService;

    @Autowired
    ObjectMapper mapper;

*/

    @EventListener
    void onDomainEvent(DomainEvent event) throws JsonProcessingException {

        LOGGER.info("onDomainEvent {}",event);



    }

}
