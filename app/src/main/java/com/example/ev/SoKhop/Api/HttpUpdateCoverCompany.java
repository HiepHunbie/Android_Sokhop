package com.example.ev.SoKhop.Api;

import android.app.Activity;

import com.example.ev.SoKhop.Api.core.API;

/**
 * Created by MSI on 10/11/2016.
 */

public class HttpUpdateCoverCompany extends API {

    private String accesstoken ;
    public HttpUpdateCoverCompany(Activity context, String token) {
        super(context);
        accesstoken = token;
    }

    @Override
    protected APIMethods getMethod() {
        return APIMethods.POST;
    }

    @Override
    protected String getUrl() {
        return APIConstants.UPDATE_COMPANY_COVER_URL +"?access_token="+ accesstoken;
    }

    public void request(String cover_url,APIDelegate httpRequestCallback) {

//        addParam("access_token",access_token);
        addParam("cover_url",cover_url);
        super.request(httpRequestCallback, true);
    }
}
