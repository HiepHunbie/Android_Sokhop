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
 * Created by MSI on 10/11/2016.
 */

public class HttpUpdateProfileCompany {
    public static void updateProfileCompany(String token,String id, String name, String address, String size, String employee_count
            , String website, String phone, String time_working, String type
            , String email, String introduce, String country, String logo_url, String img_url
            , String representation_name, String representation_email, String representation_phone, String representation_img
            , String clothes, String tax_code, String allowance, String rules, String register_img
            , String contact_name, String contact_phone, String contact_email
            , String contact_address, String contact_position, String image_url,String video_url,
                                            String avatar_url,String cover_url,String status,MainActivity mainActivity, String user_id){
        try {
            URL url = new URL(APIConstants.UPDATE_PROFILE_COMPANY_URL+token);
            HttpClient client = new DefaultHttpClient();
            HttpPut put= new HttpPut(String.valueOf(url));
            List<NameValuePair> pairs = new ArrayList<NameValuePair>();
            pairs.add(new BasicNameValuePair("id", id));
            pairs.add(new BasicNameValuePair("name", name));
            if(address!=null){
                pairs.add(new BasicNameValuePair("address", address));
            }
            if(size!=null){
                pairs.add(new BasicNameValuePair("size", size));
            }
            if(employee_count!=null){
                pairs.add(new BasicNameValuePair("employee_count", employee_count));
            }
            if(website!=null){
                pairs.add(new BasicNameValuePair("website", website));
            }
            if(phone!=null){
                pairs.add(new BasicNameValuePair("phone", phone));
            }
            if(time_working!=null){
                pairs.add(new BasicNameValuePair("time_working",time_working ));
            }
            if(type!=null){
                pairs.add(new BasicNameValuePair("type",type ));
            }
            if(email!=null){
                pairs.add(new BasicNameValuePair("email", email));
            }
            if(introduce!=null){
                pairs.add(new BasicNameValuePair("introduce",introduce ));
            }
            if(country!=null){
                pairs.add(new BasicNameValuePair("country", country));
            }
            if(logo_url!=null){
                pairs.add(new BasicNameValuePair("logo_url",logo_url ));
            }
            if(img_url!=null){
                pairs.add(new BasicNameValuePair("img_url", img_url));
            }
            if(representation_name!=null){
                pairs.add(new BasicNameValuePair("representation_name",representation_name ));
            }
            if(representation_email!=null){
                pairs.add(new BasicNameValuePair("representation_email", representation_email));
            }
            if(representation_phone!=null){
                pairs.add(new BasicNameValuePair("representation_phone", representation_phone));
            }
            if(representation_img!=null){
                pairs.add(new BasicNameValuePair("representation_img", representation_img));
            }
            if(clothes!=null){
                pairs.add(new BasicNameValuePair("clothes", clothes));
            }
            if(tax_code!=null){
                pairs.add(new BasicNameValuePair("tax_code", tax_code));
            }
            if(allowance!=null){
                pairs.add(new BasicNameValuePair("allowance", allowance));
            }
            if(rules!=null){
                pairs.add(new BasicNameValuePair("rules",rules ));
            }
            if(register_img!=null){
                pairs.add(new BasicNameValuePair("register_img",register_img ));
            }
            if(contact_name!=null){
                pairs.add(new BasicNameValuePair("contact_name",contact_name ));
            }
            if(contact_phone!=null){
                pairs.add(new BasicNameValuePair("contact_phone",contact_phone ));
            }
            if(contact_email!=null){
                pairs.add(new BasicNameValuePair("contact_email", contact_email));
            }
            if(contact_address!=null){
                pairs.add(new BasicNameValuePair("contact_address",contact_address ));
            }
            if(contact_position!=null){
                pairs.add(new BasicNameValuePair("contact_position", contact_position));
            }
            if(image_url!=null){
                pairs.add(new BasicNameValuePair("image_url",image_url ));
            }
            if(video_url!=null){
                pairs.add(new BasicNameValuePair("video_url", video_url));
            }
            if(avatar_url!=null){
                pairs.add(new BasicNameValuePair("avatar_url", avatar_url));
            }
            if(cover_url!=null){
                pairs.add(new BasicNameValuePair("cover_url", cover_url));
            }
            if(status!=null){
                pairs.add(new BasicNameValuePair("status", status));
            }
            put.setEntity(new UrlEncodedFormEntity(pairs, HTTP.UTF_8));
            HttpResponse response = client.execute(put);
            if(response.getStatusLine().getStatusCode() == 200 ){
                mainActivity.getCompanyProfile(token,user_id,12,true);
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
