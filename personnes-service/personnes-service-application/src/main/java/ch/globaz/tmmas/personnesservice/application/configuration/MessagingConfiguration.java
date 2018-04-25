package ch.globaz.tmmas.personnesservice.application.configuration;


import ch.globaz.tmmas.personnesservice.infrastructure.jms.JMSMessageSender;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

import java.util.Arrays;

@Configuration
public class MessagingConfiguration {

    @Value("${activemq.broker-url}")
    private String brokerUrl;

    private static final String PERSONNE_QUEUE = "personnes-services.q";

    @Bean
    public ActiveMQConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(brokerUrl);
        connectionFactory.setTrustedPackages(Arrays.asList("com.websystique.springmvc"));
       // connectionFactory.setRedeliveryPolicy(redeliveryPolicy());
        return connectionFactory;
    }

    @Bean
    public CachingConnectionFactory cachingConnectionFactory(){
        return new CachingConnectionFactory(connectionFactory());
    }



    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(cachingConnectionFactory());
        template.setDefaultDestinationName(PERSONNE_QUEUE);
        return template;
    }

/*
    @Bean
    public RedeliveryPolicy redeliveryPolicy () {
        RedeliveryPolicy policy = new RedeliveryPolicy();
        policy.setBackOffMultiplier(2);
        policy.setInitialRedeliveryDelay(1500);
        policy.setMaximumRedeliveries(3);
        policy.setQueue("*");
        policy.setRedeliveryDelay(1500);
        policy.setUseExponentialBackOff(true);
        return policy;
    }

    @Bean
    public InitializingBean connectionFactory(ActiveMQConnectionFactory connectionFactory)
    {
        return () ->
        {
            RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
            // configure redelivery policy
            connectionFactory.setRedeliveryPolicy(redeliveryPolicy());
        };
    }
*/
    @Bean
    public JMSMessageSender sender() {
        return new JMSMessageSender();
    }
}
