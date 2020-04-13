package com.example.ev.SoKhop.Api;

import android.app.Activity;

import com.example.ev.SoKhop.Api.core.API;

/**
 * Created by MSI on 10/17/2016.
 */

public class HttpGetRecruitment extends API {

    private String access_token ;
    private String status_id ;
    private String page_id ;
    private String limit_id ;
    public HttpGetRecruitment(Activity context, String token, String status, String page,String limit) {
        super(context);
        access_token = token;
        status_id = status;
        page_id = page;
        limit_id = limit;
    }

    @Override
    protected APIMethods getMethod() {
        return APIMethods.GET;
    }

    @Override
    protected String getUrl() {
        return APIConstants.GET_RECRUITMENT_JOB_URL +"?access_token="+ access_token +"&status="+status_id+"&page="+page_id+"&limit="+limit_id;
    }

    public void request(APIDelegate httpRequestCallback) {

//        addParam("access_token",access_token);
        super.request(httpRequestCallback, true);
    }
}
