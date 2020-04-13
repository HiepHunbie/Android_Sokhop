package com.example.ev.SoKhop.Api;

import android.app.Activity;

import com.example.ev.SoKhop.Api.core.API;

/**
 * Created by MSI on 11/3/2016.
 */

public class HttpDeleteOnesignalId extends API {
    private String access_token;

    public HttpDeleteOnesignalId(Activity context, String token) {
        super(context);
        access_token = token;
    }

    @Override
    protected APIMethods getMethod() {
        return APIMethods.DELETE_BODY;
    }

    @Override
    protected String getUrl() {
        return APIConstants.DELETE_ONESIGNAL_ID_URL + "?access_token=" + access_token;
    }

    public void request(String onesignal_id, APIDelegate httpRequestCallback) {

        addParam("onesignal_id", onesignal_id);
        // request
        super.request(httpRequestCallback, true);
    }
}
