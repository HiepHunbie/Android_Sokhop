package com.example.ev.SoKhop.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ev.SoKhop.Adapter.JobsAdapter;
import com.example.ev.SoKhop.Api.HttpGetSoKhopJob;
import com.example.ev.SoKhop.Api.core.API;
import com.example.ev.SoKhop.Api.core.HttpResponseCode;
import com.example.ev.SoKhop.Base.BaseFragment;
import com.example.ev.SoKhop.Model.RecruitmentModel;
import com.example.ev.SoKhop.R;
import com.example.ev.SoKhop.Utils.Pref;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MSI on 10/18/2016.
 */

public class SoKhopJobFragment extends BaseFragment {
    private ListView listView;
    private ArrayList<String> logo = new ArrayList<>();;
    private ArrayList<String> title = new ArrayList<>();;
    private ArrayList<String> company = new ArrayList<>();;
    private ArrayList<String> time = new ArrayList<>();;
    private ArrayList<String> other = new ArrayList<>();;
    private ArrayList<String> location = new ArrayList<>();;
    private ArrayList<String> salary = new ArrayList<>();;
    private ArrayList<String> time_type = new ArrayList<>();;
    private ArrayList<String> image = new ArrayList<>();;
    private ArrayList<String> job_id = new ArrayList<>();
    private JobsAdapter customList;
    private int refreshValue = 1;
    private int refreshValuessss = -10;
    private String jca_name,jca_value,jca_tags,jca_description,jca_created_at,jca_updated_at,lang_name,lang_code,lang_created_at,lang_updated_at;
    private String jca_id,lang_id;
    private List<RecruitmentModel> recruitmentModels ;
    private String owner_id = "";
    private String owner_avatar = "";
    private String owner_name = "";
    private String owner_website = "";
    private String owner_phone = "";
    private String job_album_img_id,job_album_contract_id,job_level_id,job_quantity,job_salary,job_work_location,job_description,job_skills,job_year_exps,job_created_at,job_updated_at,job_contact;
    private String job_time_type,job_allowance,job_language_profile,job_expired_time,job_extra_desc,job_bonus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_sokhop_job, container, false);
        mActivity.p.putString(Pref.JOB_TAB,"0");
        mActivity.databaseHelper.deleteAllRecruitment();
        recruitmentModels = new ArrayList<>();
        getSoKhopJob(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),mActivity.p.getString(Pref.USER_ID,null),String.valueOf(refreshValue));
        listView = (ListView)rootView.findViewById(R.id.listView);
        customList = new JobsAdapter(mActivity, logo, title, company,time, other,location,salary,time_type,image);
        listView.setAdapter(customList);
        listView.setFooterDividersEnabled(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mActivity.p.putString(Pref.PREF_KEY_JOB_ID,job_id.get(position));
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
                        getSoKhopJob(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),mActivity.p.getString(Pref.USER_ID,null),String.valueOf(refreshValue));
                    }
                }
            }
        });
        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        refreshValue = 1;
    }

    @Override
    public void onStop() {
        super.onStop();
        refreshValue = 1;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public static DraftRecruitmentFragment newInstance(String text) {

        DraftRecruitmentFragment f = new DraftRecruitmentFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }
    public void getSoKhopJob(String token,String user_ids,String id){
        HttpGetSoKhopJob api = new HttpGetSoKhopJob(mActivity,token,user_ids,id);
        api.request(new API.APIDelegate() {
            @Override
            public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                if (httpResponseCode == HttpResponseCode.SUCCESS) {
//                    mActivity.databaseHelper.deleteAllRecruitment();
                    refreshValuessss +=10;
                        try {
                            JSONArray data = jsonResponse.getJSONArray("data");
                            for (int i = 0; i < data.length(); i++) {
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
                                String job_company_id = "";
                                String job_owner_id = jsonObject.getString("job_owner_id").replace("null", "");
                                String job_position_id = jsonObject.getString("job_position_id").replace("null", "");
                                String pos_id = job_position.getString("pos_id").replace("null", "");
                                if(jsonObject.toString().contains("job_quantity")){
                                    job_quantity = jsonObject.getString("job_quantity").replace("null", "");
                                }else {
                                    job_quantity = "";
                                }
                                if(jsonObject.toString().contains("job_time_type")){
                                    job_time_type = jsonObject.getString("job_time_type").replace("null", "");
                                }else {
                                    job_time_type = "";
                                }
                                String job_range_salary_id = "";
                                String job_gender = jsonObject.getString("job_gender").replace("null", "");
                                String job_range_allowance_id = "";
                                String job_range_bonus_id = "";
                                String job_status = jsonObject.getString("job_status").replace("null", "");
                                String job_isactive = jsonObject.getString("job_isactive").replace("null", "");
                                String job_title = jsonObject.getString("job_title").replace("null", "");
                                String pos_name = job_position.getString("pos_name").replace("null", "");
                                String pos_value = job_position.getString("pos_value").replace("null", "");
                                String job_views_number = jsonObject.getString("job_views_number").replace("null", "");
                                String job_apply_number = jsonObject.getString("job_apply_number").replace("null", "");
                                if(jsonObject.toString().contains("job_allowance")){
                                    job_allowance = jsonObject.getString("job_allowance").replace("null", "");
                                }else {
                                    job_allowance = "";
                                }
                                if(jsonObject.toString().contains("job_bonus")){
                                    job_bonus = jsonObject.getString("job_bonus").replace("null", "");
                                }else {
                                    job_bonus = "";
                                }
                                if(jsonObject.toString().contains("job_extra_desc")){
                                    job_extra_desc = jsonObject.getString("job_extra_desc").replace("null", "");
                                }else {
                                    job_extra_desc = "";
                                }
                                if(jsonObject.toString().contains("job_expired_time")){
                                    job_expired_time = jsonObject.getString("job_expired_time").replace("null", "");
                                }else {
                                    job_expired_time = "";
                                }
                                if(jsonObject.toString().contains("job_language_profile")){
                                    job_language_profile = jsonObject.getString("job_language_profile").replace("null", "");
                                }else {
                                    job_language_profile = "";
                                }
                                if(jsonObject.toString().contains("job_album_img_id")){
                                    job_album_img_id = jsonObject.getString("job_album_img_id").replace("null", "");
                                }else {
                                    job_album_img_id = "";
                                }
                                if(jsonObject.toString().contains("job_album_contract_id")){
                                    job_album_contract_id = jsonObject.getString("job_album_contract_id").replace("null", "");
                                }else {
                                    job_album_contract_id = "";
                                }
                                if(jsonObject.toString().contains("job_level_id")){
                                    job_level_id = jsonObject.getString("job_level_id").replace("null", "");
                                }else {
                                    job_level_id = "";
                                }
                                if(jsonObject.toString().contains("job_salary")){
                                    job_salary = jsonObject.getString("job_salary").replace("null", "");
                                }else {
                                    job_salary = "";
                                }
                                if(jsonObject.toString().contains("job_work_location")){
                                    job_work_location = jsonObject.getString("job_work_location").replace("null", "");
                                }else {
                                    job_work_location = "";
                                }
                                if(jsonObject.toString().contains("job_description")){
                                    job_description = jsonObject.getString("job_description").replace("null", "");
                                }else {
                                    job_description = "";
                                }
                                if(jsonObject.toString().contains("job_skills")){
                                    job_skills = jsonObject.getString("job_skills").replace("null", "");
                                }else {
                                    job_skills = "";
                                }
                                if(jsonObject.toString().contains("job_year_exps")){
                                    job_year_exps = jsonObject.getString("job_year_exps").replace("null", "");
                                }else {
                                    job_year_exps = "";
                                }
                                if(jsonObject.toString().contains("job_created_at")){
                                    job_created_at = jsonObject.getString("job_created_at").replace("null", "");
                                }else {
                                    job_created_at = "";
                                }
                                if(jsonObject.toString().contains("job_updated_at")){
                                    job_updated_at = jsonObject.getString("job_updated_at").replace("null", "");
                                }else {
                                    job_updated_at = "";
                                }
                                if(jsonObject.toString().contains("job_contact")){
                                    job_contact = jsonObject.getString("job_contact").replace("null", "");
                                }else {
                                    job_contact = "";
                                }
                                String job_welfare = "";
                                JSONObject jsonObjectOwner = jsonObject.getJSONObject("owner_infor");
                                if(jsonObjectOwner.length()>2){
                                     owner_id = jsonObjectOwner.getString("owner_id").replace("null","");
                                     owner_avatar = jsonObjectOwner.getString("owner_avatar").replace("null","");
                                     owner_name = jsonObjectOwner.getString("owner_name").replace("null","");
                                     owner_website = jsonObjectOwner.getString("owner_website").replace("null","");
                                     owner_phone = jsonObjectOwner.getString("owner_phone").replace("null","");
                                }
                                String is_user_saved = "";
                                String number_matching = "";
                                String is_user_apply = "";
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
                            if (recruitmentModels.size() > 0) {
                                for (int j = refreshValuessss; j < recruitmentModels.size(); j++) {
                                    logo.add(recruitmentModels.get(j).getOwner_avatar());
                                    title.add(recruitmentModels.get(j).getJob_title());
                                    company.add(recruitmentModels.get(j).getOwner_name());
                                    time.add(recruitmentModels.get(j).getJob_updated_at());
//                                other.add(candidateJobModels.get(j).getCom_avatar_url());
//                                location.add(candidateJobModels.get(j).getCom_avatar_url());
                                    salary.add(recruitmentModels.get(j).getJob_salary());
//                                time_type.add(candidateJobModels.get(j).getCom_avatar_url());
                                    image.add("");

                                    try {
                                        JSONArray jsonArrayLoca = new JSONArray(recruitmentModels.get(j).getJob_location());
                                        if (jsonArrayLoca.length() > 0) {
                                            JSONObject jsonObject = jsonArrayLoca.getJSONObject(0);
                                            location.add(jsonObject.getString("province_value"));
                                        } else {
                                            location.add("");
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                    if (recruitmentModels.get(j).getJob_time_type().equals("1")) {
                                        other.add(mActivity.getString(R.string.phongvan));
                                    } else if (recruitmentModels.get(j).getJob_time_type().equals("2")) {
                                        other.add(mActivity.getString(R.string.lienhe));
                                    } else if (recruitmentModels.get(j).getJob_time_type().equals("3")) {
                                        other.add(mActivity.getString(R.string.trungtuyen));
                                    } else if (recruitmentModels.get(j).getJob_time_type().equals("4")) {
                                        other.add(mActivity.getString(R.string.khongtrungtuyen));
                                    } else {
                                        other.add(mActivity.getString(R.string.dangcho));
                                    }

                                    if (recruitmentModels.get(j).getJob_time_type().equals("1")) {
                                        time_type.add(mActivity.getString(R.string.parttime));
                                    } else if (recruitmentModels.get(j).getJob_time_type().equals("2")) {
                                        time_type.add(mActivity.getString(R.string.fulltime));
                                    } else {
                                        time_type.add("");
                                    }
                                    job_id.add(recruitmentModels.get(j).getJob_id());
                                }
                            }
                            customList.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                }else {
                }
            }
        });
    }

}
