package com.example.ev.SoKhop.Fragment;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ev.SoKhop.Adapter.SearchUserAdapter;
import com.example.ev.SoKhop.Api.HttpGetSearchAccount;
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
 * Created by MSI on 11/3/2016.
 */

public class SearchAccountFragment extends BaseFragment {

    private LinearLayout layout_search;
    private ImageView imgSearch;
    private ListView listView;
    private SearchUserAdapter searchUserAdapter;
    private ArrayList<String> logo= new ArrayList<>();;
    private ArrayList<String> title= new ArrayList<>();;
    private ArrayList<String> company= new ArrayList<>();;
    private ArrayList<String> time= new ArrayList<>();;
    private ArrayList<String> quantam= new ArrayList<>();;
    private ArrayList<String> location= new ArrayList<>();;
    private ArrayList<String> salary= new ArrayList<>();;
    private ArrayList<String> time_type= new ArrayList<>();;
    private ArrayList<String> recruiter_id = new ArrayList<>();
    private List<RecruiterModel> recruiterModels ;

    private RelativeLayout layout_SearchTitle,layout_SearchPos,layout_SearchTimeType,layout_SearchLocation,layout_SearchSalary;
    private TextView txtSearchTitle,txtSearchPos,txtSearchTimeType,txtSearchLocation,txtSearchSalary;
    private ImageView imgDelSearchTitle,imgDelSearchPos,imgDelSearchTimeType,imgDelSearchLocation,imgDelSearchSalary;

    private int refreshValue = 1;
    private int refreshValuessss = -10;
    private String province_id,position_id,job_time_type_id,job_salary,ques_get,position_name,province_name,job_time_type_name,exp_get,skill_get;
    private String first_name = "";
    private String last_name = "";
    private String full_name = "";
    private String exp_year = "";
    private String pos_name = "";
    private String pos_value = "";
    private String time_type_user = "";
    public SearchAccountFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_search_account, container, false);
        recruiterModels = new ArrayList<>();
        mActivity.databaseHelper.deleteAllRecruiter();

        ques_get = p.getString(Pref.TYPE_SEARCH_TITLE,null);
        listView = (ListView)rootView.findViewById(R.id.listView);
        imgSearch = (ImageView)rootView.findViewById(R.id.imgSearch);
        layout_search = (LinearLayout) rootView.findViewById(R.id.layout_search);

        imgSearch.setVisibility(View.VISIBLE);
        layout_search.setVisibility(View.GONE);
        getAccountSearch(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),ques_get,false);
        listView.setFooterDividersEnabled(true);
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
                        getAccountSearch(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),ques_get,false);
                    }
                }
            }
        });
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

    @Override
    public void onResume() {
        super.onResume();
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
        mActivity.showSearchItem(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                if (id == R.id.btnUpDown) {
                }
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public void getAccountSearch(String token, String ques,final boolean isRefresh){
        HttpGetSearchAccount api = new HttpGetSearchAccount(mActivity,token ,ques);
        api.request(new API.APIDelegate() {
            @Override
            public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                if (httpResponseCode == HttpResponseCode.SUCCESS) {
                    refreshValuessss +=10;
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
                                time.add("");
                                quantam.add(recruiterModels.get(i).getCandidate_is_saved());
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
                        imgSearch.setVisibility(View.GONE);
                        layout_search.setVisibility(View.VISIBLE);
                        if(refreshValuessss==0){
                            searchUserAdapter = new SearchUserAdapter(mActivity, logo, title, company,time, quantam,location,salary,time_type,recruiter_id);
                            listView.setAdapter(searchUserAdapter);
                        }else {
                            searchUserAdapter.notifyDataSetChanged();
                        }
                        if(isRefresh){
                            mActivity.selectItem(27);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }else {
                }
            }
        });
    }

}
