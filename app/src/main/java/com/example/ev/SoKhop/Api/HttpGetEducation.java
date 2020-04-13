package com.example.ev.SoKhop.Api;

import android.app.Activity;

import com.example.ev.SoKhop.Api.core.API;

/**
 * Created by MSI on 10/12/2016.
 */

public class HttpGetEducation extends API {

    private String access_token ;
    private String user_id;
    public HttpGetEducation(Activity context, String token,String id) {
        super(context);
        access_token = token;
        user_id = id;
    }

    @Override
    protected APIMethods getMethod() {
        return APIMethods.GET;
    }

    @Override
    protected String getUrl() {
        return APIConstants.GET_EDUCATION_URL +"?access_token="+ access_token+"&user_id="+user_id;
    }

    public void request(APIDelegate httpRequestCallback) {

//        addParam("access_token",access_token);
        super.request(httpRequestCallback, true);
    }
}
