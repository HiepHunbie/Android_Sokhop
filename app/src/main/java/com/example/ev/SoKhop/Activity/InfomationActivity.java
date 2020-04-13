package com.example.ev.SoKhop.Activity;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.ev.SoKhop.R;
import com.example.ev.SoKhop.Utils.Pref;

/**
 * Created by GMAN on 9/27/2016.
 */

public class InfomationActivity extends Activity {
    private CharSequence mTitle;
    private String[] mNavigationDrawerItemTitles;
    Toolbar toolbar;
    private Button btnCreate,btnSearchToolBar;
    public static String ITEM = "";
    private ImageButton btnMyPages,btnJobs,btnFindJobs,btnSearch;
    private LinearLayout bottom_tochuc,bottom_canhan;
    private Pref p;
    private boolean isCaNhan = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infomation);
        String item = null;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        bottom_tochuc = (LinearLayout)findViewById(R.id.bottom_tochuc);
        bottom_canhan = (LinearLayout)findViewById(R.id.bottom_canhan);
        p = new Pref(InfomationActivity.this);
        if(p.getString(Pref.TYPE_USER,null).equals("1")){
            isCaNhan = true;
            bottom_tochuc.setVisibility(View.GONE);
            bottom_canhan.setVisibility(View.VISIBLE);
        }else {
            isCaNhan = false;
            bottom_tochuc.setVisibility(View.VISIBLE);
            bottom_canhan.setVisibility(View.GONE);
        }
        mNavigationDrawerItemTitles= getResources().getStringArray(R.array.infomation_items_array);
        btnCreate = (Button)findViewById(R.id.btnCreate);
        btnSearchToolBar = (Button)findViewById(R.id.btnSearchToolBar);
        btnMyPages = (ImageButton)findViewById(R.id.btnMyPages);
        btnJobs = (ImageButton)findViewById(R.id.btnJobs);
        btnFindJobs = (ImageButton)findViewById(R.id.btnFindJobs);
        btnSearch = (ImageButton)findViewById(R.id.btnSearch);

        btnMyPages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                selectItem(90);
            }
        });
        btnJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                selectItem(91);
            }
        });

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.toolbar)));
        mTitle = getTitle();
        if (getIntent().hasExtra(ITEM)) {
            item = getIntent().getStringExtra(ITEM);
            selectItem(Integer.parseInt(item));
        } else {
            throw new IllegalArgumentException("Activity cannot find  extras " + ITEM);
        }
    }
    private void selectItem(int position) {

        Fragment fragment = null;

        switch (position) {
            case 0:
//                fragment = new OtherUserPageFragment();
                break;
            case 1:
//                fragment = new LibraryFragment();
                break;
            case 2:
//                fragment = new EducationFragment();
                break;
            case 3:
//                fragment = new ExpFragment();
                break;
            case 4:
//                fragment = new ExpectJobFragment();
                break;
            case 5:
//                fragment = new ProfileToChucFragment();
                break;
            default:
                break;
        }
        if (fragment != null) {
            android.app.FragmentManager fm = getFragmentManager();
            android.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.content_frame_infomation, fragment);
            fragmentTransaction.commit();
            toolbar.setTitle(mNavigationDrawerItemTitles[position]);
            toolbar.setTitleMarginStart(50);

        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }
    }
    public void showItemToolBar(boolean isCreate,boolean isSearch, final View.OnClickListener listener ){
        if(isCreate){
            btnCreate.setVisibility(View.VISIBLE);
            btnSearchToolBar.setVisibility(View.INVISIBLE);
        }else if(isSearch){
            btnSearchToolBar.setVisibility(View.VISIBLE);
            btnCreate.setVisibility(View.INVISIBLE);
        }else {
            btnSearchToolBar.setVisibility(View.INVISIBLE);
            btnCreate.setVisibility(View.INVISIBLE);
        }

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onClick(v);
                }
            }
        });
        btnSearchToolBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onClick(v);
                }
            }
        });
    }
}
