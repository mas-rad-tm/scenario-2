package ch.globaz.tmmas.rentesservice.application.event;

import ch.globaz.tmmas.rentesservice.domain.command.DomainCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
class DossiersCommandListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(DossiersCommandListener.class);


    @EventListener
    public void onCommand(DomainCommand command){
        LOGGER.info("onDomainCommand: {}",command);
    }
}
