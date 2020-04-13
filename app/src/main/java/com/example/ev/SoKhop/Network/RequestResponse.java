package com.example.ev.SoKhop.Network;

import com.example.ev.SoKhop.Base.BaseData;
import com.google.gson.Gson;

/**
 * Created by Tomorow on 2/17/2016.
 */
public class RequestResponse {
    private boolean isSuccess;
    private int statusCode;
    private String data;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public static RequestResponse newRequestResponse(boolean isSuccess, String data) {
        RequestResponse response = new RequestResponse();
        response.setIsSuccess(isSuccess);
        response.setStatusCode(isSuccess ? 200 : 400);
        response.setData(data);
        return response;
    }

    public BaseData getBaseData() {
        BaseData baseData = new Gson().fromJson(getData(), BaseData.class);
        return baseData;
    }
}
