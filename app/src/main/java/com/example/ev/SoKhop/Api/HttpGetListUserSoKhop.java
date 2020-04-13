package com.example.ev.SoKhop.Api;

import android.app.Activity;

import com.example.ev.SoKhop.Api.core.API;

/**
 * Created by MSI on 11/2/2016.
 */

public class HttpGetListUserSoKhop extends API {

    private String access_token ;
    private String recruiter_id ;
    public HttpGetListUserSoKhop(Activity context, String token, String recrui_id) {
        super(context);
        access_token = token;
        recruiter_id = recrui_id;
    }

    @Override
    protected APIMethods getMethod() {
        return APIMethods.GET;
    }

    @Override
    protected String getUrl() {
        return APIConstants.GET_INFO_EXTENDS_URL +"?access_token="+ access_token +"&user_ids="+recruiter_id;
    }

    public void request(APIDelegate httpRequestCallback) {

//        addParam("access_token",access_token);
        super.request(httpRequestCallback, true);
    }
}
