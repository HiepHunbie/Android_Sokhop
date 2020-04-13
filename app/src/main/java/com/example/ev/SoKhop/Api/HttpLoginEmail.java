package com.example.ev.SoKhop.Api;

import android.app.Activity;

import com.example.ev.SoKhop.Api.core.API;

/**
 * Created by MSI on 10/4/2016.
 */

public class HttpLoginEmail extends API {

    public HttpLoginEmail(Activity context) {
        super(context);
    }

    @Override
    protected APIMethods getMethod() {
        return APIMethods.POST;
    }

    @Override
    protected String getUrl() {
        return APIConstants.LOGIN_EMAIL_URL;
    }

    public void request(String email, String password,APIDelegate httpRequestCallback) {

        addParam("email",email);
        addParam("password",password);
        // request
        super.request(httpRequestCallback, true);
    }
}
