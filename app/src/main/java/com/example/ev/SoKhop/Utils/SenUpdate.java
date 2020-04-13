package com.example.ev.SoKhop.Utils;

import android.app.Activity;

import com.example.ev.SoKhop.Api.HttpUpdateAvatar;
import com.example.ev.SoKhop.Api.HttpUpdateAvatarCompany;
import com.example.ev.SoKhop.Api.HttpUpdateCoverCompany;
import com.example.ev.SoKhop.Api.HttpUpdateCoverImage;
import com.example.ev.SoKhop.Api.core.API;
import com.example.ev.SoKhop.Api.core.HttpResponseCode;

import org.json.JSONObject;

/**
 * Created by MSI on 10/6/2016.
 */

public class SenUpdate {
    public static void updateAvatar(String token, String imagePath, Activity activity){
        HttpUpdateAvatar api = new HttpUpdateAvatar(activity,token);
        api.request(imagePath,new API.APIDelegate() {
            @Override
            public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                if (httpResponseCode == HttpResponseCode.SUCCESS) {
                }else {
                }
            }
        });
    }

    public static void updateCoverImage(String token,String imagePath, Activity activity){
        HttpUpdateCoverImage api = new HttpUpdateCoverImage(activity,token);
        api.request(imagePath,new API.APIDelegate() {
            @Override
            public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                if (httpResponseCode == HttpResponseCode.SUCCESS) {
                }else {
                }
            }
        });
    }

    public static void updateAvatarCompany(String token, String imagePath, Activity activity){
        HttpUpdateAvatarCompany api = new HttpUpdateAvatarCompany(activity,token);
        api.request(imagePath,new API.APIDelegate() {
            @Override
            public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                if (httpResponseCode == HttpResponseCode.SUCCESS) {
                }else {
                }
            }
        });
    }

    public static void updateCoverImageCompany(String token,String imagePath, Activity activity){
        HttpUpdateCoverCompany api = new HttpUpdateCoverCompany(activity,token);
        api.request(imagePath,new API.APIDelegate() {
            @Override
            public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                if (httpResponseCode == HttpResponseCode.SUCCESS) {
                }else {
                }
            }
        });
    }

}
