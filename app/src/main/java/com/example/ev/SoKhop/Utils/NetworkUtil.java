package com.example.ev.SoKhop.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtil {
    public static boolean isNetworkConnect(Context context) {
        boolean status = false;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //check wifi
        NetworkInfo netInfo = cm.getNetworkInfo(1);
        if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED) {
            status = true;
        } else {
            //check mobile internet
            netInfo = cm.getNetworkInfo(0);
            if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED)
                status = true;
            else {
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                return activeNetwork != null && activeNetwork.isConnected();
            }
        }
        return status;
    }

}
