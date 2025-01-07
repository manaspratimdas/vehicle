package mpd.scheduler.vehicle.util;

import java.util.Random;

public class IndianVehicleBrandGenerator {

     private static final String[] VEHICLE_BRANDS = {
        "Maruti Suzuki", "Hyundai", "Tata Motors", "Mahindra", "Honda",
        "Toyota", "Kia", "Renault", "Ford", "Volkswagen",
        "Nissan", "Skoda", "MG", "Jeep", "Mercedes-Benz",
        "BMW", "Audi", "Jaguar", "Land Rover", "Volvo"
    };

    private static final Random RANDOM = new Random();

    // public static void main(String[] args) {
    //     String randomBrand = getRandomVehicleBrand();
    //     System.out.println("Random Vehicle Brand in India: " + randomBrand);
    // }

    public static String getRandomVehicleBrand() {
        int index = RANDOM.nextInt(VEHICLE_BRANDS.length);
        return VEHICLE_BRANDS[index];
    }
}
