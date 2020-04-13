package com.example.ev.SoKhop.Gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by MSI on 10/3/2016.
 */

public class GsonUserDetail {
    @SerializedName("user_id")
    public int user_id;
    @SerializedName("email")
    public String email;
    @SerializedName("token")
    public String token;
    @SerializedName("refresh_token")
    public String refresh_token;
    @SerializedName("expried_at")
    public String expried_at;
    @SerializedName("type")
    public int type;
}
