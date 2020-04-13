package com.example.ev.SoKhop.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ev.SoKhop.Activity.MainActivity;
import com.example.ev.SoKhop.Api.HttpUpdateNotification;
import com.example.ev.SoKhop.Dialog.DialogCall;
import com.example.ev.SoKhop.Fragment.InfoFragment;
import com.example.ev.SoKhop.R;
import com.example.ev.SoKhop.Utils.Pref;

import java.util.ArrayList;

/**
 * Created by GMAN on 9/27/2016.
 */

public class TabInfoAdapter extends BaseAdapter {

    private MainActivity activity;
    private static LayoutInflater inflater=null;
    private ArrayList<String> names;
    private ArrayList<String>  messages;
    private ArrayList<String>  times;
    private ArrayList<String>  quantam;
    private ArrayList<String>  isRead;
    private ArrayList<String>  notify_ids;
    private int tabs;

    public TabInfoAdapter(MainActivity a, ArrayList<String>  names, ArrayList<String>  desc, ArrayList<String>  imageid,ArrayList<String>  quantam,ArrayList<String>  isRead,ArrayList<String>  notify_ids,int tabs) {
        activity = a;
        this.names = names;
        this.messages = desc;
        this.times = imageid;
        this.quantam = quantam;
        this.isRead = isRead;
        this.notify_ids = notify_ids;
        this.tabs = tabs;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return names.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null) {
            vi = inflater.inflate(R.layout.item_list_tab_info, null);

            if (isRead.get(position).equals("1")) {
                vi.setBackgroundColor(Color.parseColor("#DDDDDD"));
            } else {
                vi.setBackgroundColor(Color.parseColor("#ffffff"));
            }
            TextView name = (TextView) vi.findViewById(R.id.txtUserName); // title
            TextView message = (TextView) vi.findViewById(R.id.txtInfo); // artist name
            TextView time = (TextView) vi.findViewById(R.id.txtTime); // duration
            ImageView thumb_image = (ImageView) vi.findViewById(R.id.imgStar); // thumb image


            // Setting all values in listview
            name.setText(names.get(position));
            message.setText(messages.get(position));
            if (times.get(position).length() > 5) {
                time.setText(times.get(position).substring(0, 10));
            }
            if (quantam.get(position).equals("2")) {
                thumb_image.setImageResource(R.drawable.ic_quantam_1);
                thumb_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int response = HttpUpdateNotification.updateTypeNotification(activity.p.getString(Pref.PREF_KEY_TOKEN, null), notify_ids.get(position), "1");
                        if (response == 200) {
                            DialogCall.showResponse(activity, activity.getString(R.string.boquantam_success), new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    int id = v.getId();
                                    if (id == R.id.btnOk) {
                                        InfoFragment.selectItem(tabs);
                                    }
                                }
                            });
                        } else {
                            DialogCall.showResponse(activity, activity.getString(R.string.boquantam_error), new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    int id = v.getId();
                                    if (id == R.id.btnOk) {
                                    }
                                }
                            });
                        }
                    }
                });

            } else {
                thumb_image.setImageResource(R.drawable.ic_quantam_2);
                thumb_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int response = HttpUpdateNotification.updateTypeNotification(activity.p.getString(Pref.PREF_KEY_TOKEN, null), notify_ids.get(position), "2");
                        if (response == 200) {
                            DialogCall.showResponse(activity, activity.getString(R.string.quantam_success), new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    int id = v.getId();
                                    if (id == R.id.btnOk) {
                                        InfoFragment.selectItem(tabs);
                                    }
                                }
                            });
                        } else {
                            DialogCall.showResponse(activity, activity.getString(R.string.quantam_error), new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    int id = v.getId();
                                    if (id == R.id.btnOk) {
                                    }
                                }
                            });
                        }
                    }
                });
            }
        }
        return vi;
    }
}
