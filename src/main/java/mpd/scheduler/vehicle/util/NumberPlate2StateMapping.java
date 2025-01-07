package mpd.scheduler.vehicle.util;

import java.util.HashMap;
import java.util.Map;

public class NumberPlate2StateMapping {

    private static final Map<String, String> stateCodes = new HashMap<>();

    static {
        stateCodes.put("AP", "Andhra Pradesh");
        stateCodes.put("AR", "Arunachal Pradesh");
        stateCodes.put("AS", "Assam");
        stateCodes.put("BR", "Bihar");
        stateCodes.put("CG", "Chhattisgarh");
        stateCodes.put("GA", "Goa");
        stateCodes.put("GJ", "Gujarat");
        stateCodes.put("HR", "Haryana");
        stateCodes.put("HP", "Himachal Pradesh");
        stateCodes.put("JK", "Jammu and Kashmir");
        stateCodes.put("JH", "Jharkhand");
        stateCodes.put("KA", "Karnataka");
        stateCodes.put("KL", "Kerala");
        stateCodes.put("MP", "Madhya Pradesh");
        stateCodes.put("MH", "Maharashtra");
        stateCodes.put("MN", "Manipur");
        stateCodes.put("ML", "Meghalaya");
        stateCodes.put("MZ", "Mizoram");
        stateCodes.put("NL", "Nagaland");
        stateCodes.put("OD", "Odisha");
        stateCodes.put("PB", "Punjab");
        stateCodes.put("RJ", "Rajasthan");
        stateCodes.put("SK", "Sikkim");
        stateCodes.put("TN", "Tamil Nadu");
        stateCodes.put("TS", "Telangana");
        stateCodes.put("TR", "Tripura");
        stateCodes.put("UP", "Uttar Pradesh");
        stateCodes.put("UK", "Uttarakhand");
        stateCodes.put("WB", "West Bengal");
        stateCodes.put("AN", "Andaman and Nicobar Islands");
        stateCodes.put("CH", "Chandigarh");
        stateCodes.put("DN", "Dadra and Nagar Haveli and Daman and Diu");
        stateCodes.put("DL", "Delhi");
        stateCodes.put("LD", "Lakshadweep");
        stateCodes.put("PY", "Puducherry");
        // Add more state codes as needed
    }

    public static String getStateName(String numberPlate) {
        if (numberPlate == null || numberPlate.length() < 2) {
            throw new IllegalArgumentException("Invalid number plate");
        }

        String stateCode = numberPlate.substring(0, 2).toUpperCase();
        return stateCodes.getOrDefault(stateCode, "Unknown State");
    }

}
