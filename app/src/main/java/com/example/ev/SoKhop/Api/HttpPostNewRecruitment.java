package com.example.ev.SoKhop.Api;

import android.app.Activity;

import com.example.ev.SoKhop.Api.core.API;

/**
 * Created by MSI on 10/14/2016.
 */

public class HttpPostNewRecruitment extends API {

    private String accesstoken ;
    public HttpPostNewRecruitment(Activity context, String token) {
        super(context);
        accesstoken = token;
    }

    @Override
    protected API.APIMethods getMethod() {
        return API.APIMethods.POST;
    }

    @Override
    protected String getUrl() {
        return APIConstants.ADD_NEW_RECRUITMENT_URL +"?access_token="+ accesstoken;
    }

    public void request(String position,String title,String company_id,String categories,String quantity,
                        String level,String time_type,String range_salary_id,String salary,String work_location,
                        String gender,String description,String range_allowance_id,String allowance,String range_bonus_id,
                        String bonus,String extra_desc,String expired_time,String languages_profile,String album_image_id,
                        String album_contract_id,String skills,String year_exps,String exclude_conditions,String require_conditions,
                        String contact,String status,String job_welfare,
                        API.APIDelegate httpRequestCallback) {

        addParam("position",position);
        if(require_conditions!=null){
            addParam("require_conditions",require_conditions);
        }
        if(contact!=null){
            addParam("contact",contact);
        }
        if(status!=null){
            addParam("status",status);
        }
        if(expired_time!=null){
            addParam("expired_time",expired_time);
        }
        if(languages_profile!=null){
            addParam("languages_profile",languages_profile);
        }
        if(album_image_id!=null){
            addParam("album_image_id",album_image_id);
        }
        if(album_contract_id!=null){
            addParam("album_contract_id",album_contract_id);
        }
        if(skills!=null){
            addParam("skills",skills);
        }
        if(year_exps!=null){
            addParam("year_exps",year_exps);
        }
        if(exclude_conditions!=null){
            addParam("exclude_conditions",exclude_conditions);
        }
        if(gender!=null){
            addParam("gender",gender);
        }
        if(description!=null){
            addParam("description",description);
        }
        if(range_allowance_id!=null){
            addParam("range_allowance_id",range_allowance_id);
        }
        if(allowance!=null){
            addParam("allowance",allowance);
        }
        if(range_bonus_id!=null){
            addParam("range_bonus_id",range_bonus_id);
        }
        if(bonus!=null){
            addParam("bonus",bonus);
        }
        if(extra_desc!=null){
            addParam("extra_desc",extra_desc);
        }
        if(title!=null){
            addParam("title",title);
        }
        if(company_id!=null){
            addParam("company_id",company_id);
        }
        if(categories!=null){
            addParam("categories",categories);
        }
        if(quantity!=null){
            addParam("quantity",quantity);
        }
        if(level!=null){
            addParam("level",level);
        }
        if(time_type!=null){
            addParam("time_type",time_type);
        }
        if(range_salary_id!=null){
            addParam("range_salary_id",range_salary_id);
        }
        if(salary!=null){
            addParam("salary",salary);
        }
        if(work_location!=null){
            addParam("work_location",work_location);
        }
        if(job_welfare!=null){
            addParam("welfare",job_welfare);
        }
        super.request(httpRequestCallback, true);
    }
}
