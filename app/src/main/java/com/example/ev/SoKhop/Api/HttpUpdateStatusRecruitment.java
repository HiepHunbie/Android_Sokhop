package com.example.ev.SoKhop.Api;

import android.util.Log;

import com.example.ev.SoKhop.Activity.MainActivity;

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
 * Created by MSI on 10/17/2016.
 */

public class HttpUpdateStatusRecruitment {
    public static int updateStatusRecruitment(String token, String id, String status,MainActivity mainActivity){
        int responesss = 0;
        try {
            URL url = new URL(APIConstants.UPDATE_RECRUITMENT_STATUS_URL+token);
            HttpClient client = new DefaultHttpClient();
            HttpPut put= new HttpPut(String.valueOf(url));
            List<NameValuePair> pairs = new ArrayList<NameValuePair>();
            pairs.add(new BasicNameValuePair("id", id));
            pairs.add(new BasicNameValuePair("status", status));
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

    public static int deleteRecruitment(String token, String id, String isactive){
        int responesss = 0;
        try {
            URL url = new URL(APIConstants.DELETE_RECRUITMENT_URL+token);
            HttpClient client = new DefaultHttpClient();
            HttpPut put= new HttpPut(String.valueOf(url));
            List<NameValuePair> pairs = new ArrayList<NameValuePair>();
            pairs.add(new BasicNameValuePair("id", id));
            pairs.add(new BasicNameValuePair("isactive", isactive));
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


    public static int updateRecruitment(String token, String id,String position,String title,String company_id,String categories,String quantity,
                                        String level,String time_type,String range_salary_id,String salary,String work_location,
                                        String gender,String description,String range_allowance_id,String allowance,String range_bonus_id,
                                        String bonus,String extra_desc,String expired_time,String languages_profile,String album_image_id,
                                        String album_contract_id,String skills,String year_exps,String exclude_conditions,String require_conditions,
                                        String contact,String status,String job_welfare){
        int responesss = 0;
        try {
            URL url = new URL(APIConstants.UPDATE_RECRUITMENT_URL+token);
            HttpClient client = new DefaultHttpClient();
            HttpPut put= new HttpPut(String.valueOf(url));
            List<NameValuePair> pairs = new ArrayList<NameValuePair>();
            pairs.add(new BasicNameValuePair("id", id));
            pairs.add(new BasicNameValuePair("position", position));
            pairs.add(new BasicNameValuePair("title", title));
            pairs.add(new BasicNameValuePair("company_id", company_id));
            pairs.add(new BasicNameValuePair("categories", categories));
            pairs.add(new BasicNameValuePair("quantity", quantity));
            pairs.add(new BasicNameValuePair("level", level));
            pairs.add(new BasicNameValuePair("time_type", time_type));
            pairs.add(new BasicNameValuePair("range_salary_id", range_salary_id));
            pairs.add(new BasicNameValuePair("salary", salary));
            pairs.add(new BasicNameValuePair("work_location", work_location));
            pairs.add(new BasicNameValuePair("gender", gender));
            pairs.add(new BasicNameValuePair("description", description));
            pairs.add(new BasicNameValuePair("range_allowance_id", range_allowance_id));
            pairs.add(new BasicNameValuePair("allowance", allowance));
            pairs.add(new BasicNameValuePair("range_bonus_id", range_bonus_id));
            pairs.add(new BasicNameValuePair("bonus", bonus));
            pairs.add(new BasicNameValuePair("extra_desc", extra_desc));
            pairs.add(new BasicNameValuePair("expired_time", expired_time));
            pairs.add(new BasicNameValuePair("languages_profile", languages_profile));
            pairs.add(new BasicNameValuePair("album_image_id", album_image_id));
            pairs.add(new BasicNameValuePair("album_contract_id", album_contract_id));
            pairs.add(new BasicNameValuePair("skills", skills));
            pairs.add(new BasicNameValuePair("year_exps", year_exps));
            pairs.add(new BasicNameValuePair("exclude_conditions", exclude_conditions));
            pairs.add(new BasicNameValuePair("require_conditions", require_conditions));
            pairs.add(new BasicNameValuePair("contact", contact));
            pairs.add(new BasicNameValuePair("status", status));
            pairs.add(new BasicNameValuePair("welfare", job_welfare));
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
