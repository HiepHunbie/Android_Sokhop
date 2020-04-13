package com.example.ev.SoKhop.Api;

import android.app.Activity;

import com.example.ev.SoKhop.Api.core.API;

/**
 * Created by MSI on 10/6/2016.
 */

public class HttpGetUserProfile extends API {

    private String access_token ;
    private String user_id;
    public HttpGetUserProfile(Activity context, String token,String user_id) {
        super(context);
        access_token = token;
        this.user_id = user_id;
    }

    @Override
    protected APIMethods getMethod() {
        return APIMethods.GET;
    }

    @Override
    protected String getUrl() {
        return APIConstants.GET_PROFILE_USER_URL +"/"+user_id+"?access_token="+ access_token;
    }

    public void request(APIDelegate httpRequestCallback) {

//        addParam("access_token",access_token);
        super.request(httpRequestCallback, true);
    }
}
