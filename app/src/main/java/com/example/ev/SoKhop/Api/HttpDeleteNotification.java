package com.example.ev.SoKhop.Api;

import android.app.Activity;

import com.example.ev.SoKhop.Api.core.API;

/**
 * Created by MSI on 11/1/2016.
 */

public class HttpDeleteNotification extends API {
    private String access_token;

    public HttpDeleteNotification(Activity context, String token) {
        super(context);
        access_token = token;
    }

    @Override
    protected APIMethods getMethod() {
        return APIMethods.DELETE;
    }

    @Override
    protected String getUrl() {
        return APIConstants.DELETE_NOTIFICATION_URL + "?access_token=" + access_token;
    }

    public void request(String notification_ids, APIDelegate httpRequestCallback) {

        addParam("&notification_ids", notification_ids);
        // request
        super.request(httpRequestCallback, true);
    }
}
