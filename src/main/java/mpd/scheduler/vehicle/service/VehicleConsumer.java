package mpd.scheduler.vehicle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import mpd.scheduler.vehicle.domain.VehicleRaw;
import mpd.scheduler.vehicle.repository.VehicleRepository;

@Component
public class VehicleConsumer {

    @Autowired
    private VehicleRepository vehicleRepository;

    @KafkaListener(topics = "vehical-data-refined-topic", groupId = "vehicle-record-created-events")
    public void consume(VehicleRaw vehicleRaw) {
        System.out.println("In vehicleRepository for saving to monogdb");
        vehicleRepository.save(vehicleRaw);
    }

}
