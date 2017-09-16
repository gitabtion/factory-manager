package net.hrsoft.transparent_factory_manager.utils;

/**
 * Created by abtion on 17/9/17.
 */

public class SubTimeStringUtil {
    public static String subTimeString(String timeData){
        if (timeData.length()>10){
            return timeData.substring(0,timeData.length()-9);
        }
        return timeData;
    }
}
