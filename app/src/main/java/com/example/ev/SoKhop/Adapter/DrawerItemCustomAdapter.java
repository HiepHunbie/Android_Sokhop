package com.example.ev.SoKhop.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ev.SoKhop.Activity.MainActivity;
import com.example.ev.SoKhop.Model.DataModel;
import com.example.ev.SoKhop.R;

/**
 * Created by anupamchugh on 10/12/15.
 */
public class DrawerItemCustomAdapter extends ArrayAdapter<DataModel> {

    Context mContext;
    int layoutResourceId;
    DataModel data[] = null;

    public DrawerItemCustomAdapter(Context mContext, int layoutResourceId, DataModel[] data) {

        super(mContext, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItem = convertView;

        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        listItem = inflater.inflate(layoutResourceId, parent, false);

        ImageView imageViewIcon = (ImageView) listItem.findViewById(R.id.imageViewIcon);
        TextView textViewName = (TextView) listItem.findViewById(R.id.textViewName);
        TextView textNumber = (TextView) listItem.findViewById(R.id.textNumber);

        DataModel folder = data[position];
        if (position == MainActivity.mSelectedItem) {
            listItem.setBackgroundResource(R.color.toolbar);
        }else {
            listItem.setBackgroundResource(R.color.white);
        }
        imageViewIcon.setImageResource(folder.icon);
        textViewName.setText(folder.name);

        if(position == 1||position == 3||position == 4){
            textViewName.setTextColor(Color.parseColor("#BBBBBB"));
        }
        textNumber.setText(folder.number);

        return listItem;
    }
}

