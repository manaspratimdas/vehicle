package mpd.scheduler.vehicle.publish;

import java.sql.Timestamp;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import mpd.scheduler.vehicle.domain.VehicleRaw;
import mpd.scheduler.vehicle.util.IndianVehicleBrandGenerator;
import mpd.scheduler.vehicle.util.VinGenerator;

@Component
public class VehicleRawPublisher implements Job{

    @Autowired
	KafkaTemplate<String, VehicleRaw> kafkaTemplate;

    
    VehicleRaw vehicleRaw=new VehicleRaw("xx","", "mh14ar1224", "Maruti", "",new Timestamp(System.currentTimeMillis()));
    //Vehicle (String id, Long VehicleId, String plateNumber, String brand, String State)

   

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        System.out.println("Staring exectute");

        String eventId = UUID.randomUUID().toString();

        VehicleRaw vehicleRaw=new VehicleRaw(eventId, VinGenerator.generateRandomVin(),"", IndianVehicleBrandGenerator.getRandomVehicleBrand(), "",new Timestamp(System.currentTimeMillis()));
          
        CompletableFuture<SendResult<String, VehicleRaw>> future = kafkaTemplate.send("vehical-data-topic", eventId, vehicleRaw);
        

        future.whenComplete((result, ex) -> {
            if (ex != null) {
                // Handle exception
                System.err.println("Failed to send message: " + ex.getMessage());
            } else {
                // Process the result
                System.out.println("Message sent successfully: " + result);
            }
        });
    
           
        
        
    }



  
    // @Override
    // public void execute(JobExecutionContext context) throws JobExecutionException {
    //     String data = "Generated data at " + System.currentTimeMillis();
    //   kafkaTemplate.send("vehical-data-topic", data);
    //     System.out.println("Data sent to Kafka: " + data);
    // }
}
