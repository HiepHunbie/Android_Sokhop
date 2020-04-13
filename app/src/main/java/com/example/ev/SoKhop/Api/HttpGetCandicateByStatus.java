package com.example.ev.SoKhop.Api;

import android.app.Activity;

import com.example.ev.SoKhop.Api.core.API;

/**
 * Created by MSI on 10/26/2016.
 */

public class HttpGetCandicateByStatus extends API {

    private String access_token ;
    private String job_id;
    private String status_id ;
    private String page_id ;
    public HttpGetCandicateByStatus(Activity context, String token, String job_ids, String status_ids,String page_ids) {
        super(context);
        access_token = token;
        job_id = job_ids;
        status_id = status_ids;
        page_id = page_ids;
    }

    @Override
    protected APIMethods getMethod() {
        return APIMethods.GET;
    }

    @Override
    protected String getUrl() {
        return APIConstants.GET_CANDICATE_BY_STATUS_URL +"?access_token="+ access_token+"&job_id="+job_id+"&status_id="+status_id+"&page="+page_id;
    }

    public void request(APIDelegate httpRequestCallback) {

//        addParam("access_token",access_token);
        super.request(httpRequestCallback, true);
    }
}
