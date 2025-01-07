package mpd.scheduler.vehicle.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import mpd.scheduler.vehicle.domain.VehicleRaw;

public interface VehicleRepository extends MongoRepository<VehicleRaw, String>{

}
