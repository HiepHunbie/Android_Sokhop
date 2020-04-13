package com.example.ev.SoKhop.Fragment;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ev.SoKhop.Adapter.SearchJobAdapter;
import com.example.ev.SoKhop.Adapter.SearchUserAdapter;
import com.example.ev.SoKhop.Api.HttpGetSearchJobs;
import com.example.ev.SoKhop.Api.core.API;
import com.example.ev.SoKhop.Api.core.HttpResponseCode;
import com.example.ev.SoKhop.Base.BaseFragment;
import com.example.ev.SoKhop.Model.RecruiterModel;
import com.example.ev.SoKhop.Model.RecruitmentModel;
import com.example.ev.SoKhop.R;
import com.example.ev.SoKhop.Utils.Pref;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MSI on 10/27/2016.
 */

public class SearchJobFragment extends BaseFragment {

    private LinearLayout layout_search,layout_item_search;
    private ImageView imgSearch;
    private ListView listView;
    private List<RecruitmentModel> recruitmentModels ;
    private SearchJobAdapter customList;
    private SearchUserAdapter searchUserAdapter;
    private ArrayList<String> logo= new ArrayList<>();;
    private ArrayList<String> title= new ArrayList<>();;
    private ArrayList<String> company= new ArrayList<>();;
    private ArrayList<String> time= new ArrayList<>();;
    private ArrayList<String> quantam= new ArrayList<>();;
    private ArrayList<String> location= new ArrayList<>();;
    private ArrayList<String> salary= new ArrayList<>();;
    private ArrayList<String> time_type= new ArrayList<>();;
    private ArrayList<String> image= new ArrayList<>();;
    private ArrayList<String> jobs_id= new ArrayList<>();;
    private ArrayList<String> recruiter_id = new ArrayList<>();
    private List<RecruiterModel> recruiterModels ;

    private RelativeLayout layout_SearchTitle,layout_SearchPos,layout_SearchTimeType,layout_SearchLocation,layout_SearchSalary;
    private TextView txtSearchTitle,txtSearchPos,txtSearchTimeType,txtSearchLocation,txtSearchSalary;
    private ImageView imgDelSearchTitle,imgDelSearchPos,imgDelSearchTimeType,imgDelSearchLocation,imgDelSearchSalary;

    private String jca_name,jca_value,jca_tags,jca_description,jca_created_at,jca_updated_at,lang_name,lang_code,lang_created_at,lang_updated_at;
    private String jca_id,lang_id;
    private String owner_id = "";
    private String owner_avatar = "";
    private String owner_name = "";
    private String owner_website = "";
    private String owner_phone = "";
    private int refreshValue = 1;
    private int refreshValuessss = -10;
    private String province_id,position_id,job_time_type_id,job_salary,ques_get,position_name,province_name,job_time_type_name,exp_get,skill_get;
    private String first_name,last_name,full_name,exp_year,pos_name,pos_value,time_type_user;
    private boolean isHasItemSearch = false;
    private boolean isSearchTitle,isSearchPos,isSearchTimeType,isSearchLocation,isSearchSalary;

    public SearchJobFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        recruitmentModels = new ArrayList<>();
        recruiterModels = new ArrayList<>();
        mActivity.databaseHelper.deleteAllRecruitment();
        mActivity.databaseHelper.deleteAllRecruiter();
        mActivity.p.putString(Pref.PREF_KEY_VIEW_NEWS_BEFORE,"0");
         province_id = p.getString(Pref.TYPE_SEARCH_LOCATION_ID,null);
         position_id = p.getString(Pref.TYPE_SEARCH_POS_ID,null);
         job_time_type_id = p.getString(Pref.TYPE_SEARCH_TIME_TYPE_ID,null);
         job_salary = p.getString(Pref.TYPE_SEARCH_SALARY,null);
         ques_get = p.getString(Pref.TYPE_SEARCH_TITLE,null);
         position_name = p.getString(Pref.TYPE_SEARCH_POS,null);
         province_name = p.getString(Pref.TYPE_SEARCH_LOCATION,null);
         job_time_type_name = p.getString(Pref.TYPE_SEARCH_TIME_TYPE,null);
         exp_get = p.getString(Pref.TYPE_SEARCH_EXP,null);
        skill_get = p.getString(Pref.TYPE_SEARCH_SKILL,null);
        listView = (ListView)rootView.findViewById(R.id.listView);
        imgSearch = (ImageView)rootView.findViewById(R.id.imgSearch);
        layout_search = (LinearLayout) rootView.findViewById(R.id.layout_search);
        layout_item_search = (LinearLayout) rootView.findViewById(R.id.layout_item_search);

        layout_SearchTitle = (RelativeLayout) rootView.findViewById(R.id.layout_SearchTitle);
        layout_SearchPos = (RelativeLayout) rootView.findViewById(R.id.layout_SearchPos);
        layout_SearchTimeType = (RelativeLayout) rootView.findViewById(R.id.layout_SearchTimeType);
        layout_SearchLocation = (RelativeLayout) rootView.findViewById(R.id.layout_SearchLocation);
        layout_SearchSalary = (RelativeLayout) rootView.findViewById(R.id.layout_SearchSalary);

        imgDelSearchTitle = (ImageView)rootView.findViewById(R.id.imgDelSearchTitle);
        imgDelSearchPos = (ImageView)rootView.findViewById(R.id.imgDelSearchPos);
        imgDelSearchTimeType = (ImageView)rootView.findViewById(R.id.imgDelSearchTimeType);
        imgDelSearchLocation = (ImageView)rootView.findViewById(R.id.imgDelSearchLocation);
        imgDelSearchSalary = (ImageView)rootView.findViewById(R.id.imgDelSearchSalary);

        txtSearchTitle = (TextView)rootView.findViewById(R.id.txtSearchTitle);
        txtSearchPos = (TextView)rootView.findViewById(R.id.txtSearchPos);
        txtSearchTimeType = (TextView)rootView.findViewById(R.id.txtSearchTimeType);
        txtSearchLocation = (TextView)rootView.findViewById(R.id.txtSearchLocation);
        txtSearchSalary = (TextView)rootView.findViewById(R.id.txtSearchSalary);

        imgSearch.setVisibility(View.VISIBLE);
        layout_search.setVisibility(View.GONE);
//                showJobSearch(recruitmentModels);
                if(ques_get!=null&&ques_get.length()>0){
                    layout_SearchTitle.setVisibility(View.VISIBLE);
                    txtSearchTitle.setText(ques_get);
                    isSearchTitle = true;
                }else {
                    layout_SearchTitle.setVisibility(View.GONE);
                    isSearchTitle = false;
                }
                if(position_name!=null&&position_name.length()>0){
                    layout_SearchPos.setVisibility(View.VISIBLE);
                    txtSearchPos.setText(position_name);
                    isSearchPos = true;
                }else {
                    layout_SearchPos.setVisibility(View.GONE);
                    isSearchPos = false;
                }
                if(job_time_type_name!=null&&job_time_type_name.length()>0){
                    layout_SearchTimeType.setVisibility(View.VISIBLE);
                    txtSearchTimeType.setText(job_time_type_name);
                    isSearchTimeType = true;
                }else {
                    layout_SearchTimeType.setVisibility(View.GONE);
                    isSearchTimeType = false;
                }
                if(province_name!=null&&province_name.length()>0){
                    layout_SearchLocation.setVisibility(View.VISIBLE);
                    txtSearchLocation.setText(province_name);
                    isSearchLocation = true;
                }else {
                    layout_SearchLocation.setVisibility(View.GONE);
                    isSearchLocation = false;
                }
                if(job_salary!=null&&job_salary.length()>0){
                    layout_SearchSalary.setVisibility(View.VISIBLE);
                    txtSearchSalary.setText(job_salary);
                    isSearchSalary = true;
                }else {
                    layout_SearchSalary.setVisibility(View.GONE);
                    job_salary ="";
                    isSearchSalary = false;
                }
        if(!isSearchTitle&&!isSearchPos&&!isSearchTimeType&&!isSearchLocation&&!isSearchSalary){
            layout_item_search.setVisibility(View.GONE);
        }else {
            layout_item_search.setVisibility(View.VISIBLE);
        }
                getJobsSearch(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),province_id,position_id,job_time_type_id,job_salary,ques_get,String.valueOf(refreshValue),false);
                imgDelSearchTitle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        layout_SearchTitle.setVisibility(View.GONE);
                        ques_get = "";
                        p.putString(Pref.TYPE_SEARCH_TITLE,"");
                        getJobsSearch(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),province_id,position_id,job_time_type_id,job_salary,ques_get,String.valueOf(refreshValue),true);
                    }
                });
                imgDelSearchPos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        layout_SearchPos.setVisibility(View.GONE);
                        position_id = "";
                        p.putString(Pref.TYPE_SEARCH_POS,"");
                        p.putString(Pref.TYPE_SEARCH_POS_ID,"");
                        getJobsSearch(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),province_id,position_id,job_time_type_id,job_salary,ques_get,String.valueOf(refreshValue),true);
                    }
                });
                imgDelSearchLocation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        layout_SearchLocation.setVisibility(View.GONE);
                        province_id = "";
                        p.putString(Pref.TYPE_SEARCH_LOCATION,"");
                        p.putString(Pref.TYPE_SEARCH_LOCATION_ID,"");
                        getJobsSearch(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),province_id,position_id,job_time_type_id,job_salary,ques_get,String.valueOf(refreshValue),true);
                    }
                });
                imgDelSearchTimeType.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        layout_SearchTimeType.setVisibility(View.GONE);
                        job_time_type_id = "";
                        p.putString(Pref.TYPE_SEARCH_TIME_TYPE,"");
                        p.putString(Pref.TYPE_SEARCH_TIME_TYPE_ID,"");
                        getJobsSearch(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),province_id,position_id,job_time_type_id,job_salary,ques_get,String.valueOf(refreshValue),true);
                    }
                });
                imgDelSearchSalary.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        layout_SearchSalary.setVisibility(View.GONE);
                        job_salary = "";
                        p.putString(Pref.TYPE_SEARCH_SALARY,"");
                        getJobsSearch(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),province_id,position_id,job_time_type_id,job_salary,ques_get,String.valueOf(refreshValue),true);
                    }
                });

        listView.setFooterDividersEnabled(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mActivity.p.putString(Pref.PREF_KEY_JOB_ID,jobs_id.get(position));
                mActivity.selectItem(20);
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
                        getJobsSearch(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),province_id,position_id,job_time_type_id,job_salary,ques_get,String.valueOf(refreshValue),false);
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

    private void showJobSearch(List<RecruitmentModel> recruitmentModels){
        if(recruitmentModels.size()>0) {
            for (int j = refreshValuessss; j < recruitmentModels.size(); j++) {
                logo.add(recruitmentModels.get(j).getOwner_avatar());
                title.add(recruitmentModels.get(j).getJob_title());
                company.add(recruitmentModels.get(j).getJob_title());
                time.add(recruitmentModels.get(j).getJob_updated_at());
                salary.add(recruitmentModels.get(j).getJob_salary());
                quantam.add(recruitmentModels.get(j).getIs_user_saved());
                try {
                    JSONArray jsonArrayLoca = new JSONArray(recruitmentModels.get(j).getJob_location());
                    if(jsonArrayLoca.length()>0){
                        JSONObject jsonObject = jsonArrayLoca.getJSONObject(0);
                        location.add(jsonObject.getString("province_value"));
                    }else {
                        location.add("");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(recruitmentModels.get(j).getJob_time_type().equals("1")){
                    time_type.add(mActivity.getString(R.string.parttime));
                }else if(recruitmentModels.get(j).getJob_time_type().equals("2")){
                    time_type.add(mActivity.getString(R.string.fulltime));
                }else {
                    time_type.add("");
                }
                jobs_id.add(recruitmentModels.get(j).getJob_id());
            }
            imgSearch.setVisibility(View.GONE);
            layout_search.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public void getJobsSearch(String token, String province_id, String position_id, String job_time_type, String job_salary, String ques, String page, final boolean isRefresh){
        HttpGetSearchJobs api = new HttpGetSearchJobs(mActivity,token ,province_id,position_id,job_time_type,job_salary,ques,page);
        api.request(new API.APIDelegate() {
            @Override
            public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                if (httpResponseCode == HttpResponseCode.SUCCESS) {
                    refreshValuessss +=10;
                    try {
                        JSONArray data = jsonResponse.getJSONArray("data");
                        for(int i = 0;i<data.length();i++){
                            JSONObject jsonObject = data.getJSONObject(i);
                            JSONArray job_categories = jsonObject.getJSONArray("job_categories");
                            for (int j = 0; j < job_categories.length(); j++) {
                                JSONObject jsonObject1 = job_categories.getJSONObject(j);
                                jca_id = jsonObject1.getString("jca_id");
                                jca_name = jsonObject1.getString("jca_name");
                                jca_value = jsonObject1.getString("jca_value");
                                jca_tags = jsonObject1.getString("jca_tags");
                                jca_description = jsonObject1.getString("jca_description");
                                jca_created_at = jsonObject1.getString("jca_created_at");
                                jca_updated_at = jsonObject1.getString("jca_updated_at");
                            }
                            JSONArray job_languages = jsonObject.getJSONArray("job_languages");
                            for (int k = 0; k < job_languages.length(); k++) {
                                JSONObject jsonObject1 = job_languages.getJSONObject(k);
                                lang_id = jsonObject1.getString("lang_id");
                                lang_name = jsonObject1.getString("lang_name");
                                lang_code = jsonObject1.getString("lang_code");
                                lang_created_at = jsonObject1.getString("lang_created_at");
                                lang_updated_at = jsonObject1.getString("lang_updated_at");
                            }
                            JSONArray job_exclude_condition = jsonObject.getJSONArray("job_exclude_condition");
                            JSONArray job_require_condition = jsonObject.getJSONArray("job_require_condition");
                            JSONArray job_location = jsonObject.getJSONArray("job_location");
                            JSONObject job_position = jsonObject.getJSONObject("job_position");
//                           this.job_allowance = job_allowance;

                            String job_id = jsonObject.getString("job_id").replace("null", "");
                            String job_company_id = jsonObject.getString("job_company_id").replace("null", "");
                            String job_owner_id = jsonObject.getString("job_owner_id").replace("null", "");
                            String job_position_id = jsonObject.getString("job_position_id").replace("null", "");
                            String pos_id = job_position.getString("pos_id").replace("null", "");
                            String job_quantity = jsonObject.getString("job_quantity").replace("null", "");
                            String job_time_type = jsonObject.getString("job_time_type").replace("null", "");
                            String job_range_salary_id = jsonObject.getString("job_range_salary_id").replace("null", "");
                            String job_gender = jsonObject.getString("job_gender").replace("null", "");
                            String job_range_allowance_id = jsonObject.getString("job_range_allowance_id").replace("null", "");
                            String job_range_bonus_id = jsonObject.getString("job_range_bonus_id").replace("null", "");
                            String job_status = jsonObject.getString("job_status").replace("null", "");
                            String job_isactive = jsonObject.getString("job_isactive").replace("null", "");
                            String job_title = jsonObject.getString("job_title").replace("null", "");
                            String pos_name = job_position.getString("pos_name").replace("null", "");
                            String pos_value = job_position.getString("pos_value").replace("null", "");
                            String job_views_number = jsonObject.getString("job_views_number").replace("null", "");
                            String job_apply_number = jsonObject.getString("job_apply_number").replace("null", "");
                            String job_allowance = jsonObject.getString("job_allowance").replace("null", "");
                            String job_bonus = jsonObject.getString("job_bonus").replace("null", "");
                            String job_extra_desc = jsonObject.getString("job_extra_desc").replace("null", "");
                            String job_expired_time = jsonObject.getString("job_expired_time").replace("null", "");
                            String job_language_profile = jsonObject.getString("job_language_profile").replace("null", "");
                            String job_album_img_id = jsonObject.getString("job_album_img_id").replace("null", "");
                            String job_album_contract_id = jsonObject.getString("job_album_contract_id").replace("null", "");
                            String job_level_id = jsonObject.getString("job_level_id").replace("null", "");
                            String job_salary = jsonObject.getString("job_salary").replace("null", "");
                            String job_work_location = jsonObject.getString("job_work_location").replace("null", "");
                            String job_description = jsonObject.getString("job_description").replace("null", "");
                            String job_skills = jsonObject.getString("job_skills").replace("null", "");
                            String job_year_exps = jsonObject.getString("job_year_exps").replace("null", "");
                            String job_created_at = jsonObject.getString("job_created_at").replace("null", "");
                            String job_updated_at = jsonObject.getString("job_updated_at").replace("null", "");
                            String job_welfare = "";
                            String job_contact = jsonObject.getString("job_contact").replace("null","");
                            JSONObject jsonObjectOwner = jsonObject.getJSONObject("owner_infor");
                            if(jsonObjectOwner.length()>2){
                                owner_id = jsonObjectOwner.getString("owner_id").replace("null","");
                                owner_avatar = jsonObjectOwner.getString("owner_avatar").replace("null","");
                                owner_name = jsonObjectOwner.getString("owner_name").replace("null","");
                                owner_website = jsonObjectOwner.getString("owner_website").replace("null","");
                                owner_phone = jsonObjectOwner.getString("owner_phone").replace("null","");
                            }
                            String is_user_saved = jsonObject.getString("is_user_saved");
                            String is_user_apply = "";
                            String number_matching = "";
//                            String job_exclude_condition = jsonObject.getString("");
//                            String job_require_condition = jsonObject.getString("");
//                            String job_location = jsonObject.getString("");
                            mActivity.databaseHelper.insertARecruitment(job_id, job_company_id, job_owner_id, jca_id, job_position_id, pos_id, job_quantity,
                                    job_time_type, job_range_salary_id, job_gender, job_range_allowance_id, job_range_bonus_id, job_status, job_isactive,
                                    lang_id, job_title, jca_name, jca_value, jca_tags, jca_description, jca_created_at, jca_updated_at
                                    , pos_name, pos_value, job_views_number, job_apply_number, job_level_id, job_salary, job_work_location
                                    , job_description, job_allowance, job_bonus, job_extra_desc, job_expired_time, job_language_profile, job_album_img_id
                                    , job_album_contract_id, job_skills, job_year_exps, job_created_at, job_updated_at, lang_name, lang_code
                                    , lang_created_at, lang_updated_at, job_exclude_condition.toString(), job_require_condition.toString(), job_location.toString(), job_welfare,job_contact
                                    ,owner_id,owner_avatar,owner_name,owner_website,owner_phone,is_user_saved,number_matching,is_user_apply);
                        }
                        recruitmentModels = mActivity.databaseHelper.getAllRecruitment();
                        if(recruitmentModels.size()>0) {
                            for (int j = refreshValuessss; j < recruitmentModels.size(); j++) {
                                logo.add(recruitmentModels.get(j).getOwner_avatar());
                                title.add(recruitmentModels.get(j).getJob_title());
                                company.add(recruitmentModels.get(j).getOwner_name());
                                time.add(recruitmentModels.get(j).getJob_updated_at());
                                salary.add(recruitmentModels.get(j).getJob_salary());
                                quantam.add(recruitmentModels.get(j).getIs_user_saved());
                                try {
                                    JSONArray jsonArrayLoca = new JSONArray(recruitmentModels.get(j).getJob_location());
                                    if(jsonArrayLoca.length()>0){
                                        JSONObject jsonObject = jsonArrayLoca.getJSONObject(0);
                                        location.add(jsonObject.getString("province_value"));
                                    }else {
                                        location.add("");
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                if(recruitmentModels.get(j).getJob_time_type().equals("1")){
                                    time_type.add(mActivity.getString(R.string.parttime));
                                }else if(recruitmentModels.get(j).getJob_time_type().equals("2")){
                                    time_type.add(mActivity.getString(R.string.fulltime));
                                }else {
                                    time_type.add("");
                                }
                                jobs_id.add(recruitmentModels.get(j).getJob_id());
                            }
                        }
                        imgSearch.setVisibility(View.GONE);
                        layout_search.setVisibility(View.VISIBLE);
                            if(refreshValuessss==0){
                                customList = new SearchJobAdapter(mActivity, logo, title, company,time, quantam,location,salary,time_type,image,jobs_id);
                                listView.setAdapter(customList);
                            }else {
                                customList.notifyDataSetChanged();
                            }

                        if(isRefresh){
                            mActivity.selectItem(23);
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
