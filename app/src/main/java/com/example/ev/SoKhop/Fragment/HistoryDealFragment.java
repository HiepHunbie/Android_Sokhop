package com.example.ev.SoKhop.Fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.ev.SoKhop.Adapter.ListHistoryDealAdapter;
import com.example.ev.SoKhop.Base.BaseFragment;
import com.example.ev.SoKhop.R;

/**
 * Created by GMAN on 9/26/2016.
 */

public class HistoryDealFragment extends BaseFragment {
    private SwipeMenuListView listViewMessage;
    private String desc[] = {
            "Cùng sử dụng SoKhop.vn",
            "Cùng sử dụng SoKhop.vn",
            "Cùng sử dụng SoKhop.vn",
            "Cùng sử dụng SoKhop.vn"
    };


    private String imageid[] = {
            "5 phút",
            "5 phút",
            "5 phút",
            "5 phút"
    };
    public HistoryDealFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_history_deal, container, false);
        listViewMessage = (SwipeMenuListView)rootView.findViewById(R.id.listViewMessage);
        ListHistoryDealAdapter customList = new ListHistoryDealAdapter(mActivity, desc, imageid);
        listViewMessage.setAdapter(customList);
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {

                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        mActivity);
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                deleteItem.setWidth(150);
                deleteItem.setTitle("delete");
                menu.addMenuItem(deleteItem);
            }
        };
        listViewMessage.setMenuCreator(creator);
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
