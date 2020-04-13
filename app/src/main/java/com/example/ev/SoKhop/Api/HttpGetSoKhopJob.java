package com.example.ev.SoKhop.Api;

import android.app.Activity;

import com.example.ev.SoKhop.Api.core.API;

/**
 * Created by MSI on 10/20/2016.
 */

public class HttpGetSoKhopJob extends API {

    private String access_token ;
    private String page_id,user_id;
    public HttpGetSoKhopJob(Activity context, String token,String user_ids, String id) {
        super(context);
        access_token = token;
        page_id = id;
        user_id = user_ids;
    }

    @Override
    protected APIMethods getMethod() {
        return APIMethods.GET;
    }

    @Override
    protected String getUrl() {
        return APIConstants.GET_ALL_MATCHING_JOB_URL +"?access_token="+ access_token+"&user_id="+ user_id+"&page="+page_id;
    }

    public void request(APIDelegate httpRequestCallback) {

//        addParam("access_token",access_token);
        super.request(httpRequestCallback, true);
    }
}
