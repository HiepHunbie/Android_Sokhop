package com.example.ev.SoKhop.Api;

import android.app.Activity;

import com.example.ev.SoKhop.Api.core.API;

/**
 * Created by MSI on 10/14/2016.
 */

public class HttpGetPositionJobs  extends API {

    private String access_token ;
    public HttpGetPositionJobs(Activity context, String token) {
        super(context);
        access_token = token;
    }

    @Override
    protected APIMethods getMethod() {
        return APIMethods.GET;
    }

    @Override
    protected String getUrl() {
        return APIConstants.GET_POSITION_JOB_URL +"?access_token="+ access_token;
    }

    public void request(APIDelegate httpRequestCallback) {

//        addParam("access_token",access_token);
        super.request(httpRequestCallback, true);
    }
}