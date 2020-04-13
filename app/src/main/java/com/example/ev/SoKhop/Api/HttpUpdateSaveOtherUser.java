package com.example.ev.SoKhop.Api;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MSI on 10/31/2016.
 */

public class HttpUpdateSaveOtherUser {
    public static int updateSave(String token, String id) {
        int responesss = 0;
        try {
            URL url = new URL(APIConstants.UPDATE_SAVED_USERS_URL + token);
            HttpClient client = new DefaultHttpClient();
            HttpPut put = new HttpPut(String.valueOf(url));
            List<NameValuePair> pairs = new ArrayList<NameValuePair>();
            pairs.add(new BasicNameValuePair("candidate_id", id));
            put.setEntity(new UrlEncodedFormEntity(pairs, HTTP.UTF_8));
            HttpResponse response = client.execute(put);
            if (response.getStatusLine().getStatusCode() == 200) {
//                mainActivity.getRecruitment(token,17);
            }
            responesss = response.getStatusLine().getStatusCode();
            Log.d("HttpResponse", "ss" + response.getStatusLine().getStatusCode());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responesss;
    }
}
