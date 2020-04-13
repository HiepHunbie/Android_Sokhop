package com.example.ev.SoKhop.Api;

import android.app.Activity;

import com.example.ev.SoKhop.Api.core.API;

/**
 * Created by MSI on 11/3/2016.
 */

public class HttpDeleteImage extends API {
    private String access_token;

    public HttpDeleteImage(Activity context, String token) {
        super(context);
        access_token = token;
    }

    @Override
    protected APIMethods getMethod() {
        return APIMethods.DELETE;
    }

    @Override
    protected String getUrl() {
        return APIConstants.DELETE_IMAGE_URL + "?access_token=" + access_token;
    }

    public void request(String image_ids, APIDelegate httpRequestCallback) {

        addParam("&image_ids", image_ids);
        // request
        super.request(httpRequestCallback, true);
    }
}
