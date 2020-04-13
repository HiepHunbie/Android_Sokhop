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
import com.example.ev.SoKhop.Api.HttpUpdateSaveOtherUser;
import com.example.ev.SoKhop.R;
import com.example.ev.SoKhop.Utils.Convert_Time_To_Now;
import com.example.ev.SoKhop.Utils.Pref;

import java.util.ArrayList;

/**
 * Created by MSI on 10/28/2016.
 */

public class SearchUserAdapter extends BaseAdapter {

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
    private ArrayList<String> recruiter_id;

    public SearchUserAdapter(MainActivity a, ArrayList<String> logo, ArrayList<String> title, ArrayList<String> company, ArrayList<String> time,
                                 ArrayList<String> quantam, ArrayList<String> location, ArrayList<String> salary, ArrayList<String> time_type,ArrayList<String> recruiter_id) {
        activity = a;
        this.logo = logo;
        this.title = title;
        this.company = company;
        this.time = time;
        this.quantam = quantam;
        this.location = location;
        this.salary = salary;
        this.time_type = time_type;
        this.recruiter_id = recruiter_id;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return company.size();
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
            vi = inflater.inflate(R.layout.item_search_user, null);

        ImageView imgLibrary = (ImageView)vi.findViewById(R.id.imgLibrary);
        TextView txtTitle = (TextView)vi.findViewById(R.id.txtTitle);
        TextView txtCompany = (TextView)vi.findViewById(R.id.txtCompany);
        com.example.ev.SoKhop.Utils.AutoResizeTextView txtTime = (com.example.ev.SoKhop.Utils.AutoResizeTextView)vi.findViewById(R.id.txtTime);
        ImageView imgQuantam = (ImageView)vi.findViewById(R.id.imgQuantam);
        TextView txtLocation = (TextView)vi.findViewById(R.id.txtLocation);
        TextView txtSalary = (TextView)vi.findViewById(R.id.txtSalary);
        TextView txtTimeType = (TextView)vi.findViewById(R.id.txtTimeType);
        TextView txtDanhGia = (TextView)vi.findViewById(R.id.txtDanhGia);
        LinearLayout layoutImage = (LinearLayout)vi.findViewById(R.id.layoutImage);

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

        if(quantam.get(position).equals("2")){
            imgQuantam.setImageResource(R.drawable.ic_quantam_heart_1);
        }else {
            imgQuantam.setImageResource(R.drawable.ic_quantam_heart_2);
        }
        txtLocation.setText(location.get(position));
        txtSalary.setText(salary.get(position));
        txtTimeType.setText(time_type.get(position));

        vi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.p.putString(Pref.BOOLEAN_IS_ME,"1");
                activity.getUserProfile(activity.p.getString(Pref.PREF_KEY_TOKEN,null),recruiter_id.get(position),0,false);
                activity.getImageAlbums(activity.p.getString(Pref.PREF_KEY_TOKEN,null),recruiter_id.get(position),999);
                activity.getEducationInfo(activity.p.getString(Pref.PREF_KEY_TOKEN,null),recruiter_id.get(position),999);
                activity.getExpericesInfo(activity.p.getString(Pref.PREF_KEY_TOKEN,null),recruiter_id.get(position),999);
                activity.getPositionTimeline(activity.p.getString(Pref.PREF_KEY_TOKEN,null),recruiter_id.get(position));
                activity.getExpectJob(activity.p.getString(Pref.PREF_KEY_TOKEN,null),recruiter_id.get(position),999);
            }
        });

        imgQuantam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quantam.get(position).equals("1")){
                    int response = HttpUpdateSaveOtherUser.updateSave(activity.p.getString(Pref.PREF_KEY_TOKEN,null),recruiter_id.get(position));
                    if(response == 200){
                        activity.selectItem(24);
//                        DialogCall.showResponse(activity, activity.getString(R.string.unsaveuser_success), new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                int id = v.getId();
//                                if (id == R.id.btnOk) {
//                                    activity.selectItem(24);
//                                }
//                            }
//                        });
                    }else {
//                        DialogCall.showResponse(activity, activity.getString(R.string.unsaveuser_error), new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                int id = v.getId();
//                                if (id == R.id.btnOk) {
//                                }
//                            }
//                        });
                    }
                }else {
                    int response = HttpUpdateSaveOtherUser.updateSave(activity.p.getString(Pref.PREF_KEY_TOKEN,null),recruiter_id.get(position));
                    if(response == 200){
                        activity.selectItem(24);
//                        DialogCall.showResponse(activity, activity.getString(R.string.save_user_success), new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                int id = v.getId();
//                                if (id == R.id.btnOk) {
//                                    activity.selectItem(24);
//                                }
//                            }
//                        });
                    }else {
//                        DialogCall.showResponse(activity, activity.getString(R.string.save_user_error), new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                int id = v.getId();
//                                if (id == R.id.btnOk) {
//                                }
//                            }
//                        });
                    }
                }
            }
        });
        return vi;
    }
}
