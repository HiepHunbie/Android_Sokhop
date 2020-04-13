package com.example.ev.SoKhop.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;

import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.net.Socket;


public class CommonData {
	public static Context applicationContext;
	private static Boolean isInitialized = false;
    private static volatile CommonData instance;
    public DisplayImageOptions circleDisplayImageOptions;
    public DisplayImageOptions normalDisplayImageOptions;
    public DisplayImageOptions avatarDisplayImageOptions;
    public DisplayImageOptions displayOptions;
    public int headerAccountHeight = 0;
    public int fullHeaderAccountHeight = 0;
    public Socket socket;
	private Bitmap mCurrentFilteredBitmap;
	public int currentFilterIndex;
	public String currentBitmapUri;
    public String access_token;
    public int user_id = -1;
	public SparseArray<Boolean> listIsFollowed = new SparseArray<>();
	public String[] genderOptions;
	public String currentLanguageCode = "en";
	public String currentDescriptionPostFacebook;
	public String publishClothId = null;


    public String currentDescriptionPostFacebookLook;
    public String currentBitmapUriLook;




	public static CommonData getInstance() {
		if (instance == null) {
			synchronized (CommonData.class) {
				if (instance == null) {
					instance = new CommonData();
				}
			}
		}
		return instance;
	}

	public Bitmap getCurrentFilteredBitmap() {
		return mCurrentFilteredBitmap;
	}

	public void setCurrentFilteredBitmap(Bitmap currentFilteredBitmap) {
		if (mCurrentFilteredBitmap != null) {
			mCurrentFilteredBitmap.recycle();
			mCurrentFilteredBitmap = null;
		}
		this.mCurrentFilteredBitmap = currentFilteredBitmap;
	}
}
