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
import com.example.ev.SoKhop.Adapter.ListMessageAdapter;
import com.example.ev.SoKhop.Base.BaseFragment;
import com.example.ev.SoKhop.R;

/**
 * Created by anupamchugh on 10/12/15.
 */
public class MessageFragment extends BaseFragment {

    private SwipeMenuListView listViewMessage;
    private String names[] = {
            "Đông Nhi",
            "Nguyễn Trung Hiệp",
            "Maradona",
            "Ronaldo"
    };

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
    public MessageFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_message, container, false);

        listViewMessage = (SwipeMenuListView)rootView.findViewById(R.id.listViewMessage);
        ListMessageAdapter customList = new ListMessageAdapter(mActivity, names, desc, imageid);
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
        mActivity.showItemToolBar(false, true, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                if (id == R.id.btnSearchToolBar) {
                }
            }
        });
    }

}
