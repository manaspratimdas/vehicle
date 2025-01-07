package mpd.scheduler.vehicle.domain;

import java.sql.Timestamp;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
import java.sql.Timestamp;

@Document(collection = "vehicles")
public record VehicleRaw(
    @Id String id,
    String vehicleId,
    String plateNumber,
    String brand,
    String state,
    Timestamp currentTimestamp
) implements Serializable {}

