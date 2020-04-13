package com.example.ev.SoKhop.Utils;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;

import com.example.ev.SoKhop.Base.UploadResponse;
import com.example.ev.SoKhop.Api.APIConstants;
import com.example.ev.SoKhop.Network.DataLoader;
import com.example.ev.SoKhop.Network.RequestParams;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by MSI on 10/6/2016.
 */

public class UpLoadImage {
    private static volatile Uri fileUri;
    public static final String TAG_UPLOAD_FILE = "TAG_UPLOAD_FILE";
    public static synchronized DataLoader getDataLoader(String id, Context act, HashMap<String, DataLoader> mLoaders) {
        if (act == null)
            return null;

        if (mLoaders == null)
            mLoaders = new HashMap<String, DataLoader>();
        DataLoader loader = mLoaders.get(id);
        if (loader == null) {
            loader = new DataLoader(act);
            loader.setId(id);
            loader.setDataLoaderListener((DataLoader.DataLoaderListener) act);
            mLoaders.put(id, loader);
        }
        return loader;
    }

    private static Uri getOutputMediaFileUri(int type){
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /** Create a File for saving an image or video */
    private static File getOutputMediaFile(int type){
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
        } else {
            return null;
        }

        return mediaFile;
    }
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static void uploadImage(String imagePath, Context context, HashMap<String, DataLoader> mLoaders, int image,String albumName,int isFragment){
        Uri uri = fileUri;
        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
        RequestParams params = new RequestParams();
        params.put("album_name", albumName);
        File files[] = new File[1];
        try {
            files[0] = new File(new URI(imagePath.replace(" ", "%20")));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try {
            params.put("files[]", files);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        getDataLoader(TAG_UPLOAD_FILE,context,mLoaders).uploadImagesByPost(UploadResponse.class,
                (new UrlFullPath.UrlBuilder().setBaseURL(APIConstants.UPLOAD_IMAGE_URL )
                        .setHasAccessToken(true, context).build().getFullUrl(context)),
                params,context,image,isFragment);
    }

    public static void uploadImageToAlbum(String imagePath, Context context, HashMap<String, DataLoader> mLoaders, int image,String albumId,int isFragment){
        Uri uri = fileUri;
        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
        RequestParams params = new RequestParams();
        params.put("album_id", albumId);
        File files[] = new File[1];
        try {
            files[0] = new File(new URI(imagePath.replace(" ", "%20")));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try {
            params.put("files[]", files);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        getDataLoader(TAG_UPLOAD_FILE,context,mLoaders).uploadImagesByPost(UploadResponse.class,
                (new UrlFullPath.UrlBuilder().setBaseURL(APIConstants.UPLOAD_IMAGE_URL )
                        .setHasAccessToken(true, context).build().getFullUrl(context)),
                params,context,image,isFragment);
    }
}