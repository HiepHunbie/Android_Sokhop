package com.example.ev.SoKhop.Api;

import android.app.Activity;

import com.example.ev.SoKhop.Api.core.API;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * Created by MSI on 10/27/2016.
 */

public class HttpGetSearchJobs extends API {
    private final Charset UTF8_CHARSET = Charset.forName("UTF-8");
    private byte[] utf8Bytes;
    private String access_token ,province_id,position_id,job_time_type,job_salary,ques,page;
    public HttpGetSearchJobs(Activity context, String token, String province_ids, String position_ids, String job_time_types, String job_salarys,String q,String page_id) {
        super(context);
        access_token = token;
        province_id = province_ids;
        position_id = position_ids;
        job_time_type = job_time_types;
        ques = q;
        page = page_id;
        job_salary = job_salarys;
        try {
           utf8Bytes = job_salarys.getBytes("UTF8");
            byte[] defaultBytes = job_salarys.getBytes();

            job_salary = new String(utf8Bytes, "UTF8");
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected APIMethods getMethod() {
        return APIMethods.GET;
    }

    @Override
    protected String getUrl() {
        try {
            return APIConstants.GET_SEARCH_JOBS_URL +"?access_token="+ access_token+"&province_id="+province_id+"&position_id="+position_id+"&job_time_type="+job_time_type+"&job_salary="+new String(utf8Bytes, "UTF8")+"&q="+ques+"&page="+page ;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return  APIConstants.GET_SEARCH_JOBS_URL +"?access_token="+ access_token+"&province_id="+province_id+"&position_id="+position_id+"&job_time_type="+job_time_type+"&job_salary="+"&q="+ques+"&page="+page ;
        }
    }

    public void request(APIDelegate httpRequestCallback) {

//        addParam("access_token",access_token);
        super.request(httpRequestCallback, true);
    }
}
