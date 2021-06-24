package br.com.zup.orange.demo;

import brave.Tracer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	final Tracer tracer;

	public Application(Tracer tracer) {
		this.tracer = tracer;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@KafkaListener(id = "sample.consumer", topics = "cats")
	void consumes(ConsumerRecord<String, String> record) {
		tracer.currentSpan().tag("app.name", "consumer");
		log.info("message: {}", record.value());
	}
}
