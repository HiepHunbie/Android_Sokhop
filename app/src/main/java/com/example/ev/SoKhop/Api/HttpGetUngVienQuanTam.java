package com.example.ev.SoKhop.Api;

import android.app.Activity;

import com.example.ev.SoKhop.Api.core.API;

/**
 * Created by MSI on 10/22/2016.
 */

public class HttpGetUngVienQuanTam extends API {

    private String access_token ,user_id;
    public HttpGetUngVienQuanTam(Activity context, String token,String recruiter_id) {
        super(context);
        access_token = token;
        user_id = recruiter_id;
    }

    @Override
    protected APIMethods getMethod() {
        return APIMethods.GET;
    }

    @Override
    protected String getUrl() {
        return APIConstants.GET_JOB_EXPECT_URL +"?access_token="+ access_token+"&recruiter_id="+user_id;
    }

    public void request(APIDelegate httpRequestCallback) {

//        addParam("access_token",access_token);
        super.request(httpRequestCallback, true);
    }
}
