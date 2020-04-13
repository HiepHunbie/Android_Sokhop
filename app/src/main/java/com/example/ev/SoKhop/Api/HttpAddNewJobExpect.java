package com.example.ev.SoKhop.Api;

import android.app.Activity;

import com.example.ev.SoKhop.Api.core.API;

/**
 * Created by MSI on 10/14/2016.
 */

public class HttpAddNewJobExpect extends API {

    private String accesstoken ;
    public HttpAddNewJobExpect(Activity context, String token) {
        super(context);
        accesstoken = token;
    }

    @Override
    protected API.APIMethods getMethod() {
        return API.APIMethods.POST;
    }

    @Override
    protected String getUrl() {
        return APIConstants.ADD_NEW_JOB_EXPECT_URL +"?access_token="+ accesstoken;
    }

    public void request(String salary_basic,String bonus,String allowance,String objective,String description
            ,String position,String location,String position_ids,String location_ids,String range_salary_id
            ,String range_allowance_id,String range_bonus_id,String time_type,API.APIDelegate httpRequestCallback) {

        addParam("salary_basic",salary_basic);
        if(bonus!=null){
            addParam("bonus",bonus);
        }
        if(allowance!=null){
            addParam("allowance",allowance);
        }
        if(objective!=null){
            addParam("objective",objective);
        }
        if(description!=null){
            addParam("description",description);
        }
        if(position!=null){
            addParam("position",position);
        }
        if(location!=null){
            addParam("location",location);
        }
        if(position_ids!=null){
            addParam("position_ids",position_ids);
        }
        if(location_ids!=null){
            addParam("location_ids",location_ids);
        }
        if(range_salary_id!=null){
            addParam("range_salary_id",range_salary_id);
        }
        if(range_allowance_id!=null){
            addParam("range_allowance_id",range_allowance_id);
        }
        if(range_bonus_id!=null){
            addParam("range_bonus_id",range_bonus_id);
        }
        if(time_type!=null){
            addParam("time_type",time_type);
        }
        super.request(httpRequestCallback, true);
    }
}
