package com.example.ev.SoKhop.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;

import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.ev.SoKhop.Adapter.SoKhopToChucAdapter;
import com.example.ev.SoKhop.Api.HttpGetSoKhopToChuc;
import com.example.ev.SoKhop.Api.core.API;
import com.example.ev.SoKhop.Api.core.HttpResponseCode;
import com.example.ev.SoKhop.Base.BaseFragment;
import com.example.ev.SoKhop.Model.SoKhopModel;
import com.example.ev.SoKhop.R;
import com.example.ev.SoKhop.Utils.Pref;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MSI on 11/2/2016.
 */

public class SoKhopToChucFragment extends BaseFragment {
    private SwipeMenuListView listView;
    private ArrayList<String> pos = new ArrayList<>();
    private ArrayList<String> places = new ArrayList<>();
    private ArrayList<String> typeWork = new ArrayList<>();
    private ArrayList<String> timeEdit = new ArrayList<>();
    private ArrayList<String> sokhop = new ArrayList<>();
    private ArrayList<String> luotxem = new ArrayList<>();
    private ArrayList<String> ungtuyen = new ArrayList<>();
    private ArrayList<String> ngayhethan = new ArrayList<>();
    private ArrayList<String> JobId = new ArrayList<>();
    private List<SoKhopModel> soKhopModels;
    private SoKhopToChucAdapter customList;
    private String jca_name,jca_value,jca_tags,jca_description,jca_created_at,jca_updated_at,lang_name,lang_code,lang_created_at,lang_updated_at;
    private String jca_id,lang_id;
    private int refreshValue = 1;
    private int refreshValuessss = -10;
    private String job_work_location;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_sokhop_tochuc, container, false);
        mActivity.databaseHelper.deleteAllSoKhop();
        soKhopModels = new ArrayList<>();
        getSoKhop(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),"1");
//        if(recruitmentModels.size()>0) {
//            for (int i = 0; i < recruitmentModels.size(); i++) {
//                pos.add(recruitmentModels.get(i).getJob_title());
//                try {
//                    JSONArray jsonArrayLoca = new JSONArray(recruitmentModels.get(i).getJob_location());
//                    if(jsonArrayLoca.length()>0){
//                        JSONObject jsonObject = jsonArrayLoca.getJSONObject(0);
//                        places.add(jsonObject.getString("province_value"));
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                if(recruitmentModels.get(i).getJob_time_type().equals("1")){
//                    typeWork.add(getString(R.string.parttime));
//                }else if(recruitmentModels.get(i).getJob_time_type().equals("2")){
//                    typeWork.add(getString(R.string.fulltime));
//                }else {
//                    typeWork.add("");
//                }
//                timeEdit.add(recruitmentModels.get(i).getJob_updated_at());
//                //// TODO: 10/17/2016 tạm thời để sokop = views_number
//                sokhop.add(recruitmentModels.get(i).getJob_views_number());
//                luotxem.add(recruitmentModels.get(i).getJob_views_number());
//                ungtuyen.add(recruitmentModels.get(i).getJob_apply_number());
//                ngayhethan.add(recruitmentModels.get(i).getJob_expired_time());
//                JobId.add(recruitmentModels.get(i).getJob_id());
//            }
//        }
        listView = (SwipeMenuListView)rootView.findViewById(R.id.listView);
        customList = new SoKhopToChucAdapter(mActivity, pos, places, typeWork,timeEdit, sokhop, luotxem, ungtuyen,ngayhethan);
        listView.setAdapter(customList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mActivity.p.putString(Pref.PREF_KEY_USER_SOKHOP,JobId.get(position));
                mActivity.selectItem(26);
            }
        });
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
                        getSoKhop(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),String.valueOf(refreshValue));
                    }
                }
            }
        });
        showToolBarItem();
        return rootView;
    }
    public void getSoKhop(String token,String page){
        HttpGetSoKhopToChuc api = new HttpGetSoKhopToChuc(mActivity,token,page);
        api.request(new API.APIDelegate() {
            @Override
            public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                if (httpResponseCode == HttpResponseCode.SUCCESS) {
                    refreshValuessss+=10;
                    try {
                        JSONArray data = jsonResponse.getJSONArray("data");
                        for(int i = 0;i<data.length();i++){
                            JSONObject jsonObject = data.getJSONObject(i);
                            JSONObject jsonObjectJob = jsonObject.getJSONObject("job");
                            JSONArray jsonArrayCategories = jsonObject.getJSONArray("candidates");
                            String job_updated_at = jsonObjectJob.getString("job_updated_at").replace("null","");
                            String job_expired_time = jsonObjectJob.getString("job_expired_time").replace("null","");
                            String job_apply_number = jsonObjectJob.getString("job_apply_number").replace("null","");
                            String job_views_number = jsonObjectJob.getString("job_views_number").replace("null","");
                            String candidates = jsonArrayCategories.toString();
                            JSONArray jsonArrayLoca = jsonObjectJob.getJSONArray("job_location");
                            for (int j = 0;j<jsonArrayLoca.length();j++){
                                JSONObject jsonObject1 = jsonArrayLoca.getJSONObject(j);
                                 job_work_location = jsonObject1.getString("province_value").replace("null","");
                            }
                            String job_time_type = jsonObjectJob.getString("job_time_type").replace("null","");
                            String job_title = jsonObjectJob.getString("job_title").replace("null","");
                            mActivity.databaseHelper.insertASoKhop(job_title,job_work_location,job_time_type,candidates,job_views_number,job_apply_number,job_updated_at,job_expired_time);
                        }
                        soKhopModels = mActivity.databaseHelper.getAllSoKhop();
                        if(soKhopModels.size()>0) {
                            for (int i = refreshValuessss; i < soKhopModels.size(); i++) {
                                pos.add(soKhopModels.get(i).getJob_title());
                                places.add(soKhopModels.get(i).getJob_work_location());
                                if(soKhopModels.get(i).getJob_time_type().equals("1")){
                                    typeWork.add(mActivity.getString(R.string.parttime));
                                }else if(soKhopModels.get(i).getJob_time_type().equals("2")){
                                    typeWork.add(mActivity.getString(R.string.fulltime));
                                }else {
                                    typeWork.add("");
                                }
                                timeEdit.add(soKhopModels.get(i).getJob_updated_at());
                                //// TODO: 10/17/2016 tạm thời để sokop = views_number
                                JSONArray numberCandicate = new JSONArray(soKhopModels.get(i).getCandidates());
                                sokhop.add(String.valueOf(numberCandicate.length()));
                                luotxem.add(soKhopModels.get(i).getJob_views_number());
                                ungtuyen.add(soKhopModels.get(i).getJob_apply_number());
                                ngayhethan.add(soKhopModels.get(i).getJob_expired_time());
                                JobId.add(numberCandicate.toString());
                            }
                        }
                        customList.notifyDataSetChanged();
//                        if(pos != 999){
//                            selectItem(pos);
//                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

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
    }
//    public static NotificationTabFragment newInstance(String text) {
//
//        NotificationTabFragment f = new NotificationTabFragment();
//        Bundle b = new Bundle();
//        b.putString("msg", text);
//
//        f.setArguments(b);
//
//        return f;
//    }
}
