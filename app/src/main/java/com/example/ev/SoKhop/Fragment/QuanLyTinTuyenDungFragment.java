package com.example.ev.SoKhop.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.ev.SoKhop.Base.BaseFragment;
import com.example.ev.SoKhop.R;
import com.example.ev.SoKhop.Utils.Pref;

/**
 * Created by MSI on 10/13/2016.
 */

public class QuanLyTinTuyenDungFragment extends BaseFragment {
    public FragmentTabHost mTabHost;
    public Context mContext;
    private static Button btnNhap;
    private static Button btnDaDang;
    private static Button btnDaKhoa;
    private static Button btnHetHan;
    private static Button btnDaXoa;
    private ViewPager pager;
    private ImageView btnAdd;

    public QuanLyTinTuyenDungFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootViews = inflater.inflate(R.layout.fragment_quanlytintuyendung, container, false);
        mContext= getActivity().getApplicationContext();
        p.putString(Pref.BOOLEAN_IS_FROM_JOB,"2");
        mActivity.p.putString(Pref.PREF_KEY_VIEW_NEWS_BEFORE,"0");
        btnNhap = (Button)rootViews.findViewById(R.id.btnTabQuanLy1);
        btnDaDang = (Button)rootViews.findViewById(R.id.btnTabQuanLy2);
        btnDaKhoa = (Button)rootViews.findViewById(R.id.btnTabQuanLy3);
        btnHetHan = (Button)rootViews.findViewById(R.id.btnTabQuanLy4);
        btnDaXoa = (Button)rootViews.findViewById(R.id.btnTabQuanLy5);
        btnAdd = (ImageView)rootViews.findViewById(R.id.btnAdd);
        if(p.getString(Pref.TYPE_QUANLYTINTUYENDUNG_TAB,null)!=null){
            selectItem(Integer.parseInt(p.getString(Pref.TYPE_QUANLYTINTUYENDUNG_TAB,null)));
        }else {
            selectItem(0);
        }
        btnNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.p.putString(Pref.TYPE_QUANLYTINTUYENDUNG_TAB,"0");
                selectItem(0);
            }
        });
        btnDaDang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.p.putString(Pref.TYPE_QUANLYTINTUYENDUNG_TAB,"1");
                selectItem(1);
            }
        });
        btnDaKhoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.p.putString(Pref.TYPE_QUANLYTINTUYENDUNG_TAB,"2");
                selectItem(2);
            }
        });
        btnHetHan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.p.putString(Pref.TYPE_QUANLYTINTUYENDUNG_TAB,"3");
                selectItem(3);
            }
        });
        btnDaXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.p.putString(Pref.TYPE_QUANLYTINTUYENDUNG_TAB,"4");
                selectItem(4);
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.p.putString(Pref.PREF_KEY_JOB_ID,"9999");
                mActivity.selectItem(18);
            }
        });
        showToolBarItem();
        rootViews.setFocusableInTouchMode(true);
        rootViews.requestFocus();
        rootViews.setOnKeyListener( new View.OnKeyListener()
        {
            @Override
            public boolean onKey( View v, int keyCode, KeyEvent event )
            {
                if( keyCode == KeyEvent.KEYCODE_BACK )
                {
                    mActivity.selectItem(16);
                    return true;
                }
                return false;
            }
        } );
        return rootViews;
    }
    private static void selectedUser(boolean slbtnNhap, boolean slbtnDaDang, boolean slbtnDaKhoa, boolean slbtnHetHan, boolean slbtnDaXoa){
        btnNhap.setSelected(slbtnNhap);
        btnDaDang.setSelected(slbtnDaDang);
        btnDaKhoa.setSelected(slbtnDaKhoa);
        btnHetHan.setSelected(slbtnHetHan);
        btnDaXoa.setSelected(slbtnDaXoa);
    }
    public static void selectItem(int position) {

        BaseFragment fragment = null;

        switch (position) {
            case 0:
                fragment = new DraftRecruitmentFragment();
                selectedUser(true,false,false,false,false);
                break;
            case 1:
                fragment = new PublishedRecruitmentFragment();
                selectedUser(false,true,false,false,false);
                break;
            case 2:
                fragment = new LookedRecruitmentFragment();
                selectedUser(false,false,true,false,false);
                break;
            case 3:
                fragment = new ExpiredRecruitmentFragment();
                selectedUser(false,false,false,true,false);
                break;
            case 4:
                fragment = new DeletedRecruitmentFragment();
                selectedUser(false,false,false,false,true);
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
}
