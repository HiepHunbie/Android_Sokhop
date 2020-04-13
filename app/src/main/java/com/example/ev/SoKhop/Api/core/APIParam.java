package com.example.ev.SoKhop.Api.core;

public class APIParam {
    public String key;
    public String stringValue;
    public String contentType = null;
    public String fileName;
    public CountingFileRequestBody.ProgressListener progressListener = null;

    public APIParam(String key, String value) {
        this.key = key;
        this.stringValue = value;
        this.contentType = null;
        this.fileName = null;
    }

    public APIParam(String key, String value, String contentType) {
        this.key = key;
        this.stringValue = value;
        this.contentType = contentType;
        this.progressListener = null;
        this.fileName = null;
    }
    public APIParam(String key, String fileName, String contentType, String value) {
        this.key = key;
        this.fileName = fileName;
        this.contentType = contentType;
        this.stringValue = value;
    }

    public APIParam(String key, String value, String contentType, CountingFileRequestBody.ProgressListener listener) {
        this.key = key;
        this.stringValue = value;
        this.contentType = contentType;
        this.progressListener = listener;
    }

    @Override
    public String toString() {
        return "APIParam{" +
                "key='" + key + '\'' +
                ", stringValue='" + stringValue + '\'' +
                ", contentType='" + contentType + '\'' +
                '}';
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public CountingFileRequestBody.ProgressListener getProgressListener() {
        return progressListener;
    }

    public void setProgressListener(CountingFileRequestBody.ProgressListener progressListener) {
        this.progressListener = progressListener;
    }
}
