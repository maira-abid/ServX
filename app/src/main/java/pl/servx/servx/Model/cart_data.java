package pl.servx.servx.Model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class cart_data {

    public static String OilChange="";
    public static String CarWash="";
    public static String reqid="";
    public static int OilGold, OilSilver, OilBronze, CarGold, CarSilver, CarBronze, costOil, costWash;
    public static Map<String, String> dict= new HashMap<String,String>();
    public static String date =""; public static String time="" ;public static  String location="";


    public void setData(String oil, String wash){
        OilChange= oil;
        CarWash=wash;
    }

    public void setLocation(String loc){
        location = loc;
    }

    public void setdDate(String loc){
        date = loc;
    }

    public void setTime(String loc){
        time = loc;
    }
}