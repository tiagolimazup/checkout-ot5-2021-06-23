package br.com.zup.orange.demo;

import brave.Tracer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class CatsResource {

    final Tracer tracer;
    final KafkaTemplate<Integer, Cat> producer;

    CatsResource(Tracer tracer, KafkaTemplate<Integer, Cat> producer) {
        this.tracer = tracer;
        this.producer = producer;
    }

    @PostMapping("/cats")
    void cats(@RequestBody Cat cat) {
        tracer.currentSpan()
                .tag("cat.name", cat.name)
                .tag("cat.age", Integer.toString(cat.age));

        producer.send("cats", cat);
    }
}
