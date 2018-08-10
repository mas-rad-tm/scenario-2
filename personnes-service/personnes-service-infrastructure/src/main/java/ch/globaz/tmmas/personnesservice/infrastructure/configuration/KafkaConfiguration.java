package ch.globaz.tmmas.personnesservice.infrastructure.configuration;



import ch.globaz.tmmas.personnesservice.domain.event.DomainEvent;
import ch.globaz.tmmas.personnesservice.infrastructure.messaging.kafka.KafkaTopics;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfiguration {

	@Value(value = "${dossierCree.topic.name}")
	public String dossierCreeTopicName;

	@Value(value = "${personneVerifie.topic.name}")
	private String personneVerifie;

	@Value(value = "${kafka.bootstrapAddress}")
	private String bootstrapAddress;


	@Bean
	public ProducerFactory<String, String> producerFactory() {
		Map<String, Object> configProps = new HashMap<>();
		configProps.put(
				ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
				bootstrapAddress);
		configProps.put(
				ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
				StringSerializer.class);
		configProps.put(
				ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
				StringSerializer.class);
		return new DefaultKafkaProducerFactory<>(configProps);
	}

	@Bean
	public KafkaTemplate<String, String> kafkaTemplate() {

		KafkaTemplate<String, String> kafkaTemplate = new KafkaTemplate<>(producerFactory());
		kafkaTemplate.setMessageConverter(new StringJsonMessageConverter());
		kafkaTemplate.setDefaultTopic(KafkaTopics.PERSONNE_VERIFIE.nom());
		return kafkaTemplate;
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> jsonKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory =
				new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		factory.setMessageConverter(new StringJsonMessageConverter());
		return factory;
	}

	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(
				ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
				bootstrapAddress);
		props.put(
				ConsumerConfig.GROUP_ID_CONFIG,
				"personnes-service-group");
		props.put(
				ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
				StringDeserializer.class);
		props.put(
				ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
				StringDeserializer.class);
		props.put(JsonDeserializer.TRUSTED_PACKAGES,"ch.globaz.tmmas.rentesservice.domain.event");
		return new DefaultKafkaConsumerFactory<>(props);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String>
	kafkaListenerContainerFactory() {

		ConcurrentKafkaListenerContainerFactory<String, String> factory
				= new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}
}
