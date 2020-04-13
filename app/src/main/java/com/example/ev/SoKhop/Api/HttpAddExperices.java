package com.example.ev.SoKhop.Api;

import android.app.Activity;

import com.example.ev.SoKhop.Api.core.API;

/**
 * Created by MSI on 10/13/2016.
 */

public class HttpAddExperices extends API {

    private String accesstoken ;
    public HttpAddExperices(Activity context, String token) {
        super(context);
        accesstoken = token;
    }

    @Override
    protected API.APIMethods getMethod() {
        return API.APIMethods.POST;
    }

    @Override
    protected String getUrl() {
        return APIConstants.UPDATE_EXPERICES_URL +"?access_token="+ accesstoken;
    }

    public void request(String company_name,String start_time,String end_time,String position_name,String skills
            ,String img_url,String video_url,String description,String year_of_working,String company_id,API.APIDelegate httpRequestCallback) {

        addParam("company_name",company_name);
        addParam("start_time",start_time);
        addParam("end_time",end_time);
        addParam("position_name",position_name);
        addParam("skills",skills);
        if(img_url!=null){
            addParam("img_url",img_url);
        }
        if(video_url!=null){
            addParam("video_url",video_url);
        }
        if(img_url!=null){
            addParam("description",description);
        }
        if(img_url!=null){
            addParam("year_of_working",year_of_working);
        }
        if(img_url!=null){
            addParam("company_id",company_id);
        }
        super.request(httpRequestCallback, true);
    }
}
