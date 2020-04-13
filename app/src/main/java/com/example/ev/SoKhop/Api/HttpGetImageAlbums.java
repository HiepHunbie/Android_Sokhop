package com.example.ev.SoKhop.Api;

import android.app.Activity;

import com.example.ev.SoKhop.Api.core.API;

/**
 * Created by MSI on 10/11/2016.
 */

public class HttpGetImageAlbums extends API {

    private String access_token ;
    private String owner_id;
    public HttpGetImageAlbums(Activity context, String token,String owner_ids) {
        super(context);
        access_token = token;
        owner_id = owner_ids;
    }

    @Override
    protected APIMethods getMethod() {
        return APIMethods.GET;
    }

    @Override
    protected String getUrl() {
        return APIConstants.GET_IMAGE_ALBUM_URL +"?access_token="+ access_token+"&owner_id="+owner_id;
    }

    public void request(APIDelegate httpRequestCallback) {

//        addParam("access_token",access_token);
        super.request(httpRequestCallback, true);
    }
}
