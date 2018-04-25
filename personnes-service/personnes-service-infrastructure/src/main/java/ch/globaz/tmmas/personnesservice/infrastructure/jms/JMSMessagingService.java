package ch.globaz.tmmas.personnesservice.infrastructure.jms;


import ch.globaz.tmmas.personnesservice.domain.event.DomainEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ch.globaz.tmmas.personnesservice.messaging.MessagingService;

@Component
public class JMSMessagingService implements MessagingService {

    @Autowired
    JMSMessageSender jmsSender;

    @Override
    public void notify(DomainEvent event) {
        jmsSender.sendMessage(event.toString());
    }
}
