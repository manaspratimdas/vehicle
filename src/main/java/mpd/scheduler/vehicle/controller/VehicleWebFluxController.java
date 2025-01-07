package mpd.scheduler.vehicle.controller;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mpd.scheduler.vehicle.domain.VehicleRaw;
import mpd.scheduler.vehicle.domain.TimestampRange;
import mpd.scheduler.vehicle.service.KafkaWebfluxConsumerListener;
import reactor.core.publisher.Flux;

@RestController
public class VehicleWebFluxController {


    private final KafkaWebfluxConsumerListener kafkaConsumerListener;

    @Autowired
    public VehicleWebFluxController(KafkaWebfluxConsumerListener kafkaConsumerListener) {
        this.kafkaConsumerListener = kafkaConsumerListener;
    }

    @GetMapping("/messages")
    public Flux<VehicleRaw> getMessages() {
        return kafkaConsumerListener.getMessages();
    }

    @PostMapping("/messages/filter")
    public Flux<VehicleRaw> getMessagesWithinRange(@RequestBody TimestampRange range) {
        Timestamp start = range.start();
        Timestamp end = range.end();
        return kafkaConsumerListener.getMessages()
                .filter(vehicleRaw -> vehicleRaw.currentTimestamp().after(start) && vehicleRaw.currentTimestamp().before(end));
    }

    @GetMapping("/test/flux")
    public Flux<Integer> myFlux(){

        return Flux.just(1,2,3);

    }


    
}
