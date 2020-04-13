package com.example.ev.SoKhop.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.example.ev.SoKhop.Adapter.UngVienQuanTamAdapter;
import com.example.ev.SoKhop.Api.HttpGetListUserSoKhop;
import com.example.ev.SoKhop.Api.core.API;
import com.example.ev.SoKhop.Api.core.HttpResponseCode;
import com.example.ev.SoKhop.Base.BaseFragment;
import com.example.ev.SoKhop.Model.RecruiterModel;
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

public class ListUserSokhopFragment extends BaseFragment {
    public Context mContext;

    private ListView listView;
    private ArrayList<String> logo = new ArrayList<>();;
    private ArrayList<String> title = new ArrayList<>();;
    private ArrayList<String> company = new ArrayList<>();;
    private ArrayList<String> time = new ArrayList<>();;
    private ArrayList<String> other = new ArrayList<>();;
    private ArrayList<String> location = new ArrayList<>();;
    private ArrayList<String> salary = new ArrayList<>();;
    private ArrayList<String> time_type = new ArrayList<>();;
    private ArrayList<String> recruiter_id = new ArrayList<>();
    private List<RecruiterModel> recruiterModels ;
    private UngVienQuanTamAdapter ungVienQuanTamAdapter;
    private int refreshValue = 1;
    private int refreshValuessss = -10;
    private String first_name,last_name,full_name,exp_year,pos_name,pos_value,time_type_user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_user_sokhop, container, false);
        mContext= getActivity().getApplicationContext();
        mActivity.databaseHelper.deleteAllRecruiter();
        recruiterModels = new ArrayList<>();
        String user_sokhop = mActivity.p.getString(Pref.PREF_KEY_USER_SOKHOP,null).substring(1,mActivity.p.getString(Pref.PREF_KEY_USER_SOKHOP,null).length()-1);
        getListUser(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),user_sokhop);
        listView = (ListView) rootView.findViewById(R.id.listView);
        ungVienQuanTamAdapter = new UngVienQuanTamAdapter(mActivity, logo, title, company,time, other, location, salary,time_type,recruiter_id);
        listView.setAdapter(ungVienQuanTamAdapter);
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
//                        refreshValue+=1;
//                        getListUser(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),mActivity.p.getString(Pref.USER_ID,null), String.valueOf(refreshValue),"10");
                    }
                }
            }
        });
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
    }
    public void getListUser(String token,String recrui_id){
        HttpGetListUserSoKhop api = new HttpGetListUserSoKhop(mActivity,token,recrui_id);
        api.request(new API.APIDelegate() {
            @Override
            public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                if (httpResponseCode == HttpResponseCode.SUCCESS) {
//                    mActivity.databaseHelper.deleteAllRecruitment();
                    refreshValuessss += 10;
                    try {
                        JSONArray data = jsonResponse.getJSONArray("data");
                        for(int i = 0;i<data.length();i++){
                            JSONObject jsonObject = data.getJSONObject(i);
                            JSONObject jsonObjectUser = jsonObject.getJSONObject("user_information");
                            if(jsonObjectUser.length()>2){
                                first_name = jsonObjectUser.getString("u_full_name").replace("null","");
                                last_name = jsonObjectUser.getString("u_last_name").replace("null","");
                                full_name = jsonObjectUser.getString("u_full_name").replace("null","");
                            }
                            JSONObject jsonObjectExp = jsonObject.getJSONObject("user_experiences");
                            if(jsonObjectExp.length()>18){
                                exp_year = jsonObjectExp.getString("exp_year").replace("null","");
                            }else {
                                exp_year = "";
                            }
                            JSONObject jsonObjectPos = jsonObject.getJSONObject("job_expect");
                            if(jsonObjectPos.length()>2){
                                String uej_position = jsonObjectPos.getString("uej_position").replace("null","");
                                time_type_user = jsonObjectPos.getString("uej_time_type").replace("null","");
                                JSONArray jsonArray = new JSONArray(uej_position);
                                for(int j = 0;j<jsonArray.length();j++){
                                    JSONObject jsonObject1 = jsonArray.getJSONObject(j);
                                    pos_name = jsonObject1.getString("pos_name").replace("null","");
                                    pos_value = jsonObject1.getString("pos_value").replace("null","");
                                }
                            }
                            String user_id = jsonObject.getString("upb_user_id").replace("null","");
                            String avatar_url = jsonObject.getString("upb_avatar_url").replace("null","");
                            String address = jsonObject.getString("upb_address").replace("null","");
                            String update_time = jsonObject.getString("upb_updated_at").replace("null","");
                            String recruiter_id = jsonObject.getString("upb_user_id").replace("null","");
                            String candidate_is_saved = jsonObject.getString("candidate_is_saved").replace("null","");
                            mActivity.databaseHelper.insertARecruiter(first_name,last_name,avatar_url,address,pos_name
                                    ,pos_value,update_time,recruiter_id,user_id,exp_year,time_type_user,candidate_is_saved);
                        }
                        recruiterModels = mActivity.databaseHelper.getAllRecruiter();
                        if(recruiterModels.size()>0) {
                            for (int i = refreshValuessss; i < recruiterModels.size(); i++) {
                                logo.add(recruiterModels.get(i).getAvatar_url());
                                title.add(recruiterModels.get(i).getPos_value());
                                company.add(recruiterModels.get(i).getFirst_name());
                                time.add(recruiterModels.get(i).getUpdate_time());
                                other.add(mActivity.getString(R.string.trangcanhan));
                                location.add(recruiterModels.get(i).getAddress());
                                salary.add(recruiterModels.get(i).getExp_year());
                                if(recruiterModels.get(i).getTime_type().equals("2")){
                                    time_type.add(mActivity.getString(R.string.fulltime));
                                }else {
                                    time_type.add(mActivity.getString(R.string.parttime));
                                }
                                recruiter_id.add(recruiterModels.get(i).getUser_id());
                            }
                        }
                        ungVienQuanTamAdapter.notifyDataSetChanged();
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
}
