package com.example.ev.SoKhop.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.ev.SoKhop.Adapter.TabInfoAdapter;
import com.example.ev.SoKhop.Api.HttpDeleteNotification;
import com.example.ev.SoKhop.Api.HttpGetNotification;
import com.example.ev.SoKhop.Api.HttpUpdateNotification;
import com.example.ev.SoKhop.Api.core.API;
import com.example.ev.SoKhop.Api.core.HttpResponseCode;
import com.example.ev.SoKhop.Base.BaseFragment;
import com.example.ev.SoKhop.Dialog.DialogCall;
import com.example.ev.SoKhop.Model.NotificationModel;
import com.example.ev.SoKhop.R;
import com.example.ev.SoKhop.Utils.Pref;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GMAN on 9/27/2016.
 */

public class SpamNotificationFragment extends BaseFragment {
    private SwipeMenuListView listView;
    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<String> messages = new ArrayList<>();
    private ArrayList<String> times = new ArrayList<>();
    private ArrayList<String> quantam = new ArrayList<>();
    private ArrayList<String> isRead = new ArrayList<>();
    private ArrayList<String> notify_ids = new ArrayList<>();
    private List<NotificationModel> notificationModels ;
    private int refreshValue = 1;
    private int refreshValuessss = -10;
    private TabInfoAdapter customList;
    private String notify_title, notify_content, notify_image_url, notify_is_read,
            notify_owner_id, notify_type, notify_created_at, notiusr_user_id,notify_id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_info_3, container, false);
        mActivity.databaseHelper.deleteAllNotification();
        notificationModels = new ArrayList<>();
        getNotification(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),"3","1","10");
        listView = (SwipeMenuListView)rootView.findViewById(R.id.listView);
        customList = new TabInfoAdapter(mActivity, names, messages, times,quantam,isRead,notify_ids,2);
        listView.setAdapter(customList);
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem delete = new SwipeMenuItem(
                        mActivity);
                delete.setBackground(R.color.recruitment2);
                delete.setWidth((int) mActivity.getResources().getDimension(R.dimen._80sdp));
                delete.setTitleSize(20);
                delete.setTitleColor(Color.WHITE);
                delete.setTitle(getString(R.string.delete));
                menu.addMenuItem(delete);
            }
        };
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            public int currentScrollState;
            public int currentFirstVisibleItem,currentVisibleItemCount,currentTotalItemCount;
            public int mLastFirstVisibleItem;
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                this.currentScrollState = scrollState;
                this.isScrollCompleted();
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                this.currentFirstVisibleItem = firstVisibleItem;
                this.currentVisibleItemCount = visibleItemCount;
                this.currentTotalItemCount = totalItemCount;
            }
            private void isScrollCompleted() {

                if (currentFirstVisibleItem + currentVisibleItemCount >= currentTotalItemCount) {
                    if (this.currentVisibleItemCount > 0
                            && this.currentScrollState == SCROLL_STATE_IDLE) {
                        refreshValue+=1;
                        getNotification(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),"3",String.valueOf(refreshValue),"10");
                    }
                }
            }
        });
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        HttpDeleteNotification httpDeleteNotification = new HttpDeleteNotification(mActivity, mActivity.p.getString(Pref.PREF_KEY_TOKEN,null));
                        httpDeleteNotification.request(notify_ids.get(position), new API.APIDelegate() {
                            @Override
                            public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                                if (httpResponseCode == HttpResponseCode.SUCCESS) {
                                    DialogCall.showResponse(mActivity, mActivity.getString(R.string.delete_success), new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            int id = v.getId();
                                            if (id == R.id.btnOk) {
                                                InfoFragment.selectItem(2);
                                            }
                                        }
                                    });
                                }
                            }
                        });
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int responses = HttpUpdateNotification.updateIsReadNotification(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),notify_ids.get(position));
                if(responses == 200){
                    DialogCall.showResponse(mActivity, mActivity.getString(R.string.dadoctin), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int id = v.getId();
                            if (id == R.id.btnOk) {
                                mActivity.getCountNotification(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null));
                                InfoFragment.selectItem(2);
                            }
                        }
                    });
                }
            }
        });
        listView.setMenuCreator(creator);
        showToolBarItem();
        return rootView;
    }
    public static SpamNotificationFragment newInstance(String text) {

        SpamNotificationFragment f = new SpamNotificationFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }
    public void getNotification(String token, final String notification_types, String page, String limit){
        HttpGetNotification api = new HttpGetNotification(mActivity,token,notification_types,page,limit);
        api.request(new API.APIDelegate() {
            @Override
            public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                if (httpResponseCode == HttpResponseCode.SUCCESS) {
                    refreshValuessss += 10;
                    try {
                        JSONObject data = jsonResponse.getJSONObject("data");
                        JSONArray jsonArray = data.getJSONArray("notifications");
                        if(jsonArray.length()>0) {
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObjectData = jsonArray.getJSONObject(i);
                                notify_title = jsonObjectData.getString("notify_title").replace("null", "");
                                notify_content = jsonObjectData.getString("notify_content").replace("null", "");
                                notify_image_url = jsonObjectData.getString("notify_image_url").replace("null", "");
                                notify_is_read = jsonObjectData.getString("notify_is_read").replace("null", "");
                                notify_owner_id = jsonObjectData.getString("notify_owner_id").replace("null", "");
                                notify_type = jsonObjectData.getString("notify_type").replace("null", "");
                                notify_created_at = jsonObjectData.getString("notify_created_at").replace("null", "");
                                notiusr_user_id = jsonObjectData.getString("notiusr_user_id").replace("null", "");
                                notify_id = jsonObjectData.getString("notify_id").replace("null", "");
                                mActivity.databaseHelper.insertANotification(notify_title, notify_content, notify_image_url, notify_is_read,
                                        notify_owner_id, notify_type, notify_created_at, notiusr_user_id,notify_id);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    notificationModels = mActivity.databaseHelper.getAllNotification();
                    if (notificationModels.size() > 0) {
                        for (int j = refreshValuessss; j < notificationModels.size(); j++) {
                            names.add(notificationModels.get(j).getNotify_title());
                            messages.add(notificationModels.get(j).getNotify_content());
                            times.add(notificationModels.get(j).getNotify_created_at());
                            quantam.add(notificationModels.get(j).getNotify_type());
                            isRead.add(notificationModels.get(j).getNotify_is_read());
                            notify_ids.add(notificationModels.get(j).getNotify_id());
                        }
                    }
                    customList.notifyDataSetChanged();
//                        if(pos != 999){
//                            selectItem(pos);
//                        }
                }else {
                }
            }
        });
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
        mActivity.showDeleteItem( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                if (id == R.id.btnDelete) {
                }
            }
        });
    }
}
