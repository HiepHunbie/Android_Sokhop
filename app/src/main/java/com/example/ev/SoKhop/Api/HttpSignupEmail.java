package com.example.ev.SoKhop.Api;

import android.app.Activity;

import com.example.ev.SoKhop.Api.core.API;

/**
 * Created by MSI on 10/4/2016.
 */

public class HttpSignupEmail extends API {

    public HttpSignupEmail(Activity context) {
        super(context);
    }

    @Override
    protected APIMethods getMethod() {
        return APIMethods.POST;
    }

    @Override
    protected String getUrl() {
        return APIConstants.REGISTER_URL;
    }

    public void request(String email, String password, String full_name, String phone_number, String type, String retype_password, String website, String date_of_birth,APIDelegate httpRequestCallback) {

        addParam("email",email);
        addParam("password",password);
        addParam("full_name",full_name);
        addParam("phone_number",phone_number);
        addParam("user_type",type);
        addParam("retype_password",retype_password);
        if(website!=null){
            addParam("website",website);
        }
        if(date_of_birth!=null){
            addParam("date_of_birth",date_of_birth);
        }
        // request
        super.request(httpRequestCallback, true);
    }
}
