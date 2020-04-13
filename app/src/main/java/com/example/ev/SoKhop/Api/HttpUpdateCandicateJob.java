package com.example.ev.SoKhop.Api;

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
 * Created by MSI on 10/24/2016.
 */

public class HttpUpdateCandicateJob {
    public static int updateApplyJobs(String token, String cv_url, String job_id){
        int responesss = 0;
        try {
            URL url = new URL(APIConstants.UPDATE_APPLY_JOB_URL+token);
            HttpClient client = new DefaultHttpClient();
            HttpPut put= new HttpPut(String.valueOf(url));
            List<NameValuePair> pairs = new ArrayList<NameValuePair>();
            pairs.add(new BasicNameValuePair("cv_url", cv_url));
            pairs.add(new BasicNameValuePair("job_id", job_id));
            put.setEntity(new UrlEncodedFormEntity(pairs, HTTP.UTF_8));
            HttpResponse response = client.execute(put);
            if(response.getStatusLine().getStatusCode() == 200 ){
            }
            responesss = response.getStatusLine().getStatusCode();
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
    public static int updateSaveJobs(String token,String job_id){
        int responesss = 0;
        try {
            URL url = new URL(APIConstants.UPDATE_SAVE_JOB_URL+token+"&job_id="+job_id);
            HttpClient client = new DefaultHttpClient();
            HttpPut put= new HttpPut(String.valueOf(url));
            List<NameValuePair> pairs = new ArrayList<NameValuePair>();
            put.setEntity(new UrlEncodedFormEntity(pairs, HTTP.UTF_8));
            HttpResponse response = client.execute(put);
            if(response.getStatusLine().getStatusCode() == 200 ){
            }
            responesss = response.getStatusLine().getStatusCode();
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
