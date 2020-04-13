package com.example.ev.SoKhop.Api.core;

import android.app.Activity;
import android.app.Dialog;
import android.util.Log;

import com.example.ev.SoKhop.Utils.NetworkUtil;
import com.example.ev.SoKhop.Api.APIConstants;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Credentials;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class API {

    public interface APIDelegate {
        void onRequestFinished(final HttpResponseCode httpResponseCode, JSONObject jsonResponse);
    }
    private MultipartEntityBuilder mBuilder;
    public final static String TAG = API.class.getSimpleName();
    private final static String PREF_COOKIES = "pref_cookies";
    public Activity mActivity;
    private Dialog mDialog = null;
    ArrayList<APIParam> mParams;
    ArrayList<APIHeader> mHeaders;

    public enum APIMethods {
        GET, POST, UPLOAD_DATA, DELETE,DELETE_BODY
    }

    public API() {
    }

    public API(Activity act) {
        mActivity = act;
        mDialog = null;
        mParams = new ArrayList<>();
        mHeaders = new ArrayList<>();
    }

    public Activity getActivity() {
        return mActivity;
    }

    protected APIMethods getMethod() {
        return APIMethods.POST;
    }

    protected String getUrl() {
        return "";
    }

    public ArrayList<APIParam> createParams(/*String apiVersion, String email, String password*/) {
        ArrayList<APIParam> params = new ArrayList<APIParam>();
//        params.add(new APIParam("apiVersion", apiVersion));
//        params.add(new APIParam("email", email));
//        params.add(new APIParam("password", password));
        return params;
    }

    protected void addHeader(String key, String value) {
        mHeaders.add(new APIHeader(key, value));
    }

    protected void addParam(String key, String value) {
        addParam(new APIParam(key, value, null));
    }

    protected void addParam(String key, String value, String contentType) {
        addParam(new APIParam(key, value, contentType));
    }

    protected void addParam(String key, String value, String contentType, CountingFileRequestBody.ProgressListener listener) {
        addParam(new APIParam(key, value, contentType, listener));
    }
    protected void addParam(String key, String filename, String contentType,String value) {
        addParam(new APIParam(key, filename, contentType,value));
    }

    protected void addParam(APIParam param) {
        mParams.add(param);
    }

    protected void request(final APIDelegate delegate, boolean showDialog) {
        if (!NetworkUtil.isNetworkConnect(mActivity)) {
            Log.d(TAG, "hasn't network!");
            delegate.onRequestFinished(HttpResponseCode.NO_INTERNET_ACCESS, null);
            return;
        }
//        if (showDialog)
//            mDialog = showProgress("");

        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(APIConstants.CONECTION_TIMEOUT, TimeUnit.MILLISECONDS);
        client.setWriteTimeout(APIConstants.WRITE_TIMEOUT, TimeUnit.MILLISECONDS);
        client.setReadTimeout(APIConstants.READ_TIMEOUT, TimeUnit.MILLISECONDS);

//        client.setHostnameVerifier(new HostnameVerifier() {
//            @Override
//            public boolean verify(String s, SSLSession sslSession) {
//                //return true;
//                HostnameVerifier hv =
//                        HttpsURLConnection.getDefaultHostnameVerifier();
//                return hv.verify("ipage.com", sslSession);
//            }
//        });


//        client.setAuthenticator(new Authenticator() {
//                                    @Override
//                                    public Request authenticate(Proxy proxy, Response response) {
//                                        Log.d("Authenticating for response: " + response);
//                                        Log.d("Challenges: " + response.challenges());
//                                        String credential = Credentials.basic("kakehashitest", "kakehashitest");
//                                        return response.request().newBuilder()
//                                                .header("Authorization", credential)
//                                                .build();
//                                    }
//
//                                    @Override
//                                    public Request authenticateProxy(Proxy proxy, Response response) {
//                                        return null; // Null indicates no attempt to authenticate.
//                                    }
//                                });

        //add param
        Request.Builder requestBuild = null;
        if (getMethod() == APIMethods.POST) {
            FormEncodingBuilder builder = new FormEncodingBuilder();
            for (APIParam param : mParams) {
                builder.add(param.key, param == null ? null : param.stringValue);
                Log.d(TAG, param.toString());
            }
            RequestBody formBody = builder.build();
            requestBuild = new Request.Builder()
                    .url(getUrl())
                    .post(formBody);

        }
        else if(getMethod() == APIMethods.DELETE){
            String url = getUrl();
//            for (APIParam param : mParams) {
//                url += param.key + "=" + param.stringValue +"&";
//                Log.d(TAG, param.toString());
//            }
            for (int i = 0; i< mParams.size();i++){
                if (i == mParams.size() - 1){
                    url += mParams.get(i).getKey() + "=" + mParams.get(i).getStringValue();
                }else {
                    url += mParams.get(i).getKey() + "=" + mParams.get(i).getStringValue() + "&";
                }
            }

            requestBuild = new Request.Builder()
                    .url(url).delete();
        }
        else if(getMethod() == APIMethods.DELETE_BODY){
            FormEncodingBuilder builder = new FormEncodingBuilder();
            for (APIParam param : mParams) {
                builder.add(param.key, param == null ? null : param.stringValue);
                Log.d(TAG, param.toString());
            }
            RequestBody formBody = builder.build();
            requestBuild = new Request.Builder()
                    .url(getUrl())
                    .delete(formBody);
        }
        else if (getMethod() == APIMethods.UPLOAD_DATA) {
            MultipartBuilder builder = new MultipartBuilder()
                    .type(MultipartBuilder.FORM);
            for (APIParam param : mParams) {
                if (param.contentType == null) {
                    builder.addFormDataPart(param.key, param.stringValue);
                } else {
                    File file = new File(param.stringValue);
                    if(param.fileName == null){
                        if (param.progressListener == null)
                            builder.addFormDataPart(param.key, file.getName(), RequestBody.create(MediaType.parse(param.contentType), file));
                        else {
                            RequestBody requestBody = new CountingFileRequestBody(file, param.contentType, param.progressListener);
                            builder.addFormDataPart(param.key, file.getName(), requestBody);
                        }
                    }else {
                        if (param.progressListener == null)
                            builder.addFormDataPart(param.key, param.fileName, RequestBody.create(MediaType.parse(param.contentType), file));
                        else {
                            RequestBody requestBody = new CountingFileRequestBody(file, param.contentType, param.progressListener);
                            builder.addFormDataPart(param.key, param.fileName, requestBody);
                        }
                    }

                }
            }
            RequestBody formBody = builder.build();
            requestBuild = new Request.Builder()
                    .url(getUrl())
                    .post(formBody);
        }

        else {
            String url = getUrl();
//            for (APIParam param : mParams) {
//                url += param.key + "=" + param.stringValue +"&";
//                Log.d(TAG, param.toString());
//            }
            for (int i = 0; i< mParams.size();i++){
                if (i == mParams.size() - 1){
                    url += mParams.get(i).getKey() + "=" + mParams.get(i).getStringValue();
                }else {
                    url += mParams.get(i).getKey() + "=" + mParams.get(i).getStringValue() + "&";
                }
            }

            requestBuild = new Request.Builder()
                    .url(url);

            Log.d("urlss","ss"+url);
        }

//        add header
        for (APIHeader header : mHeaders) {
            Log.d(TAG, "header: " + header.toString());
            requestBuild.header(header.getKey(), header.getValue());
        }

        //add basic authentication
        requestBuild.header("Authorization", Credentials.basic("kakehashitest", "kakehashitest"));

        Request request = requestBuild.build();
        Log.d(TAG, "Request: " + request.toString());
        Log.d(TAG, "Request Header: " + request.headers());

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                if (mDialog != null ){
                    mDialog.dismiss();
                }
                e.printStackTrace();
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        delegate.onRequestFinished(HttpResponseCode.REQUEST_ERROR, null);
                    }
                });
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                Log.d(TAG, "Response: " + response.toString());
                final String responseBody = response.body().string();
                if (mDialog != null ){
                    mDialog.dismiss();
                }
                if (!response.isSuccessful()) {
                    mActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                final JSONObject obj = new JSONObject(responseBody);
                                delegate.onRequestFinished(HttpResponseCode.RESPONSE_ERROR, obj);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    throw new IOException("Unexpected code " + response);
                }

//                Log.d(TAG, "Cookies in header: " + response.header("Set-Cookie"));
//
//
//                CookieManager cookieManager = CookieManager.getInstance();
//                //cookieManager.setAcceptCookie(true);
//
//                cookieManager.setCookie(APIConstants.BASE_DOMAIN, response.header("Set-Cookie"));
//                CookieSyncManager.getInstance().sync();
//
//                String cookies = CookieManager.getInstance().getCookie(APIConstants.BASE_DOMAIN);
//                Log.d(TAG, "Cookies after request: " + cookies);
//                PersistentCookies.getInstance().setCookie(response.header("Set-Cookie"));

                try {
                    final JSONObject obj = new JSONObject(responseBody);
                    obj.put("cookies", response.header("Set-Cookie"));
                    ((Activity) mActivity).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            delegate.onRequestFinished(HttpResponseCode.SUCCESS, obj);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    protected MultipartEntityBuilder getMultipartEntityBuilder() {
        if(this.mBuilder == null) {
            this.mBuilder = MultipartEntityBuilder.create();
            this.mBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            this.mBuilder.setCharset(Charset.forName("UTF-8"));
        }

        return this.mBuilder;
    }

//    public static void showErrorDialog(final Activity act, final int errCode) {
//        if (act.isFinishing())
//            return;
//
//        final OneButtonDialog oneButtonDialog = new OneButtonDialog(act, HttpResponseCode.getMessage(errCode), new DialogCallback(){
//            @Override
//            public void callback(int index) {
//
//            }
//        });
//        act.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                if (!oneButtonDialog.isShowing())
//                    oneButtonDialog.show();
//            }
//        });
//    }
}