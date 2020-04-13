package com.example.ev.SoKhop.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ev.SoKhop.R;

/**
 * Created by GMAN on 9/27/2016.
 */

public class MyPagesAdapter extends BaseAdapter {

    private Activity activity;
    private static LayoutInflater inflater=null;
    private String[] messages;
    private Integer[] image;

    public MyPagesAdapter(Activity a, String[] desc, Integer[] imageid) {
        activity = a;
        this.messages = desc;
        this.image = imageid;
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
            vi = inflater.inflate(R.layout.item_list_mypages, null);

        TextView message = (TextView)vi.findViewById(R.id.txtTab); // artist name
        ImageView imgIcon = (ImageView)vi.findViewById(R.id.imgIcon) ;
        // Setting all values in listview
        message.setText(messages[position]);
        imgIcon.setImageResource(image[position]);
        if(messages[position].equals("Phát Triển Bản Thân")){
            message.setTextColor(Color.parseColor("#BBBBBB"));
        }else if(messages[position].equals("Nhân Sự")){
            message.setTextColor(Color.parseColor("#BBBBBB"));
        }else {
            message.setTextColor(Color.parseColor("#707070"));
        }
        return vi;
    }
}
