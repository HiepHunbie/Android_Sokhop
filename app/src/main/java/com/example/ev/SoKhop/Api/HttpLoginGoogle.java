package com.example.ev.SoKhop.Api;

import android.app.Activity;

import com.example.ev.SoKhop.Api.core.API;

/**
 * Created by MSI on 11/21/2016.
 */

public class HttpLoginGoogle extends API {

    public HttpLoginGoogle(Activity context) {
        super(context);
    }

    @Override
    protected APIMethods getMethod() {
        return APIMethods.POST;
    }

    @Override
    protected String getUrl() {
        return APIConstants.LOGIN_BY_GOOGLE_URL;
    }

    public void request(String google_access_token,APIDelegate httpRequestCallback) {

        addParam("google_access_token",google_access_token);
        // request
        super.request(httpRequestCallback, true);
    }
}
