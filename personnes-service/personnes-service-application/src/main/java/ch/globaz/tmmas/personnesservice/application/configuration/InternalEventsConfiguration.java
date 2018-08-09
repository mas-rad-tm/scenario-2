package ch.globaz.tmmas.personnesservice.application.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class InternalEventsConfiguration {

/*
	@Bean(name = "applicationEventMultiCaster")
	public DistributiveEventMultiCaster applicationEventMultiCaster(){
		return new DistributiveEventMultiCaster();
	}

	@Bean(name = "asyncEventMulticaster")
    public ApplicationEventMulticaster asyncEventMulticaster() {
		SimpleApplicationEventMulticaster eventMulticaster
				= new SimpleApplicationEventMulticaster();

		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		//SimpleAsyncTaskExecutor executor = new SimpleAsyncTaskExecutor();
		executor.setTaskDecorator(new MdcTaskDecorator());

		eventMulticaster.setTaskExecutor(executor);
		return eventMulticaster;
	}

	@Bean(name = "syncEventMultiCaster")
	public SimpleApplicationEventMulticaster syncEventMulticaster() {
		return new SimpleApplicationEventMulticaster();
	}
*/



}
