package com.example.ev.SoKhop.Api;

import android.app.Activity;

import com.example.ev.SoKhop.Api.core.API;

/**
 * Created by MSI on 10/12/2016.
 */

public class HttpAddNewAlbum extends API {
    private String access_token ;
    public HttpAddNewAlbum(Activity context, String token) {
        super(context);
        access_token = token;
    }

    @Override
    protected APIMethods getMethod() {
        return APIMethods.POST;
    }

    @Override
    protected String getUrl() {
        return APIConstants.ADD_NEW_ALBUM_URL +"?access_token="+ access_token;
    }

    public void request(String album_name, APIDelegate httpRequestCallback) {

        addParam("album_name",album_name);
        // request
        super.request(httpRequestCallback, true);
    }
}
