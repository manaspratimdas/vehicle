package mpd.scheduler.vehicle.util;

import java.util.Random;

public class VinGenerator {

    private static final String VIN_CHARACTERS = "ABCDEFGHJKLMNPRSTUVWXYZ0123456789";
    private static final int VIN_LENGTH = 17;
    private static final Random RANDOM = new Random();

    public static String generateRandomVin() {
        StringBuilder vin = new StringBuilder(VIN_LENGTH);
        for (int i = 0; i < VIN_LENGTH; i++) {
            int index = RANDOM.nextInt(VIN_CHARACTERS.length());
            vin.append(VIN_CHARACTERS.charAt(index));
        }
        return vin.toString();
    }

    public static void main(String[] args) {
        String randomVin = generateRandomVin();
        System.out.println("Generated VIN: " + randomVin);
    }

}
