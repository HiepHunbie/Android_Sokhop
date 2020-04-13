package com.example.ev.SoKhop.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ev.SoKhop.R;

/**
 * Created by GMAN on 9/26/2016.
 */

public class ListHistoryDealAdapter extends BaseAdapter {

    private Activity activity;
    private static LayoutInflater inflater=null;
    private String[] messages;
    private String[] times;

    public ListHistoryDealAdapter(Activity a, String[] desc, String[] imageid) {
        activity = a;
        this.messages = desc;
        this.times = imageid;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return messages.length;
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
            vi = inflater.inflate(R.layout.item_list_history_deal, null);

        TextView message = (TextView)vi.findViewById(R.id.artist); // artist name
        TextView time = (TextView)vi.findViewById(R.id.duration); // duration
        // Setting all values in listview
        message.setText(messages[position]);
        time.setText(times[position]);
        return vi;
    }
}
