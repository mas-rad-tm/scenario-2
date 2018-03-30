package ch.globaz.tmmas.rentesservice.application.event.impl;

import ch.globaz.tmmas.rentesservice.application.event.InternalCommandPublisher;
import ch.globaz.tmmas.rentesservice.domain.command.DomainCommand;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class DomainCommandPublisher implements InternalCommandPublisher {

	@Autowired
	ApplicationEventPublisher commandPublisher;

	@Override
	public void publishCommand(DomainCommand command) {

		MDC.get("correlationid");
		commandPublisher.publishEvent(command);
	}
}
