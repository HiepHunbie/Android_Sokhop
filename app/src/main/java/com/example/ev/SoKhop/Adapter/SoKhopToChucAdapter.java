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
 * Created by MSI on 11/2/2016.
 */

public class SoKhopToChucAdapter extends BaseAdapter {

    private Activity activity;
    private static LayoutInflater inflater=null;
    private ArrayList<String> pos;
    private ArrayList<String> places;
    private ArrayList<String> typeWork;
    private ArrayList<String> timeEdit;
    private ArrayList<String> sokhop;
    private ArrayList<String> luotxem;
    private ArrayList<String> ungtuyen;
    private ArrayList<String> ngayhethan;

    public SoKhopToChucAdapter(Activity a, ArrayList<String> pos, ArrayList<String> places, ArrayList<String> typeWork, ArrayList<String> timeEdit,
                                   ArrayList<String> sokhop, ArrayList<String> luotxem, ArrayList<String> ungtuyen, ArrayList<String> ngayhethan) {
        activity = a;
        this.pos = pos;
        this.places = places;
        this.typeWork = typeWork;
        this.timeEdit = timeEdit;
        this.sokhop = sokhop;
        this.luotxem = luotxem;
        this.ungtuyen = ungtuyen;
        this.ngayhethan = ngayhethan;
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
            vi = inflater.inflate(R.layout.item_sokhop_tochuc, null);

        TextView txtJobs = (TextView)vi.findViewById(R.id.txtJobs);
        TextView txtPlaces = (TextView)vi.findViewById(R.id.txtPlaces);
        TextView txtTypeWork = (TextView)vi.findViewById(R.id.txtTypeWork);
        TextView txtTimeSuaLanCuoi = (TextView)vi.findViewById(R.id.txtTimeSuaLanCuoi);
        TextView txtTimeSoKhop = (TextView)vi.findViewById(R.id.txtTimeSoKhop);
        TextView txtTimeLuotXem = (TextView)vi.findViewById(R.id.txtTimeLuotXem);
        TextView txtTimeUngTuyen = (TextView)vi.findViewById(R.id.txtTimeUngTuyen);
        TextView txtTimeHetHan = (TextView)vi.findViewById(R.id.txtTimeHetHan);

        txtJobs.setText(pos.get(position));
        txtPlaces.setText(places.get(position));
        txtTypeWork.setText(typeWork.get(position));
        if(timeEdit.get(position).length()>5) {
            txtTimeSuaLanCuoi.setText(timeEdit.get(position).substring(0, 10));
        }
        txtTimeSoKhop.setText(sokhop.get(position));
        txtTimeLuotXem.setText(luotxem.get(position));
        txtTimeUngTuyen.setText(ungtuyen.get(position));
        if(ngayhethan.get(position).length()>5){
            txtTimeHetHan.setText(ngayhethan.get(position).substring(0,10));
        }
        return vi;
    }
}
