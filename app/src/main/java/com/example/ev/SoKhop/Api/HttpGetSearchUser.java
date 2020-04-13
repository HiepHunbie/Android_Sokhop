package com.example.ev.SoKhop.Api;

import android.app.Activity;

import com.example.ev.SoKhop.Api.core.API;

import java.io.UnsupportedEncodingException;

/**
 * Created by MSI on 10/31/2016.
 */

public class HttpGetSearchUser extends API {

    private String access_token ,province_id ,position_id,exp,job_time_type,ques,page;
    private byte[] utf8Bytes;
    public HttpGetSearchUser(Activity context, String token, String province_ids, String position_ids,String exps, String job_time_types, String q, String page_id) {
        super(context);
        access_token = token;
        province_id = province_ids;
        position_id = position_ids;
        job_time_type = job_time_types;
        ques = q;
        page = page_id;
        exp = exps;
        try {
            utf8Bytes = exps.getBytes("UTF8");
            byte[] defaultBytes = exps.getBytes();

            exp = new String(utf8Bytes, "UTF8");
        }catch (UnsupportedEncodingException e) {
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
            return APIConstants.GET_SEARCH_USERS_URL +"?access_token="+ access_token+"&province_id="+province_id+"&position_id="+position_id+"&year_exp="+new String(utf8Bytes, "UTF8")+"&time_type="+job_time_type+"&q="+ques+"&page="+page ;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return APIConstants.GET_SEARCH_USERS_URL +"?access_token="+ access_token+"&province_id="+province_id+"&position_id="+position_id+"&year_exp="+"&time_type="+job_time_type+"&q="+ques+"&page="+page ;
        }
    }
    public void request(APIDelegate httpRequestCallback) {

//        addParam("access_token",access_token);
        super.request(httpRequestCallback, true);
    }
}
