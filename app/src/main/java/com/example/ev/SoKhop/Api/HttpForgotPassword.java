package com.example.ev.SoKhop.Api;

import android.app.Activity;

import com.example.ev.SoKhop.Api.core.API;

/**
 * Created by MSI on 11/3/2016.
 */

public class HttpForgotPassword extends API {

    public HttpForgotPassword(Activity context) {
        super(context);
    }

    @Override
    protected APIMethods getMethod() {
        return APIMethods.POST;
    }

    @Override
    protected String getUrl() {
        return APIConstants.FORGOT_PASSWORD_URL;
    }

    public void request(String email,APIDelegate httpRequestCallback) {

        addParam("email",email);
        // request
        super.request(httpRequestCallback, true);
    }
}
