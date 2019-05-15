package pl.servx.servx.Model;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

public class SharePref {

    public static final String PREF_NAME = "userinfo";
    public static final String PREF_KEY = "username";
    public static final ArrayList<String> PREF_CAR=new ArrayList<>();

    public SharePref() {
    }

    public void save(Context context, String text) {
        SharedPreferences sharePref;
        SharedPreferences.Editor editor;
        sharePref = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        editor = sharePref.edit();
        editor.putString(PREF_KEY,text);
        editor.apply();
    }

//    public  void  save_list(Context context, ArrayList<String> car){
//        SharedPreferences sharePref;
//        SharedPreferences.Editor editor;
//        sharePref = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
//        editor = sharePref.edit();
//        Set<String> set = new HashSet<String>();
//        set.addAll(car);
//        editor.putStringSet("car", set);
//        editor.apply();
//    }

    public String getData(Context context) {
        SharedPreferences sharePref;
        String text;
        sharePref = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        text = sharePref.getString(PREF_KEY,null);
        return text;
    }

//    public ArrayList<String> get_list (Context context){
//        SharedPreferences sharePref;
//        String text;
//        sharePref = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
//        Set<String> set = sharePref.getStringSet("key", null);
//        if (set!=null){
//        ArrayList<String>= new ArrayList<>(set);}
//
//
//    }
}
