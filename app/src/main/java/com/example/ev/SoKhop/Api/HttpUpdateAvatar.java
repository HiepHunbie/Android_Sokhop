package com.example.ev.SoKhop.Api;

import android.app.Activity;

import com.example.ev.SoKhop.Api.core.API;

/**
 * Created by MSI on 10/6/2016.
 */

public class HttpUpdateAvatar  extends API {

    private String imagepath ;
    public HttpUpdateAvatar(Activity context,String token) {
        super(context);
        imagepath = token;
    }

    @Override
    protected APIMethods getMethod() {
        return APIMethods.POST;
    }

    @Override
    protected String getUrl() {
        return APIConstants.UPDATE_AVATAR_URL +"?access_token="+ imagepath;
    }

    public void request(String avatar_url,APIDelegate httpRequestCallback) {

//        addParam("access_token",access_token);
        addParam("avatar_url",avatar_url);
        super.request(httpRequestCallback, true);
    }
}
