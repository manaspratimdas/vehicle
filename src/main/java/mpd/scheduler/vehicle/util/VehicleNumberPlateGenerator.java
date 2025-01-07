package mpd.scheduler.vehicle.util;

import java.util.Random;

public class VehicleNumberPlateGenerator {

     private static final String STATE_CODES = "AP,AR,AS,BR,CG,GA,GJ,HR,HP,JH,KA,KL,MP,MH,MN,ML,MZ,NL,OD,PB,RJ,SK,TN,TS,TR,UP,UK,WB";
    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final Random RANDOM = new Random();

    // public static void main(String[] args) {
    //     String vehicleNumberPlate = generateVehicleNumberPlate();
    //     System.out.println("Random Vehicle Number Plate: " + vehicleNumberPlate);
    // }

    public static String generateVehicleNumberPlate() {
        String[] stateCodes = STATE_CODES.split(",");
        String stateCode = stateCodes[RANDOM.nextInt(stateCodes.length)];
        String districtCode = String.format("%02d", RANDOM.nextInt(100));
        String letters = "" + LETTERS.charAt(RANDOM.nextInt(LETTERS.length())) + LETTERS.charAt(RANDOM.nextInt(LETTERS.length()));
        String numbers = String.format("%04d", RANDOM.nextInt(10000));

        return stateCode + districtCode + letters + numbers;
    }
}


