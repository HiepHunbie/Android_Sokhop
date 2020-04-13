package com.example.ev.SoKhop.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.ev.SoKhop.Adapter.UngVienQuanTamAdapter;
import com.example.ev.SoKhop.Api.HttpGetRecruiter;
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
 * Created by MSI on 10/13/2016.
 */

public class RecruitmentFragment extends BaseFragment {
    public Context mContext;
    private LinearLayout layout_quanly,layout_nhansu,layout_ungvienquantam;

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
    private boolean isNotshow = false;
    private ImageView imgUngvienShow ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recruitment, container, false);
        mContext= getActivity().getApplicationContext();
        mActivity.databaseHelper.deleteAllRecruiter();
        recruiterModels = new ArrayList<>();
        getRecruiter(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),mActivity.p.getString(Pref.USER_ID,null),"1","10");
        layout_quanly = (LinearLayout)rootView.findViewById(R.id.layout_quanly);
        layout_nhansu = (LinearLayout)rootView.findViewById(R.id.layout_nhansu);
        layout_ungvienquantam = (LinearLayout)rootView.findViewById(R.id.layout_ungvienquantam);
        imgUngvienShow = (ImageView)rootView.findViewById(R.id.imgUngvienShow);

        layout_ungvienquantam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNotshow){
                    isNotshow = false;
                    imgUngvienShow.setImageResource(R.drawable.ic_up);
                    listView.setVisibility(View.VISIBLE);
                }else {
                    isNotshow = true;
                    imgUngvienShow.setImageResource(R.drawable.ic_next);
                    listView.setVisibility(View.INVISIBLE);
                }
            }
        });
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
                        refreshValue+=1;
                        getRecruiter(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),mActivity.p.getString(Pref.USER_ID,null), String.valueOf(refreshValue),"10");
                    }
                }
            }
        });
        layout_quanly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.selectItem(17);
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
        mActivity.showItemToolBar(false, false, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                if (id == R.id.btnCreate) {
                }
            }
        });

        if(!isNotshow){
            listView.setVisibility(View.VISIBLE);
        }else {
            listView.setVisibility(View.INVISIBLE);
        }
        return rootView;
    }


    public void getRecruiter(String token,String recrui_id,String page,String limit){
        HttpGetRecruiter api = new HttpGetRecruiter(mActivity,token,recrui_id,page,limit);
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
                            String first_name = jsonObject.getString("first_name").replace("null","");
                            String last_name = jsonObject.getString("last_name").replace("null","");
                            String avatar_url = jsonObject.getString("avatar_url").replace("null","");
                            String address = jsonObject.getString("address").replace("null","");
                            String pos_name = jsonObject.getString("pos_name").replace("null","");
                            String pos_value = jsonObject.getString("pos_value").replace("null","");
                            String update_time = jsonObject.getString("update_time").replace("null","");
                            String recruiter_id = jsonObject.getString("recruiter_id").replace("null","");
                            String user_id = jsonObject.getString("user_id").replace("null","");
                            String exp_year = jsonObject.getString("exp_year").replace("null","");
                            String time_type = jsonObject.getString("time_type").replace("null","");
                            String candidate_is_saved = "";
                            mActivity.databaseHelper.insertARecruiter(first_name,last_name,avatar_url,address,pos_name
                                    ,pos_value,update_time,recruiter_id,user_id,exp_year,time_type,candidate_is_saved);
                        }
                        recruiterModels = mActivity.databaseHelper.getAllRecruiter();
                        if(recruiterModels.size()>0) {
                            for (int i = refreshValuessss; i < recruiterModels.size(); i++) {
                                logo.add(recruiterModels.get(i).getAvatar_url());
                                title.add(recruiterModels.get(i).getPos_value());
                                company.add(recruiterModels.get(i).getFirst_name()+" "+recruiterModels.get(i).getLast_name());
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
