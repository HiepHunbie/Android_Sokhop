package com.example.ev.SoKhop.Api;

import android.app.Activity;

import com.example.ev.SoKhop.Api.core.API;

/**
 * Created by MSI on 11/3/2016.
 */

public class HttpConfirmForgotPassword extends API {

    public HttpConfirmForgotPassword(Activity context) {
        super(context);
    }

    @Override
    protected APIMethods getMethod() {
        return APIMethods.POST;
    }

    @Override
    protected String getUrl() {
        return APIConstants.CONFIRM_FORGOT_PASSWORD_URL;
    }

    public void request(String email,String reset_token,APIDelegate httpRequestCallback) {

        addParam("email",email);
        addParam("reset_token",reset_token);
        // request
        super.request(httpRequestCallback, true);
    }
}
