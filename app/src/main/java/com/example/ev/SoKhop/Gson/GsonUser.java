package com.example.ev.SoKhop.Gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by MSI on 10/3/2016.
 */

public class GsonUser {
    @SerializedName("code")
    public int code;
    @SerializedName("data")
    public GsonUserDetail gsonUserDetail = new GsonUserDetail() ;
}
