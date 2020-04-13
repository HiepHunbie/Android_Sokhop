package com.example.ev.SoKhop.Api;

import android.app.Activity;

import com.example.ev.SoKhop.Api.core.API;

/**
 * Created by MSI on 11/3/2016.
 */

public class HttpChangePassword extends API {

    private String access_token;
    public HttpChangePassword(Activity context,String token) {
        super(context);
        access_token = token;
    }

    @Override
    protected APIMethods getMethod() {
        return APIMethods.POST;
    }

    @Override
    protected String getUrl() {
        return APIConstants.CHANGE_PASSWORD_URL+"?access_token="+access_token;
    }

    public void request(String old_password,String new_password,String retype_new_password,APIDelegate httpRequestCallback) {

        addParam("old_password",old_password);
        addParam("new_password",new_password);
        addParam("retype_new_password",retype_new_password);
        // request
        super.request(httpRequestCallback, true);
    }
}
