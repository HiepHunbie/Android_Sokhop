package com.example.ev.SoKhop.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ev.SoKhop.Adapter.JobsAdapter;
import com.example.ev.SoKhop.Api.HttpGetAllJobSaved;
import com.example.ev.SoKhop.Api.core.API;
import com.example.ev.SoKhop.Api.core.HttpResponseCode;
import com.example.ev.SoKhop.Base.BaseFragment;
import com.example.ev.SoKhop.Model.CandidateJobModel;
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

public class SavedJobFragment extends BaseFragment {
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
    private ArrayList<String> Job_id = new ArrayList<>();;
    private List<CandidateJobModel> candidateJobModels ;
    private JobsAdapter customList;
    private int refreshValue = 1;
    private int refreshValuessss = -10;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_quantam_job, container, false);
        mActivity.p.putString(Pref.JOB_TAB,"1");
        mActivity.databaseHelper.deleteAllRecruitment();
        candidateJobModels = new ArrayList<>();
        getSavedJob(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),String.valueOf(refreshValue));
        listView = (ListView)rootView.findViewById(R.id.listView);
        customList = new JobsAdapter(mActivity, logo, title, company,time, other,location,salary,time_type,image);
        listView.setAdapter(customList);
        listView.setFooterDividersEnabled(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mActivity.p.putString(Pref.PREF_KEY_JOB_ID,Job_id.get(position));
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
                        getSavedJob(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),String.valueOf(refreshValue));
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
    public void getSavedJob(String token,String id){
        HttpGetAllJobSaved api = new HttpGetAllJobSaved(mActivity,token,id);
        api.request(new API.APIDelegate() {
            @Override
            public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                if (httpResponseCode == HttpResponseCode.SUCCESS) {
                    Log.d("jsonResponsess","ss"+jsonResponse);
                    mActivity.databaseHelper.deleteAllCandicateJob();
                    refreshValuessss +=10;
                    try {
                        JSONArray data = jsonResponse.getJSONArray("data");
                        for(int i = 0;i<data.length();i++){
                            JSONObject jsonObject = data.getJSONObject(i);
                            String job_position = jsonObject.getString("job_position").replace("null","");
                            String job_salary = jsonObject.getString("job_salary").replace("null","");
                            String com_avatar_url = jsonObject.getString("com_avatar_url").replace("null","");
                            String job_work_location = jsonObject.getString("job_work_location").replace("null","");
                            String job_time_type = jsonObject.getString("job_time_type").replace("null","");
                            String com_name = jsonObject.getString("com_name").replace("null","");
                            String canjob_candidate_id = jsonObject.getString("canjob_candidate_id").replace("null","");
                            String canjob_result_status = jsonObject.getString("canjob_result_status").replace("null","");
                            String canjob_is_apply = jsonObject.getString("canjob_is_apply").replace("null","");
                            String job_id = jsonObject.getString("job_id").replace("null","");
                            String job_title = jsonObject.getString("job_title").replace("null","");
                            String job_updated_at = jsonObject.getString("job_updated_at").replace("null","");
                            String is_user_saved = "";
                            mActivity.databaseHelper.insertACandidateJob( job_position, job_salary, com_avatar_url, job_work_location,
                                    job_time_type, com_name, canjob_candidate_id, canjob_result_status, canjob_is_apply,job_id,job_title,job_updated_at,is_user_saved);
                        }
                        candidateJobModels.clear();
                        candidateJobModels = mActivity.databaseHelper.getAllCandidateJob();
                        if(candidateJobModels.size()>0) {
                            for (int j = refreshValuessss; j < candidateJobModels.size(); j++) {
                                logo.add(candidateJobModels.get(j).getCom_avatar_url());
                                title.add(candidateJobModels.get(j).getJob_title());
                                company.add(candidateJobModels.get(j).getCom_name());
                                time.add(candidateJobModels.get(j).getJob_updated_at());
//                                other.add(candidateJobModels.get(j).getCom_avatar_url());
//                                location.add(candidateJobModels.get(j).getCom_avatar_url());
                                salary.add(candidateJobModels.get(j).getJob_salary());
//                                time_type.add(candidateJobModels.get(j).getCom_avatar_url());
                                image.add("");

                                String loca = "";
                                try {
                                    if(candidateJobModels.get(j).getJob_work_location().length()>6){
                                        JSONArray jsonArrayLoca = new JSONArray(candidateJobModels.get(j).getJob_work_location());
                                        if(jsonArrayLoca.length()>0){
                                            JSONObject jsonObject = jsonArrayLoca.getJSONObject(0);
                                            loca = jsonObject.getString("province_value");
                                        }
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                location.add(loca);
                                if(candidateJobModels.get(j).getJob_time_type().equals("1")){
                                    other.add(mActivity.getString(R.string.phongvan));
                                }else if(candidateJobModels.get(j).getJob_time_type().equals("2")){
                                    other.add(mActivity.getString(R.string.lienhe));
                                }else if(candidateJobModels.get(j).getJob_time_type().equals("3")){
                                    other.add(mActivity.getString(R.string.trungtuyen));
                                }else if(candidateJobModels.get(j).getJob_time_type().equals("4")){
                                    other.add(mActivity.getString(R.string.khongtrungtuyen));
                                }else {
                                    other.add(mActivity.getString(R.string.dangcho));
                                }

                                if(candidateJobModels.get(j).getJob_time_type().equals("1")){
                                    time_type.add(mActivity.getString(R.string.parttime));
                                }else if(candidateJobModels.get(j).getJob_time_type().equals("2")){
                                    time_type.add(mActivity.getString(R.string.fulltime));
                                }else {
                                    time_type.add("");
                                }
                                Job_id.add(candidateJobModels.get(j).getJob_id());
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
