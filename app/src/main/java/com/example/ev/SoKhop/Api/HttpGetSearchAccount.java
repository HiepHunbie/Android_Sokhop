package com.example.ev.SoKhop.Api;

import android.app.Activity;

import com.example.ev.SoKhop.Api.core.API;

/**
 * Created by MSI on 11/3/2016.
 */

public class HttpGetSearchAccount extends API {

    private String access_token,ques;
    public HttpGetSearchAccount(Activity context, String token, String q) {
        super(context);
        access_token = token;
        ques = q;
    }

    @Override
    protected APIMethods getMethod() {
        return APIMethods.GET;
    }

    @Override
    protected String getUrl() {
        return APIConstants.GET_SEARCH_ACCOUNTS_URL +"?access_token="+ access_token+"&q="+ques ;
    }
    public void request(APIDelegate httpRequestCallback) {

//        addParam("access_token",access_token);
        super.request(httpRequestCallback, true);
    }
}
