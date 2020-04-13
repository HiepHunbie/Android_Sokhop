package com.example.ev.SoKhop.Api;

import android.app.Activity;

import com.example.ev.SoKhop.Api.core.API;

/**
 * Created by MSI on 11/1/2016.
 */

public class HttpPostRegisterDeviceNotification extends API {

    private String accesstoken ;
    public HttpPostRegisterDeviceNotification(Activity context,String token) {
        super(context);
        accesstoken = token;
    }

    @Override
    protected APIMethods getMethod() {
        return APIMethods.POST;
    }

    @Override
    protected String getUrl() {
        return APIConstants.POST_NOTIFICATION_REGISTER_DEVICE_URL +"?access_token="+accesstoken;
    }

    public void request(String onesignal_uid, String gcm_id,
                        String device_id, String device_type,APIDelegate httpRequestCallback) {

        addParam("onesignal_uid",onesignal_uid);
        addParam("gcm_id",gcm_id);
        addParam("device_id",device_id);
        addParam("device_type",device_type);
        // request
        super.request(httpRequestCallback, true);
    }
}
