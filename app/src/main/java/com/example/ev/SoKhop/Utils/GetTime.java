package com.example.ev.SoKhop.Utils;

import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by MSI on 10/12/2016.
 */

public class GetTime {

    public static String dateTimeToday(){
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        dateFormatter.setLenient(false);
        Date today = new Date();
        String s = dateFormatter.format(today);
        Log.d("dateFormatter","ss"+s);
        return s;
    }


}
