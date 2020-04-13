package com.example.ev.SoKhop.Api;

import android.app.Activity;

import com.example.ev.SoKhop.Api.core.API;

/**
 * Created by MSI on 10/24/2016.
 */

public class HttpGetRecruiter extends API {

    private String access_token ;
    private String recruiter_id ;
    private String page_id ;
    private String limit_id ;
    public HttpGetRecruiter(Activity context, String token, String recrui_id, String page, String limit) {
        super(context);
        access_token = token;
        recruiter_id = recrui_id;
        page_id = page;
        limit_id = limit;
    }

    @Override
    protected APIMethods getMethod() {
        return APIMethods.GET;
    }

    @Override
    protected String getUrl() {
        return APIConstants.GET_RECRUITER_URL +"?access_token="+ access_token +"&recruiter_id="+recruiter_id+"&page="+page_id+"&limit="+limit_id;
    }

    public void request(APIDelegate httpRequestCallback) {

//        addParam("access_token",access_token);
        super.request(httpRequestCallback, true);
    }
}
