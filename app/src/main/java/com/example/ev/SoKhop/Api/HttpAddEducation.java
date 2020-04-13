package com.example.ev.SoKhop.Api;

import android.app.Activity;

import com.example.ev.SoKhop.Api.core.API;

/**
 * Created by MSI on 10/12/2016.
 */

public class HttpAddEducation extends API {

    private String imagepath ;
    public HttpAddEducation(Activity context, String token) {
        super(context);
        imagepath = token;
    }

    @Override
    protected API.APIMethods getMethod() {
        return API.APIMethods.POST;
    }

    @Override
    protected String getUrl() {
        return APIConstants.UPDATE_EDUCATION_URL +"?access_token="+ imagepath;
    }

    public void request(String start_time,String end_time,String degree,String img_url,String video_url,String description,String school_name,String major,API.APIDelegate httpRequestCallback) {

        addParam("start_time",start_time);
        addParam("end_time",end_time);
        addParam("degree",degree);
        if(img_url!=null){
            addParam("img_url",img_url);
        }
        if(video_url!=null){
            addParam("video_url",video_url);
        }
        if(description!=null){
            addParam("description",description);
        }
        addParam("school_name",school_name);
        addParam("major",major);
        super.request(httpRequestCallback, true);
    }
}
