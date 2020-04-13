package com.example.ev.SoKhop.Utils;

import android.content.Context;

public class Common {

	public final static String BA_USR = null;
	public final static String BA_PWD = null;
	public final static String TAG_GET_CLOTH_CONFIG = "TAG_GET_CLOTH_CONFIG";
	public final static String TAG_UPDATE_USER_GENDER = "TAG_UPDATE_USER_GENDER";

	public static String[] getDefaultAPIParams(Context context) {
		final Pref p = new Pref(context);
		return new String[] { "access_token", p.getString(Pref.PREF_KEY_TOKEN, "") };
	}

}
