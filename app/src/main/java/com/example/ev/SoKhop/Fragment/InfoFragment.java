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
 * Created by anupamchugh on 10/12/15.
 */
public class InfoFragment extends BaseFragment{
    public FragmentTabHost mTabHost;
    public Context mContext;
    private static Button btnThongbao;
    private static Button btnQuantam;
    private static Button btnSpam;
    private ViewPager pager;

    public InfoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_info, container, false);
        mContext= getActivity().getApplicationContext();

        btnThongbao = (Button)rootView.findViewById(R.id.btnTabInfo1);
        btnQuantam = (Button)rootView.findViewById(R.id.btnTabInfo2);
        btnSpam = (Button)rootView.findViewById(R.id.btnTabInfo3);
        btnThongbao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectItem(0);
            }
        });
        btnQuantam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectItem(1);
            }
        });
        btnSpam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectItem(2);
            }
        });
        selectItem(0);
        showToolBarItem();
        rootView.setFocusableInTouchMode(true);
        rootView.requestFocus();
        rootView.setOnKeyListener( new View.OnKeyListener()
        {
            @Override
            public boolean onKey( View v, int keyCode, KeyEvent event )
            {
                if( keyCode == KeyEvent.KEYCODE_BACK )
                {
                    mActivity.selectItem(0);
                    return true;
                }
                return false;
            }
        } );
        return rootView;
    }

    private static void selectedButton(boolean slbtnThongbao, boolean slbtnQuantam, boolean slbtnSpam){
        btnThongbao.setSelected(slbtnThongbao);
        btnQuantam.setSelected(slbtnQuantam);
        btnSpam.setSelected(slbtnSpam);
    }
    public static void selectItem(int position) {

        BaseFragment fragment = null;

        switch (position) {
            case 0:
                fragment = new NotificationTabFragment();
                selectedButton(true,false,false);
                break;
            case 1:
                fragment = new QuanTamNotificationFragment();
                selectedButton(false,true,false);
                break;
            case 2:
                fragment = new SpamNotificationFragment();
                selectedButton(false,false,true);
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
        mActivity.showItemToolBar(true, false, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                if (id == R.id.btnCreate) {
                }
            }
        });
    }
}
