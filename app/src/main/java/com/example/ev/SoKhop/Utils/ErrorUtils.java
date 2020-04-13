package com.example.ev.SoKhop.Utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;


import org.json.JSONException;
import org.json.JSONObject;

public class ErrorUtils {
	// TODO:  ResponseCode: 401 = errorCode
	//	{
	//		  "error_code": 2,
	//		  "message": "Access denied"
	//	}
	public static final int HTTP_ERROR_CODE_ACCESS_DENIED = 401;
	public static final int ERROR_CODE_ACCESS_DENIED = 2;

	public static final int ERROR_NONE = 0;
	public static final int ERROR_ACCESS_DENIED = 1;
	public static final int ERROR_JSON = 2;
	public static final int ERROR_NULL = 3;

	public static int handleErrorCode(int errorCode, String rawResponse) {
		if (TextUtils.isEmpty(rawResponse)) return ERROR_NULL;
		Context context = CommonData.getInstance().applicationContext;
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(rawResponse);
			Log.d("ErrorUtils", "rawResponse: " + rawResponse);
			if (jsonObject.has("error_code")) {
				int error_code = jsonObject.getInt("error_code");
				if (context != null && (error_code == ERROR_CODE_ACCESS_DENIED) && (jsonObject != null)) {
//					Log4jHelper.getLogger("ErrorUtils").trace("ERROR_CODE_ACCESS_DENIED");
//					BaseActivity.closeAllActivities();
//					Common.logout(context);
					return ERROR_ACCESS_DENIED;
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return ERROR_JSON;
		}
		return ERROR_NONE;
	}
}
