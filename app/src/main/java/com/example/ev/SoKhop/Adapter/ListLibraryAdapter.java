package com.example.ev.SoKhop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.ev.SoKhop.Activity.MainActivity;
import com.example.ev.SoKhop.R;
import com.example.ev.SoKhop.Utils.Pref;

import java.util.ArrayList;

/**
 * Created by GMAN on 9/27/2016.
 */

public class ListLibraryAdapter extends BaseAdapter {

    private MainActivity activity;
    private static LayoutInflater inflater = null;
    private ArrayList<String> names;
    private ArrayList<String> messages;
    private ArrayList<String> image;
    private boolean isCheckDelete;

    public interface OnCheckListener {
        void onCheck(int position, String name);

        void onUncheck(int position, String name);
    }

    public void setCheckDelete(boolean checkDelete) {
        isCheckDelete = checkDelete;
    }

    public OnCheckListener listener;

    public void setListener(OnCheckListener listener) {
        this.listener = listener;
    }

    public ListLibraryAdapter(MainActivity a, ArrayList<String> names, ArrayList<String> desc, ArrayList<String> imageid) {
        activity = a;
        this.names = names;
        this.messages = desc;
        this.image = imageid;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return names.size() + 1;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, final View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.item_list_library, null);

        TextView txtTitle = (TextView) vi.findViewById(R.id.txtTitle); // title
        TextView txtNumber = (TextView) vi.findViewById(R.id.txtNumber); // artist name
        ImageView imgLibrary = (ImageView) vi.findViewById(R.id.imgLibraryss); // thumb image
        CheckBox checkBox = (CheckBox) vi.findViewById(R.id.cb_check_delete);
        final ProgressBar progressAvatar = (ProgressBar)vi.findViewById(R.id.progressAvatar);

        if (position == 0) {
            progressAvatar.setVisibility(View.GONE);
            if (activity.p.getString(Pref.BOOLEAN_IS_ME, null) != null) {
                if (activity.p.getString(Pref.BOOLEAN_IS_ME, null).equals("1")) {
//                    vi.setVisibility(View.GONE);
                    vi.setEnabled(false);
                    imgLibrary.setImageResource(R.drawable.no_image);
                } else {
                    vi.setEnabled(true);
                    txtTitle.setText(R.string.add_new);
                    imgLibrary.setImageResource(R.drawable.button_addnew1);
                    imgLibrary.setScaleType(ImageView.ScaleType.FIT_XY);
                    txtNumber.setVisibility(View.GONE);
                }
            } else {
                vi.setEnabled(true);
                txtTitle.setText(R.string.add_new);
                imgLibrary.setImageResource(R.drawable.button_addnew1);
                imgLibrary.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                txtNumber.setVisibility(View.GONE);
            }

        } else {
            // Setting all values in listview
            txtNumber.setVisibility(View.VISIBLE);
            txtTitle.setText(names.get(position - 1));
            txtNumber.setText(messages.get(position - 1));
            if (image.get(position - 1).length() < 5) {
                imgLibrary.setImageResource(R.drawable.no_image);
                progressAvatar.setVisibility(View.GONE);
            } else {
                Glide.with(activity).load(image.get(position - 1)).listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        progressAvatar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        progressAvatar.setVisibility(View.GONE);
                        return false;
                    }
                }).into(imgLibrary);
            }
            setCheckbox(position, checkBox);
        }

        return vi;
    }

    private void setCheckbox(final int position, CheckBox checkBox) {
        final String name = names.get(position - 1);
        if (isCheckDelete) {
            checkBox.setVisibility(View.VISIBLE);
        }else {
            checkBox.setVisibility(View.INVISIBLE);
        }
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    listener.onCheck(position - 1, name);
                } else {
                    listener.onUncheck(position - 1, name);
                }
            }
        });
    }
}
