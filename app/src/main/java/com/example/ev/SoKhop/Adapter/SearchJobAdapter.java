package com.example.ev.SoKhop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ev.SoKhop.Activity.MainActivity;
import com.example.ev.SoKhop.Api.HttpUpdateCandicateJob;
import com.example.ev.SoKhop.Dialog.DialogCall;
import com.example.ev.SoKhop.R;
import com.example.ev.SoKhop.Utils.Convert_Time_To_Now;
import com.example.ev.SoKhop.Utils.Edittext;
import com.example.ev.SoKhop.Utils.Pref;

import java.util.ArrayList;

/**
 * Created by MSI on 10/27/2016.
 */

public class SearchJobAdapter extends BaseAdapter {

    private MainActivity activity;
    private static LayoutInflater inflater=null;
    private ArrayList<String> logo;
    private ArrayList<String> title;
    private ArrayList<String> company;
    private ArrayList<String> time;
    private ArrayList<String> quantam;
    private ArrayList<String> location;
    private ArrayList<String> salary;
    private ArrayList<String> time_type;
    private ArrayList<String> image;
    private ArrayList<String> jobs_id;

    public SearchJobAdapter(MainActivity a, ArrayList<String> logo, ArrayList<String> title, ArrayList<String> company, ArrayList<String> time,
                       ArrayList<String> quantam, ArrayList<String> location, ArrayList<String> salary, ArrayList<String> time_type, ArrayList<String> image,ArrayList<String> jobs_id) {
        activity = a;
        this.logo = logo;
        this.title = title;
        this.company = company;
        this.time = time;
        this.quantam = quantam;
        this.location = location;
        this.salary = salary;
        this.time_type = time_type;
        this.image = image;
        this.jobs_id = jobs_id;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return title.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.item_search_jobs, null);

        ImageView imgLibrary = (ImageView)vi.findViewById(R.id.imgLibrary);
        com.example.ev.SoKhop.Utils.AutoResizeTextView txtTitle = (com.example.ev.SoKhop.Utils.AutoResizeTextView)vi.findViewById(R.id.txtTitle);
        TextView txtCompany = (TextView)vi.findViewById(R.id.txtCompany);
        com.example.ev.SoKhop.Utils.AutoResizeTextView txtTime = (com.example.ev.SoKhop.Utils.AutoResizeTextView)vi.findViewById(R.id.txtTime);
        final ImageView imgQuantam = (ImageView)vi.findViewById(R.id.imgQuantam);
        TextView txtLocation = (TextView)vi.findViewById(R.id.txtLocation);
        TextView txtSalary = (TextView)vi.findViewById(R.id.txtSalary);
        TextView txtTimeType = (TextView)vi.findViewById(R.id.txtTimeType);
        TextView txtDanhGia = (TextView)vi.findViewById(R.id.txtDanhGia);
        LinearLayout layoutImage = (LinearLayout)vi.findViewById(R.id.layoutImage);
        ImageView img1 = (ImageView)vi.findViewById(R.id.img1);
        ImageView img2 = (ImageView)vi.findViewById(R.id.img2);
        ImageView img3 = (ImageView)vi.findViewById(R.id.img3);

        if(logo.get(position).length()>5){
            Glide.with(activity).load(logo.get(position)).into(imgLibrary);
        }else {
            imgLibrary.setImageResource(R.drawable.avatar);
        }
        txtTitle.setText(title.get(position));
        txtCompany.setText(company.get(position));
        if(time.get(position).length()>5){
//            txtTime.setText(time.get(position).substring(0,10));
            txtTime.setText(Convert_Time_To_Now.dataToFeauture(time.get(position)));
        }else {
            txtTime.setText("");
        }

        if(quantam.get(position).equals("1")){
            imgQuantam.setImageResource(R.drawable.ic_quantam_1);
        }else {
            imgQuantam.setImageResource(R.drawable.ic_quantam_2);
        }

        imgQuantam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quantam.get(position).equals("1")){
                    int response = HttpUpdateCandicateJob.updateSaveJobs(activity.p.getString(Pref.PREF_KEY_TOKEN,null),jobs_id.get(position));
                    if(response == 200){
                        DialogCall.showResponse(activity, activity.getString(R.string.unsavejob_success), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int id = v.getId();
                                if (id == R.id.btnOk) {
                                    activity.p.putString(Pref.JOB_TAB,"1");
                                    activity.selectItem(23);
                                }
                            }
                        });
                    }else {
                        DialogCall.showResponse(activity, activity.getString(R.string.unsavejob_error), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int id = v.getId();
                                if (id == R.id.btnOk) {
                                }
                            }
                        });
                    }
                }else {
                    int response = HttpUpdateCandicateJob.updateSaveJobs(activity.p.getString(Pref.PREF_KEY_TOKEN,null),jobs_id.get(position));
                    if(response == 200){
                        DialogCall.showResponse(activity, activity.getString(R.string.savejob_success), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int id = v.getId();
                                if (id == R.id.btnOk) {
                                    activity.p.putString(Pref.JOB_TAB,"1");
                                    activity.selectItem(23);
                                }
                            }
                        });
                    }else {
                        DialogCall.showResponse(activity, activity.getString(R.string.savejob_error), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int id = v.getId();
                                if (id == R.id.btnOk) {
                                }
                            }
                        });
                    }
                }

            }
        });
        txtLocation.setText(location.get(position));
        txtSalary.setText(Edittext.convertTextToCommas(salary.get(position)));
        txtTimeType.setText(time_type.get(position));
        if(image.size()>0){
            if(image.get(0).length()==0){
                layoutImage.setVisibility(View.GONE);
            }else  if(image.size()==1){
                Glide.with(activity).load(image.get(0)).into(img1);
                img2.setVisibility(View.GONE);
                img3.setVisibility(View.GONE);
            }else if(image.size()==2){
                Glide.with(activity).load(image.get(0)).into(img1);
                Glide.with(activity).load(image.get(1)).into(img2);
                img3.setVisibility(View.GONE);
            }else if(image.size()==3){
                Glide.with(activity).load(image.get(0)).into(img1);
                Glide.with(activity).load(image.get(1)).into(img2);
                Glide.with(activity).load(image.get(2)).into(img3);
            }
        }

        return vi;
    }
}
