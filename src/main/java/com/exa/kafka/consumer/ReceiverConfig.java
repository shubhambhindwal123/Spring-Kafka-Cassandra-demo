package com.exa.kafka.consumer;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.exa.pojo.EventContainerClass;

@Configuration
@EnableKafka
public class ReceiverConfig {

	@Value("${kafka.bootstrap-servers}")
	private String bootstrapServers;

	@Value("${group.groupId}")
	private String eventGroup;
	
	@Bean
	public Map<String, Object> consumerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "jsonTo123223");
		// props.put("auto.commit.enable",false);
		// props.put("auto.offset.reset","latest");
		return props;
	}

	@Bean
	public ConsumerFactory<String, EventContainerClass> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(),
				new JsonDeserializer<>(EventContainerClass.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, EventContainerClass> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, EventContainerClass> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

	@Bean
	public Receiver receiver() {
		return new Receiver();
	}
}
