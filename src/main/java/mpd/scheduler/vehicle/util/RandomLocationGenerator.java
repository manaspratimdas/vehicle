package mpd.scheduler.vehicle.util;

import java.util.Random;

public class RandomLocationGenerator {

    private static final double MIN_LATITUDE = 6.0;
    private static final double MAX_LATITUDE = 37.0;
    private static final double MIN_LONGITUDE = 68.0;
    private static final double MAX_LONGITUDE = 97.0;


    // public static void main(String[] args) {
    //     double[] location = generateRandomLocation();
    //     System.out.println("Random Location in India: Latitude = " + location[0] + ", Longitude = " + location[1]);
    // }

    // public static double[] generateRandomLocation() {
    //     Random random = new Random();
    //     double latitude = MIN_LATITUDE + (MAX_LATITUDE - MIN_LATITUDE) * random.nextDouble();
    //     double longitude = MIN_LONGITUDE + (MAX_LONGITUDE - MIN_LONGITUDE) * random.nextDouble();
    //     return new double[]{latitude, longitude};
    // }

}
