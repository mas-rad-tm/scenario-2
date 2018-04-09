package ch.globaz.tmmas.rentesservice.application.event.impl;

import ch.globaz.tmmas.rentesservice.application.event.InternalCommandPublisher;
import ch.globaz.tmmas.rentesservice.application.event.InternalEventPublisher;
import ch.globaz.tmmas.rentesservice.domain.command.DomainCommand;
import ch.globaz.tmmas.rentesservice.domain.event.DomainEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class DomainEventPublisher implements InternalEventPublisher {

	@Autowired
	ApplicationEventPublisher commandPublisher;

	@Override
	public void publishEvent(DomainEvent event) {

		commandPublisher.publishEvent(event);
	}
}
