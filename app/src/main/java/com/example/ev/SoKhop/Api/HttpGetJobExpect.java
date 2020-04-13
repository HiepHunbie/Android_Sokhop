package com.example.ev.SoKhop.Api;

import android.app.Activity;

import com.example.ev.SoKhop.Api.core.API;

/**
 * Created by MSI on 10/14/2016.
 */

public class HttpGetJobExpect extends API {

    private String access_token ;
    private String user_id;
    public HttpGetJobExpect(Activity context, String token,String user_ids) {
        super(context);
        access_token = token;
        user_id = user_ids;
    }

    @Override
    protected APIMethods getMethod() {
        return APIMethods.GET;
    }

    @Override
    protected String getUrl() {
        return APIConstants.GET_JOB_EXPECT_URL +"?access_token="+ access_token+"&user_id="+user_id;
    }

    public void request(APIDelegate httpRequestCallback) {

//        addParam("access_token",access_token);
        super.request(httpRequestCallback, true);
    }
}
