package com.example.ev.SoKhop.Api;

import android.app.Activity;

import com.example.ev.SoKhop.Api.core.API;

/**
 * Created by MSI on 10/25/2016.
 */

public class HttpGetRecruitmentByJobId extends API {

    private String access_token ;
    private String job_id ;
    public HttpGetRecruitmentByJobId(Activity context, String token, String job_ids) {
        super(context);
        access_token = token;
        job_id = job_ids;
    }

    @Override
    protected APIMethods getMethod() {
        return APIMethods.GET;
    }

    @Override
    protected String getUrl() {
        return APIConstants.GET_RECRUITMENT_BY_JOB_ID_URL +"/"+job_id+"?access_token="+ access_token;
    }

    public void request(APIDelegate httpRequestCallback) {

//        addParam("access_token",access_token);
        super.request(httpRequestCallback, true);
    }
}
