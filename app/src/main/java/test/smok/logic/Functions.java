package test.smok.logic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Kuba on 30.04.2018.
 */

public class Functions {
    public static double checkArea(double lat1, double lon1, double lat2, double lon2){
        double earthRadiusKm = 6371.0;
        double dLat = degreesToRadians(lat2-lat1);
        double dLon = degreesToRadians(lon2-lon1);

        lat1 = degreesToRadians(lat1);
        lat2 = degreesToRadians(lat2);

        double a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(lat1) * Math.cos(lat2);
        double c =  2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return earthRadiusKm*c;
    }

    private static double degreesToRadians(double degrees){
        return degrees * Math.PI /180;
    }

    public static boolean checkRepetitions(String pattern, String s){
        Pattern p = Pattern.compile(pattern);
        int count = 0;
        Matcher m = p.matcher(s);
        while (m.find())
            count++;
        return count == 1;
    }

}
