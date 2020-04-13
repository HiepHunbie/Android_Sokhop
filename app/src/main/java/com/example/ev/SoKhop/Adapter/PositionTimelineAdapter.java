package com.example.ev.SoKhop.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ev.SoKhop.R;

import java.util.ArrayList;

/**
 * Created by MSI on 10/13/2016.
 */

public class PositionTimelineAdapter extends BaseAdapter {

    private Activity activity;
    private static LayoutInflater inflater=null;
    private ArrayList<String> positions;
    private ArrayList<String> exp;

    public PositionTimelineAdapter(Activity a, ArrayList<String> position, ArrayList<String> exp) {
        activity = a;
        this.positions = position;
        this.exp = exp;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return positions.size();
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
            vi = inflater.inflate(R.layout.item_position_timeline, null);

        TextView txtPos = (TextView)vi.findViewById(R.id.txtPosition);
        TextView txtExp = (TextView)vi.findViewById(R.id.txtExp);


        // Setting all values in listview
        txtPos.setText(positions.get(position));
        txtExp.setText(exp.get(position));
        return vi;
    }
}
