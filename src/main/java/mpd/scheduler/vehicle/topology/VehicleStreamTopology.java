package mpd.scheduler.vehicle.topology;

import java.sql.Timestamp;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.StreamsBuilderFactoryBeanCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerde;

import mpd.scheduler.vehicle.domain.VehicleRaw;
import mpd.scheduler.vehicle.util.NumberPlate2StateMapping;
import mpd.scheduler.vehicle.util.VehicleNumberPlateGenerator;

@Configuration
@EnableKafkaStreams
public class VehicleStreamTopology {

    @Autowired
	Environment environment;


@Bean
public KStream<String, VehicleRaw> kStream(StreamsBuilder streamsBuilder) {
    KStream<String, VehicleRaw> stream = streamsBuilder.stream("vehical-data-topic", Consumed.with(Serdes.String(), new JsonSerde<>(VehicleRaw.class)));

    KStream<String, VehicleRaw> processedStream = stream.mapValues(vehicleRaw -> {
        // Generate a new number plate
        var numberPlate = VehicleNumberPlateGenerator.generateVehicleNumberPlate();
        
        // Get the state name based on the new number plate
        var newState = NumberPlate2StateMapping.getStateName(numberPlate);
        
        // Return a new VehicleRaw object with the updated number plate and state
        return new VehicleRaw(vehicleRaw.id(), vehicleRaw.vehicleId(), numberPlate, vehicleRaw.brand(), newState,vehicleRaw.currentTimestamp());
    });

    processedStream.to("vehical-data-refined-topic", Produced.with(Serdes.String(), new JsonSerde<>(VehicleRaw.class)));
    return processedStream;
}

        @Bean
    public StreamsBuilderFactoryBeanCustomizer customizer() {
        
        return factoryBean -> factoryBean.setStreamsConfiguration(streamsConfiguration());
    }

    private Properties streamsConfiguration() {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "vehicle-record-streams");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, JsonSerde.class.getName());
      	props.put(JsonDeserializer.TRUSTED_PACKAGES,
				environment.getProperty("spring.kafka.consumer.properties.json.trusted.packages"));
		props.put(ConsumerConfig.GROUP_ID_CONFIG, environment.getProperty("spring.kafka.consumer.group-id"));
        return props;
    }

}
