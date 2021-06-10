package kafkafirst.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KafkaConsumerService {

	@KafkaListener(topics = "${spring.kafka.listener.topic}", groupId = "${spring.kafka.consumer.group-id}"
			, autoStartup = "${spring.kafka.listener.auto-start}")
	public void process (String msg) {
		log.debug("[service]: message from topic" +"[ "+msg+" ]");
	}
	
}
