package kafkafirst.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KafkaConsumerService {

	/*
	 @KafkaListener(containerFactory = "myKafkaListenerContainerFactory", topics = "myTopic")
	 public void process(String msg, @Header("kafka_partition") int partition) {
	 	// process incoming message
	 }
	 */
	@KafkaListener(topics = "${spring.kafka.listener.topic}", groupId = "${spring.kafka.consumer.group-id}"
			, autoStartup = "${spring.kafka.listener.auto-start}")
	public void process (String msg) {
		System.out.println(msg);
		log.debug("[SERVICE]: message received" +"["+msg+"]");
	}
	
}
