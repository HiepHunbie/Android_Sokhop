package com.example.ev.SoKhop.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ev.SoKhop.Base.PermissionActivity;
import com.example.ev.SoKhop.Dialog.DialogCall;
import com.example.ev.SoKhop.Gson.GsonUser;
import com.example.ev.SoKhop.Network.RequestResponse;
import com.example.ev.SoKhop.R;
import com.example.ev.SoKhop.Utils.Constants;
import com.example.ev.SoKhop.Utils.PhotoPicker;
import com.example.ev.SoKhop.Utils.Pref;
import com.example.ev.SoKhop.Utils.ProgresBar;
import com.example.ev.SoKhop.Utils.SenUpdate;
import com.example.ev.SoKhop.Utils.UpLoadImage;
import com.example.ev.SoKhop.Api.HttpUploadImage;
import com.example.ev.SoKhop.Api.core.API;
import com.example.ev.SoKhop.Api.core.HttpResponseCode;
import com.example.ev.SoKhop.Network.DataLoader;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by GMAN on 9/26/2016.
 */

public class AvatarActivity extends PermissionActivity implements DataLoader.DataLoaderListener {

    private TextView txtTitle;
    private Button btnNext,btnSkip;
    private boolean theEndChangeImage = false;
    private ImageView imgIcon,imgCoverImage;
    private PhotoPicker mPhotoPicker;
    private GsonUser gsonUser = new GsonUser();
    private SharedPreferences mSharedPreferences;
    private volatile Uri fileUri;
    HashMap<String, DataLoader> mLoaders;
    Handler handler = new Handler();
    private Pref p;
    private boolean isCaNhan;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        mSharedPreferences = getSharedPreferences(Pref.MY_PREFERENCES,0);
        mPhotoPicker = new PhotoPicker(AvatarActivity.this);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        p = new Pref(AvatarActivity.this);
        if(p.getString(Pref.TYPE_USER,null).equals("1")){
            isCaNhan = true;
        }else {
            isCaNhan = false;
        }
        txtTitle = (TextView)findViewById(R.id.txtTitle);
        btnNext = (Button)findViewById(R.id.btnNext);
        btnSkip = (Button)findViewById(R.id.btnSkip);
        imgIcon = (ImageView)findViewById(R.id.imgIcon);
        imgCoverImage = (ImageView)findViewById(R.id.imgCoverImage);
        imgIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p.putString(Pref.NUMBER_IMAGE_CHANGE,"0");
                mPhotoPicker.startPick();
            }
        });

        imgCoverImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p.putString(Pref.NUMBER_IMAGE_CHANGE,"1");
                mPhotoPicker.startPick();
            }
        });
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!theEndChangeImage){
                    txtTitle.setText(R.string.txtCoverImage);
                    theEndChangeImage = true;
                    imgCoverImage.setVisibility(View.VISIBLE);
                    imgIcon.setVisibility(View.GONE);
                }else {
                    Intent intent = new Intent(AvatarActivity.this,FinishInfoActivity.class);
                    startActivity(intent);
                    finish();
                    theEndChangeImage = false;
                    imgCoverImage.setVisibility(View.GONE);
                    imgIcon.setVisibility(View.VISIBLE);
                }
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!theEndChangeImage){
                    txtTitle.setText(R.string.txtCoverImage);
                    theEndChangeImage = true;
                    imgCoverImage.setVisibility(View.VISIBLE);
                    imgIcon.setVisibility(View.GONE);
                }else {
                    Intent intent = new Intent(AvatarActivity.this,FinishInfoActivity.class);
                    startActivity(intent);
                    finish();
                    theEndChangeImage = false;
                    imgCoverImage.setVisibility(View.GONE);
                    imgIcon.setVisibility(View.VISIBLE);
                }
            }
        });
//        upLoadImage("/mnt/storage/emulated/0/DCIM/jetkeys1475487227998.jpg",mSharedPreferences.getString(Pref.PREF_KEY_TOKEN,null),"avatar",12,"12");
}


    @Override
    public void onStart(DataLoader sender) {

    }

    @Override
    public void onCompleted(DataLoader sender, RequestResponse response, Object result, int fragment) {
        if(response.getStatusCode()==200) {
            progressDialog.dismiss();
            if (!theEndChangeImage) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        final String imagepath = p.getString(Pref.PREF_KEY_AVATAR, null);
                        if (imagepath != null) {
                            Glide.with(AvatarActivity.this).load(imagepath).into(imgIcon);
                            if (isCaNhan) {
                                SenUpdate.updateAvatar(p.getString(Pref.PREF_KEY_TOKEN, null), imagepath, AvatarActivity.this);
                            } else {
                                SenUpdate.updateAvatarCompany(p.getString(Pref.PREF_KEY_TOKEN, null), imagepath, AvatarActivity.this);
                            }

                        }
                    }
                }, 0);
            } else {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        final String imagepathCover = p.getString(Pref.PREF_KEY_COVER, null);
                        if (imagepathCover != null) {
                            Glide.with(AvatarActivity.this).load(imagepathCover).into(imgCoverImage);
                            if (isCaNhan) {
                                SenUpdate.updateCoverImage(p.getString(Pref.PREF_KEY_TOKEN, null), imagepathCover, AvatarActivity.this);
                            } else {
                                SenUpdate.updateCoverImageCompany(p.getString(Pref.PREF_KEY_TOKEN, null), imagepathCover, AvatarActivity.this);
                            }
                        }
                    }
                }, 0);
            }
        }else {
            progressDialog.dismiss();
        DialogCall.showResponse(this, getString(R.string.upload_image_fail), new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    }

    @Override
    public void onCompleted(DataLoader sender, RequestResponse response) {

    }

    @Override
    public void onCompletedDUpImage(DataLoader sender, RequestResponse response, Object result, int fragment) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String photoPath = mPhotoPicker.onActivityResult(requestCode, resultCode, data);
        if(photoPath.length()>1){
           progressDialog = ProgresBar.loadingProgress(AvatarActivity.this);
            progressDialog.show();
                final Pref p = new Pref(AvatarActivity.this);
                if(!theEndChangeImage){
                    UpLoadImage.uploadImage(photoPath,AvatarActivity.this,mLoaders,1,Constants.AVATAR,1);
                }else {
                    UpLoadImage.uploadImage(photoPath,AvatarActivity.this,mLoaders,2,Constants.COVER,1);
            }
        }
//        if (!TextUtils.isEmpty(photoPath)) { // handled
//            photoPicked(photoPath);
//            return;
//        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void upLoadImage(final String imagePath, String access_token, String album_name,int file_name, String album_id){
        HttpUploadImage api = new HttpUploadImage(AvatarActivity.this);
            api.request(imagePath, access_token, album_name,file_name,  album_id, new API.APIDelegate() {
                @Override
                public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                    if (httpResponseCode == HttpResponseCode.SUCCESS) {
                    }else {
                    }
                }

            });
        }

}
