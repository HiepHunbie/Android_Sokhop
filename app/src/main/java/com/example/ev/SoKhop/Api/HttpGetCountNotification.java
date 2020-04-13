package com.example.ev.SoKhop.Api;

import com.example.ev.SoKhop.Activity.MainActivity;
import com.example.ev.SoKhop.Api.core.API;

/**
 * Created by MSI on 12/12/2016.
 */

public class HttpGetCountNotification extends API {

    private String access_token ;
    public HttpGetCountNotification(MainActivity context, String token) {
        super(context);
        access_token = token;
    }

    @Override
    protected APIMethods getMethod() {
        return APIMethods.GET;
    }

    @Override
    protected String getUrl() {
        return APIConstants.GET_COUNT_NOTIFICATION_URL +"?access_token="+ access_token ;
    }

    public void request(APIDelegate httpRequestCallback) {

//        addParam("access_token",access_token);
        super.request(httpRequestCallback, true);
    }
}
