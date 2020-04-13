package com.example.ev.SoKhop.Api;

import com.example.ev.SoKhop.Activity.MainActivity;
import com.example.ev.SoKhop.Api.core.API;

/**
 * Created by MSI on 11/1/2016.
 */

public class HttpGetNotification extends API {

    private String access_token ;
    private String notification_type ;
    private String page_id ;
    private String limit_id ;
    public HttpGetNotification(MainActivity context, String token, String notification_types, String page, String limit) {
        super(context);
        access_token = token;
        notification_type = notification_types;
        page_id = page;
        limit_id = limit;
    }

    @Override
    protected APIMethods getMethod() {
        return APIMethods.GET;
    }

    @Override
    protected String getUrl() {
        return APIConstants.GET_NOTIFICATION_URL +"?access_token="+ access_token +"&page="+page_id+"&limit="+limit_id+"&notification_type="+notification_type;
    }

    public void request(APIDelegate httpRequestCallback) {

//        addParam("access_token",access_token);
        super.request(httpRequestCallback, true);
    }
}
