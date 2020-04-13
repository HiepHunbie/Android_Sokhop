package com.example.ev.SoKhop.Fragment;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ev.SoKhop.Adapter.QuanLyUngVienAdapter;
import com.example.ev.SoKhop.Api.HttpGetCandicateByStatus;
import com.example.ev.SoKhop.Api.core.API;
import com.example.ev.SoKhop.Api.core.HttpResponseCode;
import com.example.ev.SoKhop.Base.BaseFragment;
import com.example.ev.SoKhop.Model.QuanLyUngVienModel;
import com.example.ev.SoKhop.R;
import com.example.ev.SoKhop.Utils.Pref;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MSI on 10/25/2016.
 */

public class UngVienDaPhongVanFragment extends BaseFragment {
    private ListView listView;
    private ArrayList<String> logo = new ArrayList<>();;
    private ArrayList<String> title = new ArrayList<>();;
    private ArrayList<String> company = new ArrayList<>();;
    private ArrayList<String> time = new ArrayList<>();;
    private ArrayList<String> other = new ArrayList<>();;
    private ArrayList<String> location = new ArrayList<>();;
    private ArrayList<String> exp = new ArrayList<>();;
    private ArrayList<String> time_type = new ArrayList<>();;
    private ArrayList<String> recruiter_id = new ArrayList<>();
    private ArrayList<String> JobId = new ArrayList<>();
    private List<QuanLyUngVienModel> quanLyUngVienModels;
    private QuanLyUngVienAdapter customList;
    private int refreshValue = 1;
    private int refreshValuessss = -10;
    private String job_id_check;
    private String start_time = "";
    private String end_time = "";
    private String position_name = "";
    private String position_value = "";
    private String position_id = "";
    private String diff_time = "";
    private String uej_time_type = "";
    private String u_email = "";
    private String u_first_name = "";
    private String u_last_name = "";
    private String u_middle_name = "";
    private String u_full_name = "";
    private String u_phone = "";
    private String user_id = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_ungvien_daphongvan, container, false);
        mActivity.databaseHelper.deleteAllQuanLyUngVien();
        quanLyUngVienModels = new ArrayList<>();
        job_id_check = mActivity.p.getString(Pref.PREF_KEY_JOB_ID,null);
        getUngVien(p.getString(Pref.PREF_KEY_TOKEN,null),job_id_check,"3",2,"1");
        listView = (ListView) rootView.findViewById(R.id.listView);
        customList = new QuanLyUngVienAdapter(mActivity, logo, title, company,time, other, location, exp,time_type,recruiter_id);
        listView.setAdapter(customList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mActivity.p.putString(Pref.BOOLEAN_IS_ME,"1");
                mActivity.getUserProfile(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),recruiter_id.get(position),0,false);
                mActivity.getImageAlbums(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),recruiter_id.get(position),999);
                mActivity.getEducationInfo(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),recruiter_id.get(position),999);
                mActivity.getExpericesInfo(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),recruiter_id.get(position),999);
                mActivity.getPositionTimeline(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),recruiter_id.get(position));
                mActivity.getExpectJob(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),recruiter_id.get(position),999);
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
                        getUngVien(p.getString(Pref.PREF_KEY_TOKEN,null),job_id_check,"3",2,String.valueOf(refreshValue));
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
                    mActivity.selectItem(6);
                    return true;
                }
                return false;
            }
        } );
        return rootView;
    }

    public void getUngVien(String token, String job_ids, String status_ids, final int pos,String page){
        HttpGetCandicateByStatus api = new HttpGetCandicateByStatus(mActivity,token,job_ids,status_ids,page);
        api.request(new API.APIDelegate() {
            @Override
            public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                if (httpResponseCode == HttpResponseCode.SUCCESS) {
                    refreshValuessss+=10;
                    try {
                        JSONArray data = jsonResponse.getJSONArray("data");
                        for(int i = 0;i<data.length();i++) {
                            JSONObject jsonObject = data.getJSONObject(i);
                            JSONObject jsonObjectExp = jsonObject.getJSONObject("user_experiences");
                            JSONObject jsonObjectJobExpect = jsonObject.getJSONObject("job_expect");
                            JSONObject jsonObjectInfo = jsonObject.getJSONObject("user_information");
                            String upb_address = jsonObject.getString("upb_address").replace("null","");
                            String upb_avatar_url = jsonObject.getString("upb_avatar_url").replace("null","");
                            String canjob_test_result = jsonObject.getString("canjob_test_result").replace("null","");
                            String canjob_is_apply = jsonObject.getString("canjob_is_apply").replace("null","");
                            String canjob_result_status = jsonObject.getString("canjob_result_status").replace("null","");
                            String canjob_updated_at = jsonObject.getString("canjob_updated_at").replace("null","");
                            String canjob_candidate_id = jsonObject.getString("canjob_candidate_id").replace("null","");
                            String canjob_job_id = jsonObject.getString("canjob_job_id").replace("null","");
                            String canjob_test_score = jsonObject.getString("canjob_test_score").replace("null","");
                            String canjob_final_result = jsonObject.getString("canjob_final_result").replace("null","");
                            if(jsonObjectExp.length()>2){
                                user_id = jsonObjectExp.getString("user_id").replace("null","");
                                start_time = jsonObjectExp.getString("start_time").replace("null","");
                                end_time = jsonObjectExp.getString("end_time").replace("null","");
                                position_name = jsonObjectExp.getString("position_name").replace("null","");
                                position_value = jsonObjectExp.getString("position_value").replace("null","");
                                position_id = jsonObjectExp.getString("position_id").replace("null","");
                                diff_time = jsonObjectExp.getString("diff_time").replace("null","");
                                uej_time_type = jsonObjectJobExpect.getString("uej_time_type").replace("null","");
                            }

                            if(jsonObjectInfo.length()>2) {
                                u_email = jsonObjectInfo.getString("u_email").replace("null", "");
                                u_first_name = jsonObjectInfo.getString("u_first_name").replace("null", "");
                                u_last_name = jsonObjectInfo.getString("u_last_name").replace("null", "");
                                u_middle_name = jsonObjectInfo.getString("u_middle_name").replace("null", "");
                                u_full_name = jsonObjectInfo.getString("u_full_name").replace("null", "");
                                u_phone = jsonObjectInfo.getString("u_phone").replace("null", "");
                            }

                            mActivity.databaseHelper.insertAQuanLyUngVien(upb_address,upb_avatar_url,canjob_test_result,canjob_is_apply,
                                    canjob_result_status,canjob_updated_at,canjob_candidate_id,canjob_job_id,canjob_test_score,
                                    user_id,start_time,end_time,position_name,position_value,position_id,diff_time,uej_time_type,u_email,u_first_name,
                                    u_last_name,u_middle_name,u_full_name,u_phone,canjob_final_result);
                        }
                        quanLyUngVienModels.clear();
                        quanLyUngVienModels = mActivity.databaseHelper.getAllQuanLyUngVien();
                        if(quanLyUngVienModels.size()>0) {
                            for (int i = refreshValuessss; i < quanLyUngVienModels.size(); i++) {
                                logo.add(quanLyUngVienModels.get(i).getUpb_avatar_url());
                                title.add(quanLyUngVienModels.get(i).getPosition_value());
                                company.add(quanLyUngVienModels.get(i).getU_full_name());
                                time.add(quanLyUngVienModels.get(i).getCanjob_updated_at());
                                if(quanLyUngVienModels.get(i).getCanjob_test_result().equals("2")){
                                    other.add(quanLyUngVienModels.get(i).getCanjob_test_score()+getString(R.string.dat));
                                }else {
                                    other.add(quanLyUngVienModels.get(i).getCanjob_test_score()+getString(R.string.khongdat));
                                }
                                location.add(quanLyUngVienModels.get(i).getUpb_address());
                                exp.add(quanLyUngVienModels.get(i).getDiff_time());
                                if(quanLyUngVienModels.get(i).getUej_time_type().equals("2")){
                                    time_type.add(mActivity.getString(R.string.fulltime));
                                }else {
                                    time_type.add(mActivity.getString(R.string.parttime));
                                }
                                recruiter_id.add(quanLyUngVienModels.get(i).getCanjob_candidate_id());
                            }
                        }
                        customList.notifyDataSetChanged();
//                        QuanLyUngVienFragment.selectItem(pos);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }else {
                }
            }
        });
    }
}
