package com.example.ev.SoKhop.Api;

import android.app.Activity;

import com.example.ev.SoKhop.Api.core.API;

/**
 * Created by MSI on 10/11/2016.
 */

public class HttpUpdateAvatarCompany extends API {

    private String accesstoken ;
    public HttpUpdateAvatarCompany(Activity context, String token) {
        super(context);
        accesstoken = token;
    }

    @Override
    protected APIMethods getMethod() {
        return APIMethods.POST;
    }

    @Override
    protected String getUrl() {
        return APIConstants.UPDATE_COMPANY_AVATAR_URL +"?access_token="+ accesstoken;
    }

    public void request(String avatar_url,APIDelegate httpRequestCallback) {

//        addParam("access_token",access_token);
        addParam("avatar_url",avatar_url);
        super.request(httpRequestCallback, true);
    }
}
