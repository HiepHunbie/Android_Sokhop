package com.example.ev.SoKhop.Base;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.example.ev.SoKhop.Activity.MainActivity;
import com.example.ev.SoKhop.Utils.Pref;
import com.example.ev.SoKhop.Utils.ZImageLoader;

/**
 * Created by GMAN on 9/26/2016.
 */

public abstract class BaseFragment extends android.support.v4.app.Fragment{
    protected static MainActivity mActivity;
    public Pref p;

    public BaseFragment(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivity = (MainActivity) getActivity();
        mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        p = new Pref(mActivity);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }
    protected void hideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    public void asyncLoadImage(String url, ImageView iv) {
//        String realurl = PhotoUtils.getPhotoUrl(url);
//        if (!TextUtils.isEmpty(realurl)) {
//            ZImageLoader.asyncLoadImage(realurl, iv);
//        }

        ZImageLoader.asyncLoadImage(url, iv);
    }

}
