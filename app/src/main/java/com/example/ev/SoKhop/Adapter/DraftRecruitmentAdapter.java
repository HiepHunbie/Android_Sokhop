package com.example.ev.SoKhop.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ev.SoKhop.R;
import com.example.ev.SoKhop.Utils.DateUtils;

import java.util.ArrayList;

/**
 * Created by MSI on 10/17/2016.
 */

public class DraftRecruitmentAdapter extends BaseAdapter {

    private Activity activity;
    private static LayoutInflater inflater=null;
    private ArrayList<String> pos;
    private ArrayList<String> places;
    private ArrayList<String> typeWork;
    private ArrayList<String> timeEdit;

    public DraftRecruitmentAdapter(Activity a, ArrayList<String> pos, ArrayList<String> places, ArrayList<String> typeWork, ArrayList<String> timeEdit) {
        activity = a;
        this.pos = pos;
        this.places = places;
        this.typeWork = typeWork;
        this.timeEdit = timeEdit;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return pos.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.item_draft_recruitment, null);

        TextView txtJobs = (TextView)vi.findViewById(R.id.txtJobs);
        TextView txtPlaces = (TextView)vi.findViewById(R.id.txtPlaces);
        TextView txtTypeWork = (TextView)vi.findViewById(R.id.txtTypeWork);
        TextView txtTimeLastEdit = (TextView)vi.findViewById(R.id.txtTimeLastEdit);

        txtJobs.setText(pos.get(position));
        txtPlaces.setText(places.get(position));
        txtTypeWork.setText(typeWork.get(position));
        txtTimeLastEdit.setText(DateUtils.convertDateFormat(timeEdit.get(position).substring(0,10)));
        return vi;
    }
}
