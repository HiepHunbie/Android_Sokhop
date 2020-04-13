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
import com.example.ev.SoKhop.Adapter.OtherRecruitmentAdapter;
import com.example.ev.SoKhop.Api.HttpGetRecruitment;
import com.example.ev.SoKhop.Api.HttpUpdateStatusRecruitment;
import com.example.ev.SoKhop.Api.core.API;
import com.example.ev.SoKhop.Api.core.HttpResponseCode;
import com.example.ev.SoKhop.Base.BaseFragment;
import com.example.ev.SoKhop.Dialog.DialogCall;
import com.example.ev.SoKhop.Model.RecruitmentModel;
import com.example.ev.SoKhop.R;
import com.example.ev.SoKhop.Utils.Pref;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MSI on 10/17/2016.
 */

public class LookedRecruitmentFragment extends BaseFragment {
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
    private List<RecruitmentModel> recruitmentModels;
    private OtherRecruitmentAdapter customList;
    private String jca_name,jca_value,jca_tags,jca_description,jca_created_at,jca_updated_at,lang_name,lang_code,lang_created_at,lang_updated_at;
    private String jca_id,lang_id;
    private int refreshValue = 1;
    private int refreshValuessss = -10;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_looked_recruitment, container, false);
        mActivity.databaseHelper.deleteAllRecruitment();
        recruitmentModels = new ArrayList<>();
        getRecruitment(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),0,"3","1","10");

        listView = (SwipeMenuListView)rootView.findViewById(R.id.listView);
        customList = new OtherRecruitmentAdapter(mActivity, pos, places, typeWork,timeEdit, sokhop, luotxem, ungtuyen,ngayhethan);
        listView.setAdapter(customList);
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {

                SwipeMenuItem fix = new SwipeMenuItem(
                        mActivity);
                fix.setBackground(R.color.recruitment1);
                fix.setWidth((int) mActivity.getResources().getDimension(R.dimen._80sdp));
                fix.setTitleSize(15);
                fix.setTitleColor(Color.WHITE);
                fix.setTitle(getString(R.string.fix));
                menu.addMenuItem(fix);
                SwipeMenuItem unlock = new SwipeMenuItem(
                        mActivity);
                unlock.setBackground(R.color.recruitment2);
                unlock.setWidth((int) mActivity.getResources().getDimension(R.dimen._80sdp));
                unlock.setTitleSize(15);
                unlock.setTitleColor(Color.WHITE);
                unlock.setTitle(getString(R.string.unlock));
                menu.addMenuItem(unlock);
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        mActivity);
                deleteItem.setBackground(R.color.recruitment3);
                deleteItem.setWidth((int) mActivity.getResources().getDimension(R.dimen._80sdp));
                deleteItem.setTitle(getString(R.string.delete));
                deleteItem.setTitleSize(15);
                deleteItem.setTitleColor(Color.WHITE);
                menu.addMenuItem(deleteItem);
            }
        };
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        mActivity.p.putString(Pref.PREF_KEY_JOB_ID,JobId.get(position));
                        mActivity.p.putString(Pref.PREF_KEY_STATUS_ID,"3");
                        mActivity.selectItem(18);
                        break;
                    case 1:
                        int response = HttpUpdateStatusRecruitment.updateStatusRecruitment(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),JobId.get(position),"1",mActivity);
                        if(response == 200){
                            DialogCall.showResponse(mActivity, mActivity.getString(R.string.mokhoa_success), new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    int id = v.getId();
                                    if (id == R.id.btnOk) {
                                        QuanLyTinTuyenDungFragment.selectItem(2);
                                    }
                                }
                            });
                        }else {
                            DialogCall.showResponse(mActivity, mActivity.getString(R.string.mokhoa_error), new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    int id = v.getId();
                                    if (id == R.id.btnOk) {
                                    }
                                }
                            });
                        }
                        break;
                    case 2:
                        int responses = HttpUpdateStatusRecruitment.updateStatusRecruitment(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),JobId.get(position),"5",mActivity);
                        if(responses == 200){
                            DialogCall.showResponse(mActivity, mActivity.getString(R.string.delete_success), new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    int id = v.getId();
                                    if (id == R.id.btnOk) {
                                        QuanLyTinTuyenDungFragment.selectItem(2);
                                    }
                                }
                            });
                        }else {
                            DialogCall.showResponse(mActivity, mActivity.getString(R.string.delete_error), new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    int id = v.getId();
                                    if (id == R.id.btnOk) {
                                    }
                                }
                            });
                        }
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mActivity.p.putString(Pref.PREF_KEY_JOB_ID,JobId.get(position));
                mActivity.p.putString(Pref.TYPE_QUANLYTINTUYENDUNG_TAB,"2");
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
                        getRecruitment(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),0,"3", String.valueOf(refreshValue),"10");
                    }
                }
            }
        });
        listView.setMenuCreator(creator);
        return rootView;
    }
    public void getRecruitment(String token, final int posss,String status,String page,String limit){
        HttpGetRecruitment api = new HttpGetRecruitment(mActivity,token,status,page,limit);
        api.request(new API.APIDelegate() {
            @Override
            public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                if (httpResponseCode == HttpResponseCode.SUCCESS) {
//                    mActivity.databaseHelper.deleteAllRecruitment();
                    refreshValuessss +=10;
                    try {
                        JSONArray data = jsonResponse.getJSONArray("data");
                        for(int i = 0;i<data.length();i++){
                            JSONObject jsonObject = data.getJSONObject(i);
                            JSONArray job_categories = jsonObject.getJSONArray("job_categories");
                            for(int j = 0;j < job_categories.length();j++){
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
                            for(int k = 0;k < job_languages.length();k++){
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

                            String job_id = jsonObject.getString("job_id").replace("null","");
                            String job_company_id = jsonObject.getString("job_company_id").replace("null","");
                            String job_owner_id = jsonObject.getString("job_owner_id").replace("null","");
                            String job_position_id = jsonObject.getString("job_position_id").replace("null","");
                            String pos_id = job_position.getString("pos_id").replace("null","");
                            String job_quantity = jsonObject.getString("job_quantity").replace("null","");
                            String job_time_type = jsonObject.getString("job_time_type").replace("null","");
                            String job_range_salary_id = jsonObject.getString("job_range_salary_id").replace("null","");
                            String job_gender = jsonObject.getString("job_gender").replace("null","");
                            String job_range_allowance_id = jsonObject.getString("job_range_allowance_id").replace("null","");
                            String job_range_bonus_id = jsonObject.getString("job_range_bonus_id").replace("null","");
                            String job_status = jsonObject.getString("job_status").replace("null","");
                            String job_isactive = jsonObject.getString("job_isactive").replace("null","");
                            String job_title = jsonObject.getString("job_title").replace("null","");
                            String pos_name = job_position.getString("pos_name").replace("null","");
                            String pos_value = job_position.getString("pos_value").replace("null","");
                            String job_views_number = jsonObject.getString("job_views_number").replace("null","");
                            String job_apply_number = jsonObject.getString("job_apply_number").replace("null","");
                            String job_allowance = jsonObject.getString("job_allowance").replace("null","");
                            String job_bonus = jsonObject.getString("job_bonus").replace("null","");
                            String job_extra_desc = jsonObject.getString("job_extra_desc").replace("null","");
                            String job_expired_time = jsonObject.getString("job_expired_time").replace("null","");
                            String job_language_profile = jsonObject.getString("job_language_profile").replace("null","");
                            String job_album_img_id = jsonObject.getString("job_album_img_id").replace("null","");
                            String job_album_contract_id = jsonObject.getString("job_album_contract_id").replace("null","");
                            String job_level_id = jsonObject.getString("job_level_id").replace("null","");
                            String job_salary = jsonObject.getString("job_salary").replace("null","");
                            String job_work_location = jsonObject.getString("job_work_location").replace("null","");
                            String job_description = jsonObject.getString("job_description").replace("null","");
                            String job_skills = jsonObject.getString("job_skills").replace("null","");
                            String job_year_exps = jsonObject.getString("job_year_exps").replace("null","");
                            String job_created_at = jsonObject.getString("job_created_at").replace("null","");
                            String job_updated_at = jsonObject.getString("job_updated_at").replace("null","");
                            String job_welfare = jsonObject.getString("job_welfare").replace("null","");
                            String job_contact = jsonObject.getString("job_contact").replace("null","");
                            JSONObject jsonObjectOwner = jsonObject.getJSONObject("owner_infor");
                            String owner_id = jsonObjectOwner.getString("owner_id").replace("null","");
                            String owner_avatar = jsonObjectOwner.getString("owner_avatar").replace("null","");
                            String owner_name = jsonObjectOwner.getString("owner_name").replace("null","");
                            String owner_website = jsonObjectOwner.getString("owner_website").replace("null","");
                            String owner_phone = jsonObjectOwner.getString("owner_phone").replace("null","");
                            String is_user_saved = "";
                            String number_matching = jsonObject.getString("number_matching").replace("null","");
                            String is_user_apply = "";
//                            String job_exclude_condition = jsonObject.getString("");
//                            String job_require_condition = jsonObject.getString("");
//                            String job_location = jsonObject.getString("");
                            mActivity.databaseHelper.insertARecruitment( job_id, job_company_id, job_owner_id, jca_id, job_position_id, pos_id, job_quantity,
                                    job_time_type, job_range_salary_id, job_gender, job_range_allowance_id, job_range_bonus_id, job_status, job_isactive,
                                    lang_id, job_title, jca_name, jca_value, jca_tags, jca_description, jca_created_at, jca_updated_at
                                    , pos_name, pos_value, job_views_number, job_apply_number, job_level_id, job_salary, job_work_location
                                    , job_description, job_allowance, job_bonus, job_extra_desc, job_expired_time, job_language_profile, job_album_img_id
                                    , job_album_contract_id, job_skills, job_year_exps, job_created_at, job_updated_at, lang_name, lang_code
                                    , lang_created_at, lang_updated_at, job_exclude_condition.toString(), job_require_condition.toString(), job_location.toString(),job_welfare,job_contact
                                    ,owner_id,owner_avatar,owner_name,owner_website,owner_phone,is_user_saved,number_matching,is_user_apply);
                        }
                        recruitmentModels.clear();
                        recruitmentModels = mActivity.databaseHelper.getARecruitment(3);
                        if(recruitmentModels.size()>0) {
                            for (int i = refreshValuessss; i < recruitmentModels.size(); i++) {
                                pos.add(recruitmentModels.get(i).getJob_title());
                                try {
                                    JSONArray jsonArrayLoca = new JSONArray(recruitmentModels.get(i).getJob_location());
                                    if(jsonArrayLoca.length()>0){
                                        JSONObject jsonObject = jsonArrayLoca.getJSONObject(0);
                                        places.add(jsonObject.getString("province_value"));
                                    }else {
                                        places.add("");
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                if(recruitmentModels.get(i).getJob_time_type().equals("1")){
                                    typeWork.add(mActivity.getString(R.string.parttime));
                                }else if(recruitmentModels.get(i).getJob_time_type().equals("2")){
                                    typeWork.add(mActivity.getString(R.string.fulltime));
                                }else {
                                    typeWork.add("");
                                }
                                timeEdit.add(recruitmentModels.get(i).getJob_updated_at());
                                //// TODO: 10/17/2016 tạm thời để sokop = views_number
                                sokhop.add(recruitmentModels.get(i).getNumber_matching());
                                luotxem.add(recruitmentModels.get(i).getJob_views_number());
                                ungtuyen.add(recruitmentModels.get(i).getJob_apply_number());
                                ngayhethan.add(recruitmentModels.get(i).getJob_expired_time());
                                JobId.add(recruitmentModels.get(i).getJob_id());
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
