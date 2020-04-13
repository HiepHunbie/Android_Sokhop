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
import com.example.ev.SoKhop.Fragment.EducationFragment;
import com.example.ev.SoKhop.R;
import com.example.ev.SoKhop.Utils.Pref;

import java.util.ArrayList;

/**
 * Created by GMAN on 9/29/2016.
 */

public class ListEducationAdapter extends BaseAdapter {

    private MainActivity activity;
    private static LayoutInflater inflater=null;
    private ArrayList<String> from;
    private ArrayList<String> to;
    private ArrayList<String> school;
    private ArrayList<String> image;
    private ArrayList<String> detail;
    private ArrayList<String> classification;
    private ArrayList<String> edu_id;
    private boolean isShowingDetail = false;

    public ListEducationAdapter(MainActivity a, ArrayList<String> from, ArrayList<String> to, ArrayList<String> school
            , ArrayList<String> imageid, ArrayList<String> detail, ArrayList<String> classification, ArrayList<String> edu_id) {
        activity = a;
        this.from = from;
        this.to = to;
        this.school = school;
        this.classification = classification;
        this.detail = detail;
        this.image = imageid;
        this.edu_id = edu_id;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return from.size();
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
            vi = inflater.inflate(R.layout.item_list_education, null);

        TextView txtFrom = (TextView)vi.findViewById(R.id.txtFrom);
        TextView txtTo = (TextView)vi.findViewById(R.id.txtTo);
        TextView txtSchool = (TextView)vi.findViewById(R.id.txtSchool);
        TextView txtclassification = (TextView)vi.findViewById(R.id.txtclassification);
        TextView txtInfoDetail = (TextView)vi.findViewById(R.id.txtInfoDetail);
        final TextView txtShow = (TextView)vi.findViewById(R.id.txtShow);
        ImageView imgInfo=(ImageView)vi.findViewById(R.id.imgInfo);
        TextView imgEdit=(TextView)vi.findViewById(R.id.imgEdit);
        final LinearLayout layoutImgDetail = (LinearLayout)vi.findViewById(R.id.layoutImageDetail);
        final LinearLayout layoutTxtDetail = (LinearLayout)vi.findViewById(R.id.layoutTextDetail);

        txtShow.setVisibility(View.GONE);
        txtShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isShowingDetail){
                    layoutImgDetail.setVisibility(View.VISIBLE);
                    layoutTxtDetail.setVisibility(View.VISIBLE);
                    isShowingDetail = true;
                    txtShow.setText(R.string.hideDetail);
                    notifyDataSetChanged();
                }else {
                    layoutImgDetail.setVisibility(View.GONE);
                    layoutTxtDetail.setVisibility(View.GONE);
                    isShowingDetail = false;
                    txtShow.setText(R.string.show_detail);
                    notifyDataSetChanged();
                }
            }
        });

        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EducationFragment.ShowDialogEducation(activity, activity.getString(R.string.fix_education),position);
            }
        });
        // Setting all values in listview
        txtFrom.setText(activity.getString(R.string.from)+" "+from.get(position).substring(0,10));
        if(to.get(position).length()==0){
            txtTo.setText(activity.getString(R.string.to_now));
        }else {
            txtTo.setText(activity.getString(R.string.to)+" "+to.get(position).substring(0,10));
        }
        txtSchool.setText(school.get(position));
//        if(classification.get(position)==1){
//            txtclassification.setText(R.string.gioi);
//        }else if(classification.get(position)==2){
//            txtclassification.setText(R.string.kha);
//        }else if(classification.get(position)==3){
//            txtclassification.setText(R.string.trungbinh);
//        }
        txtclassification.setText(activity.getString(R.string.xeploai)+" "+classification.get(position)+"");
        if(detail.get(position).length()==0){
            txtInfoDetail.setText("...");
        }else {
            txtInfoDetail.setText(detail.get(position));
        }
//        txtInfoDetail.setText(detail.get(position));
        if(image.get(position).length()<5){
            imgInfo.setImageResource(R.drawable.bang_cap);
        }else {
            Glide.with(activity).load(image.get(position)).into(imgInfo);
        }

        if(activity.p.getString(Pref.BOOLEAN_IS_ME,null)!=null){
            if(activity.p.getString(Pref.BOOLEAN_IS_ME,null).equals("1")){
                imgEdit.setVisibility(View.GONE);
            }else {
                imgEdit.setVisibility(View.VISIBLE);
            }
        }else {
            imgEdit.setVisibility(View.VISIBLE);
        }
        return vi;
    }
}
