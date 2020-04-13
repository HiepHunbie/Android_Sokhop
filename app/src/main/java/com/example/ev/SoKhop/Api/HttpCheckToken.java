package com.example.ev.SoKhop.Api;

import android.app.Activity;

import com.example.ev.SoKhop.Api.core.API;

/**
 * Created by MSI on 10/4/2016.
 */

public class HttpCheckToken extends API {

    public HttpCheckToken(Activity context) {
        super(context);
    }

    @Override
    protected APIMethods getMethod() {
        return APIMethods.GET;
    }

    @Override
    protected String getUrl() {
        return APIConstants.CHECK_TOKEN_URL;
    }

    public void request(String access_token,APIDelegate httpRequestCallback) {

        addParam("access_token",access_token);
        // request
        super.request(httpRequestCallback, true);
    }
}
