package kafkafirst.configuration;

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

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

	@Value("${spring.kafka.consumer.bootstrap-servers}")
	public String BOOTSTRAP_SERVER;
	@Value("${spring.kafka.consumer.group-id}")
	public String GROUP_ID;
	@Value("${spring.kafka.consumer.client-id}")
	public String CLIENT_ID;
	@Value("${spring.kafka.consumer.auto-offset-reset}")
	public String AUTO_OFFSET_RESET;
	@Value("${spring.kafka.consumer.enable-auto-commit}")
	public String ENABLE_AUTO_COMMIT;
	@Value("${spring.kafka.consumer.auto-commit-interval}")
	public String AUTO_COMMIT_INTERVAL;
	@Value("${spring.kafka.consumer.max-poll-records}")
	public String MAX_POLL_RECORDS;
	
	public Map<String, Object> consumerProps () {
		HashMap<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
		props.put(ConsumerConfig.CLIENT_ID_CONFIG, CLIENT_ID);
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, AUTO_OFFSET_RESET);
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, ENABLE_AUTO_COMMIT);
		props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, AUTO_COMMIT_INTERVAL);
		props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, MAX_POLL_RECORDS);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		
		return props;
	}
	
	@Bean
	public ConsumerFactory<String, String> consumerFactory () {
		return new DefaultKafkaConsumerFactory<>(consumerProps());
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(){
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		
		return factory;
	}
	
	
}
