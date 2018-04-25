package ch.globaz.tmmas.personnesservice.messaging;

import ch.globaz.tmmas.personnesservice.domain.event.DomainEvent;

public interface MessagingService {
	void notify(DomainEvent event);
}
