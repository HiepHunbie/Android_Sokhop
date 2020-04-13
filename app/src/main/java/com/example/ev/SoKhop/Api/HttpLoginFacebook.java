package com.example.ev.SoKhop.Api;

import android.app.Activity;

import com.example.ev.SoKhop.Api.core.API;

/**
 * Created by MSI on 10/7/2016.
 */

public class HttpLoginFacebook extends API {

    public HttpLoginFacebook(Activity context) {
        super(context);
    }

    @Override
    protected APIMethods getMethod() {
        return APIMethods.POST;
    }

    @Override
    protected String getUrl() {
        return APIConstants.LOGIN_FACEBOOK_URL;
    }

    public void request(String fb_token,APIDelegate httpRequestCallback) {

        addParam("fb_token",fb_token);
        // request
        super.request(httpRequestCallback, true);
    }
}
