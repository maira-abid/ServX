package pl.servx.servx.Model;

import java.util.HashMap;
import java.util.Map;

public class cart_data {

    public static String OilChange="";
    public static String CarWash="";
    public static int OilGold, OilSilver, OilBronze, CarGold, CarSilver, CarBronze, costOil, costWash;
    public static Map<String, String> dict= new HashMap<String,String>();


    public void setData(String oil, String wash){
        OilChange= oil;
        CarWash=wash;
    }
}
