package com.example.ev.SoKhop.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ev.SoKhop.Base.BaseFragment;
import com.example.ev.SoKhop.R;
import com.example.ev.SoKhop.Utils.Pref;

/**
 * Created by GMAN on 9/29/2016.
 */

public class JobsFragment extends BaseFragment {
    public FragmentTabHost mTabHost;
    public Context mContext;
    private Button btnSokhop,btnApplied,btnQuantam;
    private ViewPager pager;

    public JobsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_jobs, container, false);
        mContext= getActivity().getApplicationContext();
        mActivity.p.putString(Pref.PREF_KEY_VIEW_NEWS_BEFORE,"0");
        p.putString(Pref.BOOLEAN_IS_FROM_JOB,"1");
        p.getString(Pref.JOB_TAB,null);
        btnSokhop = (Button)rootView.findViewById(R.id.btnTabJobs1);
        btnQuantam = (Button)rootView.findViewById(R.id.btnTabJobs2);
        btnApplied = (Button)rootView.findViewById(R.id.btnTabJobs3);
        if( p.getString(Pref.JOB_TAB,null)!=null){
            if(p.getString(Pref.JOB_TAB,null).equals("1")){
                selectItem(1);
            }else if(p.getString(Pref.JOB_TAB,null).equals("2")){
                selectItem(2);
            }else{
                selectItem(0);
            }
        }else {
            selectItem(0);
        }
        btnSokhop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectItem(0);
            }
        });
        btnApplied.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectItem(2);
            }
        });
        btnQuantam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectItem(1);
            }
        });
        showToolBarItem();
        return rootView;
    }
    public void showToolBarItem(){
        mActivity.showItemToolBar(false, false, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                if (id == R.id.btnCreate) {
                }
            }
        });
//        mActivity.showDeleteItem(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int id = v.getId();
//                if (id == R.id.btnDelete) {
//                }
//            }
//        });
    }
    private void selectedUser(boolean slbtnNhap,boolean slbtnDaDang,boolean slbtnDaKhoa){
        btnSokhop.setSelected(slbtnNhap);
        btnQuantam.setSelected(slbtnDaDang);
        btnApplied.setSelected(slbtnDaKhoa);
    }
    private void selectItem(int position) {

        BaseFragment fragment = null;

        switch (position) {
            case 0:
                fragment = new SoKhopJobFragment();
                selectedUser(true,false,false);
                break;
            case 1:
                fragment = new SavedJobFragment();
                selectedUser(false,true,false);
                break;
            case 2:
                fragment = new AppliedJobFragment();
                selectedUser(false,false,true);
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = mActivity.getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame_tab, fragment).commit();

        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }
    }
}
