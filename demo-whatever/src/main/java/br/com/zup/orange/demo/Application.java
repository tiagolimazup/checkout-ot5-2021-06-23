package br.com.zup.orange.demo;

import brave.Span;
import brave.Tracer;
import brave.Tracer.SpanInScope;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.annotation.SpanTag;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

@SpringBootApplication
@RestController
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	final Tracer tracer;

	Application(Tracer tracer) {
		this.tracer = tracer;
	}

	@GetMapping("/")
	String hello() {
		tracer.currentSpan().tag("message", "hello");

		return "hello";
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
