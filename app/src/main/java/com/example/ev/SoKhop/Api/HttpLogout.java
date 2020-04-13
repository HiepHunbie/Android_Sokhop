package com.example.ev.SoKhop.Api;

import android.app.Activity;

import com.example.ev.SoKhop.Api.core.API;

/**
 * Created by MSI on 10/19/2016.
 */

public class HttpLogout extends API {
    private String access_token ;
    public HttpLogout(Activity context, String token) {
        super(context);
        access_token = token;
    }

    @Override
    protected APIMethods getMethod() {
        return APIMethods.POST;
    }

    @Override
    protected String getUrl() {
        return APIConstants.LOGOUT_URL +"?access_token="+ access_token;
    }

    public void request(APIDelegate httpRequestCallback) {
        // request
        super.request(httpRequestCallback, true);
    }
}
