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

import com.example.ev.SoKhop.Base.BaseFragment;
import com.example.ev.SoKhop.R;

/**
 * Created by MSI on 10/25/2016.
 */

public class QuanLyUngVienFragment extends BaseFragment {
    public FragmentTabHost mTabHost;
    public Context mContext;
    private static Button btnDaUngTuyen;
    private static Button btnDaLamTest;
    private static Button btnDaPhongVan;
    private static Button btnDaGapMat;
    private ViewPager pager;

    public QuanLyUngVienFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootViews = inflater.inflate(R.layout.fragment_quanlyungvien, container, false);
        mContext= getActivity().getApplicationContext();

        btnDaUngTuyen = (Button)rootViews.findViewById(R.id.btnTabQuanLy1);
        btnDaLamTest = (Button)rootViews.findViewById(R.id.btnTabQuanLy2);
        btnDaPhongVan = (Button)rootViews.findViewById(R.id.btnTabQuanLy3);
        btnDaGapMat = (Button)rootViews.findViewById(R.id.btnTabQuanLy4);
        selectItem(0);
        btnDaUngTuyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectItem(0);
            }
        });
        btnDaLamTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectItem(1);
            }
        });
        btnDaPhongVan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectItem(2);
            }
        });
        btnDaGapMat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectItem(3);
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
                    mActivity.selectItem(6);
                    return true;
                }
                return false;
            }
        } );
        return rootViews;
    }
    private static void selectedUser(boolean slbtnNhap, boolean slbtnDaDang, boolean slbtnDaKhoa, boolean slbtnHetHan){
        btnDaUngTuyen.setSelected(slbtnNhap);
        btnDaLamTest.setSelected(slbtnDaDang);
        btnDaPhongVan.setSelected(slbtnDaKhoa);
        btnDaGapMat.setSelected(slbtnHetHan);
    }
    public static void selectItem(int position) {

        BaseFragment fragment = null;

        switch (position) {
            case 0:
                fragment = new UngVienDaUngTuyenFragment();
                selectedUser(true,false,false,false);
                break;
            case 1:
                fragment = new UngVienDaLamTestFragment();
                selectedUser(false,true,false,false);
                break;
            case 2:
                fragment = new UngVienDaPhongVanFragment();
                selectedUser(false,false,true,false);
                break;
            case 3:
                fragment = new UngVienDaGapMatFragment();
                selectedUser(false,false,false,true);
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
        mActivity.showDeleteItem(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                if (id == R.id.btnDelete) {
                }
            }
        });
    }
}
