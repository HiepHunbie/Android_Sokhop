package com.example.ev.SoKhop.Base;

import android.app.Activity;
import android.app.Application;

import com.example.ev.SoKhop.Gson.GsonUser;
import com.example.ev.SoKhop.Utils.ConnectivityReceiver;

/**
 * Created by MSI on 10/3/2016.
 */

public class SoKhopApplication extends Application {
    public static SoKhopApplication mApplication;
    public static Activity mActivity;
    public GsonUser gsonUser = new GsonUser();

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }

    public static Activity getmActivity() {
        return mActivity;
    }

    public void setmActivity(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public static SoKhopApplication getmApplication() {
        return mApplication;
    }


    public static synchronized SoKhopApplication getInstance() {
        return mApplication;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }
}
