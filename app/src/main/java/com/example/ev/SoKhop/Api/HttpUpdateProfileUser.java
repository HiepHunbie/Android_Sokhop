package com.example.ev.SoKhop.Api;

import android.util.Log;
import android.view.View;

import com.example.ev.SoKhop.Activity.MainActivity;
import com.example.ev.SoKhop.Dialog.DialogCall;
import com.example.ev.SoKhop.R;

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
 * Created by MSI on 10/10/2016.
 */

public class HttpUpdateProfileUser {

    public static void updateProfileUser(String first_name, String last_name, String status, String token, String email, String gender, String date_of_birth, String phone, String address
            , String address_temp, String height, String weight, String measurements, String hobbies
            , String skills, String speaks, String soft_skills, String speaks_url, String sings
            , String sings_url, String special_skills, String website, String marriage_status
            , String description, String image_url, String video_url
            , String avatar_url, String cover_url, String appearance, MainActivity mainActivity,String user_id,String middle_name){
        UrlEncodedFormEntity form;
        try {
            URL url = new URL(APIConstants.UPDATE_PROFILE_USER_URL+token);
            HttpClient client = new DefaultHttpClient();

            HttpPut put= new HttpPut(String.valueOf(url));
            List<NameValuePair> pairs = new ArrayList<NameValuePair>();
            pairs.add(new BasicNameValuePair("email", email));
            if(first_name!=null){
                pairs.add(new BasicNameValuePair("first_name", first_name));
            }
            if(middle_name!=null){
                pairs.add(new BasicNameValuePair("middle_name", middle_name));
            }
            if(last_name!=null){
                pairs.add(new BasicNameValuePair("last_name", last_name));
            }
            if(status!=null){
                pairs.add(new BasicNameValuePair("status", status));
            }
            if(gender!=null){
                pairs.add(new BasicNameValuePair("gender", gender));
            }
            if(date_of_birth!=null){
                pairs.add(new BasicNameValuePair("date_of_birth",date_of_birth ));
            }
            if(phone!=null){
                pairs.add(new BasicNameValuePair("phone",phone ));
            }
            if(address!=null){
                pairs.add(new BasicNameValuePair("address", address));
            }
            if(address_temp!=null){
                pairs.add(new BasicNameValuePair("address_temp",address_temp ));
            }
            if(height!=null){
                pairs.add(new BasicNameValuePair("height", height));
            }
            if(weight!=null){
                pairs.add(new BasicNameValuePair("weight",weight ));
            }
            if(measurements!=null){
                pairs.add(new BasicNameValuePair("measurements", measurements));
            }
            if(hobbies!=null){
                pairs.add(new BasicNameValuePair("hobbies",hobbies ));
            }
            if(skills!=null){
                pairs.add(new BasicNameValuePair("skills", skills));
            }
            if(speaks!=null){
                pairs.add(new BasicNameValuePair("speaks", speaks));
            }
            if(soft_skills!=null){
                pairs.add(new BasicNameValuePair("soft_skills", soft_skills));
            }
            if(speaks_url!=null){
                pairs.add(new BasicNameValuePair("speaks_url", speaks_url));
            }
            if(sings!=null){
                pairs.add(new BasicNameValuePair("sings", sings));
            }
            if(sings_url!=null){
                pairs.add(new BasicNameValuePair("sings_url", sings_url));
            }
            if(special_skills!=null){
                pairs.add(new BasicNameValuePair("special_skills",special_skills ));
            }
            if(website!=null){
                pairs.add(new BasicNameValuePair("website",website ));
            }
            if(marriage_status!=null){
                pairs.add(new BasicNameValuePair("marriage_status",marriage_status ));
            }
            if(description!=null){
                pairs.add(new BasicNameValuePair("description",description ));
            }
            if(image_url!=null){
                pairs.add(new BasicNameValuePair("image_url", image_url));
            }
            if(video_url!=null){
                pairs.add(new BasicNameValuePair("video_url",video_url ));
            }
            if(avatar_url!=null){
                pairs.add(new BasicNameValuePair("avatar_url", avatar_url));
            }
            if(cover_url!=null){
                pairs.add(new BasicNameValuePair("cover_url",cover_url ));
            }
            if(appearance!=null){
                pairs.add(new BasicNameValuePair("appearance", appearance));
            }
            put.setEntity(new UrlEncodedFormEntity(pairs, HTTP.UTF_8));
            HttpResponse response = client.execute(put);
            if(response.getStatusLine().getStatusCode() == 200 ){
                mainActivity.getUserProfile(token,user_id,7,true);
            }else {
                DialogCall.showResponse(mainActivity, mainActivity.getString(R.string.edit_profile_error), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int id = v.getId();
                        if (id == R.id.btnOk) {
                        }
                    }
                });
            }
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
    }
}
