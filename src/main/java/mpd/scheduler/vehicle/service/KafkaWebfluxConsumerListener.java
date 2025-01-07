package mpd.scheduler.vehicle.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import mpd.scheduler.vehicle.domain.VehicleRaw;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Service
public class KafkaWebfluxConsumerListener {

    private final Sinks.Many<VehicleRaw> replaySink = Sinks.many().multicast().onBackpressureBuffer();

    @KafkaListener(topics = "vehical-data-refined-topic", groupId = "vehicle-record-created-events")
    public void listen(VehicleRaw vehicleRaw) {
        System.out.println("Received message: " + vehicleRaw);
        replaySink.tryEmitNext(vehicleRaw);
    }

    public Flux<VehicleRaw> getMessages() {
        try {
            System.out.println("you are in KafkaConsumerListener - getMessages()");
            return replaySink.asFlux().log();
        } catch (Exception e) {
            System.err.println("An error occurred while getting messages: " + e.getMessage());
            return Flux.error(e);
        }
    }
    

}
