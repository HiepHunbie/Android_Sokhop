package com.example.ev.SoKhop.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.ev.SoKhop.R;
import com.example.ev.SoKhop.Api.HttpCheckToken;
import com.example.ev.SoKhop.Api.core.API;
import com.example.ev.SoKhop.Api.core.HttpResponseCode;
import com.example.ev.SoKhop.Utils.Pref;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by GMAN on 9/26/2016.
 */

public class SplashActivity extends Activity {

    private ImageView ic_loading;
    private Handler handler = new Handler();
    private Runnable runnable;
    private SharedPreferences mSharedPreferences;
    private Animation animRotate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mSharedPreferences = getSharedPreferences(Pref.MY_PREFERENCES,0);

//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        animRotate = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.rotate_anim);

        ic_loading = (ImageView)findViewById(R.id.ic_loading);
        ic_loading.startAnimation(animRotate);
        runnable = new Runnable() {
            @Override
            public void run() {
                if(!isFinishing()){
                    if(mSharedPreferences.getString(Pref.PREF_KEY_TOKEN,null)==null||mSharedPreferences.getString(Pref.PREF_KEY_TOKEN,null).length()==0){
                        Intent i = new Intent(SplashActivity.this,LoginActivity.class);
                        startActivity(i);
                        finish();
                    }else {
                        HttpCheckToken api = new HttpCheckToken(SplashActivity.this);
                        api.request(mSharedPreferences.getString(Pref.PREF_KEY_TOKEN,null),new API.APIDelegate() {
                            @Override
                            public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                                if (httpResponseCode == HttpResponseCode.SUCCESS) {
                                    Intent i = new Intent(SplashActivity.this,MainActivity.class);
                                    startActivity(i);
                                    finish();
                                }else {
                                    Intent i = new Intent(SplashActivity.this,LoginActivity.class);
                                    startActivity(i);
                                    finish();
                                }
                            }
                        });
                    }
                }

                FacebookSdk.sdkInitialize(getApplicationContext());
                AppEventsLogger.activateApp(SplashActivity.this);
                // Initialize the SDK before executing any other operations,
                // especially, if you're using Facebook UI elements.
                new AccessTokenTracker() {
                    @Override
                    protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                        AccessToken.setCurrentAccessToken(currentAccessToken);
                    }
                };

                AccessToken accessToken = AccessToken.getCurrentAccessToken();
                // Add code to print out the key hash
                try {
                    PackageInfo info = getPackageManager().getPackageInfo(
                            "com.example.gman.myapplication",
                            PackageManager.GET_SIGNATURES);
                    for (Signature signature : info.signatures) {
                        MessageDigest md = MessageDigest.getInstance("SHA");
                        md.update(signature.toByteArray());
                    }
                } catch (PackageManager.NameNotFoundException e) {

                } catch (NoSuchAlgorithmException e) {

                }
            }
        };

        handler.postDelayed(runnable,3000);
    }
}
