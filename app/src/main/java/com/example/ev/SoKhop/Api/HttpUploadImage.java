package com.example.ev.SoKhop.Api;

import android.app.Activity;

import com.example.ev.SoKhop.Api.core.API;
import com.example.ev.SoKhop.Api.core.ContentType;

import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.File;

/**
 * Created by MSI on 10/4/2016.
 */

public class HttpUploadImage extends API {

    public HttpUploadImage(Activity context) {
        super(context);
    }

    @Override
    protected APIMethods getMethod() {
        return APIMethods.UPLOAD_DATA;
    }

    @Override
    protected String getUrl() {
        return APIConstants.UPLOAD_IMAGE_URL + "/?access_token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1X2lkIjo1MCwidV9lbWFpbCI6ImFjZEBnbm8uY29tIiwicmFuZG9tIjoibGwwVE5mcjF1TDc4IiwiaWF0IjoxNDc1NTUxMDYyfQ.y48nXE9D87CkcBkYiIfC2N_Fgb6VOdku8F9GnTyp19k";
    }

    public void request(String imageFilePath,String token,String album_name,
                        int file_name,
                        String album_id,
                        APIDelegate httpRequestCallback) {
        // force show loading dialog

        // add params
        MultipartEntityBuilder builder = getMultipartEntityBuilder();
//        ContentType utf8ContentType = ContentType.create("text/plain", MIME.UTF8_CHARSET);
        // image_file
        File file = new File(imageFilePath);
        String filename = String.format("%02d.jpg", file_name);
//        ContentType contentType = ContentType.create("image/jpeg");
//        builder.addBinaryBody("file[]", file, contentType, filename);
        addHeader("ENCTYPE", "multipart/form-data");
        addHeader("Content-Type", "multipart/form-data");
        addParam("file[]", imageFilePath,ContentType.CONTENT_TYPE_JPEG);
        addParam("album_name", album_name,ContentType.CONTENT_TYPE_TEXT);
        addParam("album_id", album_id,ContentType.CONTENT_TYPE_TEXT);
//        builder.addTextBody("album_name", album_name);
//        builder.addTextBody("album_id", album_id);
        // request
        super.request(httpRequestCallback,false);
    }
}
