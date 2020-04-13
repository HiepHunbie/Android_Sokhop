package com.example.ev.SoKhop.Api;

import android.app.Activity;

import com.example.ev.SoKhop.Api.core.API;

/**
 * Created by MSI on 10/12/2016.
 */

public class HttpDeleteAlbum extends API {
    private String access_token;

    public HttpDeleteAlbum(Activity context, String token) {
        super(context);
        access_token = token;
    }

    @Override
    protected APIMethods getMethod() {
        return APIMethods.DELETE;
    }

    @Override
    protected String getUrl() {
        return APIConstants.DELETE_ALBUM_URL + "?access_token=" + access_token;
    }

    public void request(String id, APIDelegate httpRequestCallback) {

        addParam("&album_ids", id);
        // request
        super.request(httpRequestCallback, true);
    }
}
