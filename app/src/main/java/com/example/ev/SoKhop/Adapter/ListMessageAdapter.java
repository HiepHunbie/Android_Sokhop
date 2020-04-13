package com.example.ev.SoKhop.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ev.SoKhop.R;

/**
 * Created by GMAN on 9/26/2016.
 */

public class ListMessageAdapter extends BaseAdapter {

    private Activity activity;
    private static LayoutInflater inflater=null;
    private String[] names;
    private String[] messages;
    private String[] times;

    public ListMessageAdapter(Activity a, String[] names, String[] desc, String[] imageid) {
        activity = a;
        this.names = names;
        this.messages = desc;
        this.times = imageid;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return names.length;
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
            vi = inflater.inflate(R.layout.item_list_message, null);

        TextView name = (TextView)vi.findViewById(R.id.title); // title
        TextView message = (TextView)vi.findViewById(R.id.artist); // artist name
        TextView time = (TextView)vi.findViewById(R.id.duration); // duration
        ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); // thumb image


        // Setting all values in listview
        name.setText(names[position]);
        message.setText(messages[position]);
        time.setText(times[position]);
        return vi;
    }
}
