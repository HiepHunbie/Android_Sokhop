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
 * Created by MSI on 10/21/2016.
 */

public class HttpUpdateInfoUser {
    public static int updateEducation(String token, String edu_id,
                                              String start_time,String end_time,String degree,String img_url,String video_url,String description,String school_name,String major){
        int responesss = 0;
        try {
            URL url = new URL(APIConstants.UPDATE_EDUCATION_FIX_URL+token+"&edu_id="+edu_id);
            HttpClient client = new DefaultHttpClient();
            HttpPut put= new HttpPut(String.valueOf(url));
            List<NameValuePair> pairs = new ArrayList<NameValuePair>();
            pairs.add(new BasicNameValuePair("start_time", start_time));
            pairs.add(new BasicNameValuePair("end_time", end_time));
            pairs.add(new BasicNameValuePair("degree", degree));
            pairs.add(new BasicNameValuePair("img_url", img_url));
            pairs.add(new BasicNameValuePair("video_url", video_url));
            pairs.add(new BasicNameValuePair("description", description));
            pairs.add(new BasicNameValuePair("school_name", school_name));
            pairs.add(new BasicNameValuePair("major", major));
            put.setEntity(new UrlEncodedFormEntity(pairs, HTTP.UTF_8));
            HttpResponse response = client.execute(put);
            if(response.getStatusLine().getStatusCode() == 200 ){
//                mainActivity.getRecruitment(token,17);
            }
            responesss = response.getStatusLine().getStatusCode();
            Log.d("HttpResponse","ss"+response.getStatusLine().getStatusCode());
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

    public static int updateExp(String token, String exp_id,
                                String company_name,String start_time,String end_time,String position_name,String skills
            ,String img_url,String video_url,String description,String year_of_working,String company_id){
        int responesss = 0;
        try {
            URL url = new URL(APIConstants.UPDATE_EXP_FIX_URL+token+"&exp_id="+exp_id);
            HttpClient client = new DefaultHttpClient();
            HttpPut put= new HttpPut(String.valueOf(url));
            List<NameValuePair> pairs = new ArrayList<NameValuePair>();
            pairs.add(new BasicNameValuePair("company_name", company_name));
            pairs.add(new BasicNameValuePair("start_time", start_time));
            pairs.add(new BasicNameValuePair("end_time", end_time));
            pairs.add(new BasicNameValuePair("position_name", position_name));
            pairs.add(new BasicNameValuePair("skills", skills));
            pairs.add(new BasicNameValuePair("img_url", img_url));
            pairs.add(new BasicNameValuePair("video_url", video_url));
            pairs.add(new BasicNameValuePair("description", description));
            pairs.add(new BasicNameValuePair("year_of_working", year_of_working));
            pairs.add(new BasicNameValuePair("company_id", company_id));
            put.setEntity(new UrlEncodedFormEntity(pairs, HTTP.UTF_8));
            HttpResponse response = client.execute(put);
            if(response.getStatusLine().getStatusCode() == 200 ){
//                mainActivity.getRecruitment(token,17);
            }
            responesss = response.getStatusLine().getStatusCode();
            Log.d("HttpResponse","ss"+response.getStatusLine().getStatusCode());
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

    public static int updateExpectJob(String token, String expect_id,
                                      String salary_basic,String bonus,String allowance,String objective,String description
            ,String position,String location,String position_ids,String location_ids,String range_salary_id
            ,String range_allowance_id,String range_bonus_id,String time_type){
        int responesss = 0;
        try {
            URL url = new URL(APIConstants.UPDATE_EXPECT_JOB_FIX_URL+token+"&expect_id="+expect_id);
            HttpClient client = new DefaultHttpClient();
            HttpPut put= new HttpPut(String.valueOf(url));
            List<NameValuePair> pairs = new ArrayList<NameValuePair>();
            pairs.add(new BasicNameValuePair("salary_basic", salary_basic));
            pairs.add(new BasicNameValuePair("bonus", bonus));
            pairs.add(new BasicNameValuePair("allowance", allowance));
            pairs.add(new BasicNameValuePair("objective", objective));
            pairs.add(new BasicNameValuePair("description", description));
            pairs.add(new BasicNameValuePair("position", position));
            pairs.add(new BasicNameValuePair("location", location));
            pairs.add(new BasicNameValuePair("position_ids", position_ids));
            pairs.add(new BasicNameValuePair("location_ids", location_ids));
            pairs.add(new BasicNameValuePair("range_salary_id", range_salary_id));
            pairs.add(new BasicNameValuePair("range_allowance_id", range_allowance_id));
            pairs.add(new BasicNameValuePair("range_bonus_id", range_bonus_id));
            pairs.add(new BasicNameValuePair("time_type", time_type));
            put.setEntity(new UrlEncodedFormEntity(pairs, HTTP.UTF_8));
            HttpResponse response = client.execute(put);
            if(response.getStatusLine().getStatusCode() == 200 ){
//                mainActivity.getRecruitment(token,17);
            }
            responesss = response.getStatusLine().getStatusCode();
            Log.d("HttpResponse","ss"+response.getStatusLine().getStatusCode());
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


    public static int updateEmail(String token, String email){
        int responesss = 0;
        try {
            URL url = new URL(APIConstants.UPDATE_EMAIL_URL+token);
            HttpClient client = new DefaultHttpClient();
            HttpPut put= new HttpPut(String.valueOf(url));
            List<NameValuePair> pairs = new ArrayList<NameValuePair>();
            pairs.add(new BasicNameValuePair("email", email));
            put.setEntity(new UrlEncodedFormEntity(pairs, HTTP.UTF_8));
            HttpResponse response = client.execute(put);
            if(response.getStatusLine().getStatusCode() == 200 ){
//                mainActivity.getRecruitment(token,17);
            }
            responesss = response.getStatusLine().getStatusCode();
            Log.d("HttpResponse","ss"+response.getStatusLine().getStatusCode());
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
