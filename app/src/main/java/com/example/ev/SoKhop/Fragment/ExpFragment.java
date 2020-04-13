package com.example.ev.SoKhop.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ev.SoKhop.Activity.InfomationActivity;
import com.example.ev.SoKhop.Adapter.ListExpAdapter;
import com.example.ev.SoKhop.Adapter.PositionTimelineAdapter;
import com.example.ev.SoKhop.Api.HttpAddExperices;
import com.example.ev.SoKhop.Api.HttpUpdateInfoUser;
import com.example.ev.SoKhop.Api.core.API;
import com.example.ev.SoKhop.Api.core.HttpResponseCode;
import com.example.ev.SoKhop.Base.BaseFragment;
import com.example.ev.SoKhop.Dialog.DialogCall;
import com.example.ev.SoKhop.Model.ExperiencesModel;
import com.example.ev.SoKhop.Model.PositionJobModel;
import com.example.ev.SoKhop.Model.PositionTimelineModel;
import com.example.ev.SoKhop.R;
import com.example.ev.SoKhop.Utils.DateUtils;
import com.example.ev.SoKhop.Utils.Pref;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Created by GMAN on 9/28/2016.
 */

public class ExpFragment extends BaseFragment {
    private static String end_time;
    private InfomationActivity activity;
    private ListView listView;
    private ArrayList<String> from = new ArrayList<>();
    private ArrayList<String> to = new ArrayList<>();
    private ArrayList<String> company = new ArrayList<>();
    private ArrayList<String> detail = new ArrayList<>();
    private ArrayList<String> imageid = new ArrayList<>();
    private ArrayList<String> possition = new ArrayList<>();
    private static final ArrayList<String> exp_id = new ArrayList<>();
    private static List<ExperiencesModel> experiencesModels;
    private static List<ExperiencesModel> experiencesModelsItem;
    private List<PositionTimelineModel> positionTimelineModels;
    private ArrayList<String> possTime = new ArrayList<>();
    private ArrayList<String> expTime = new ArrayList<>();
    private ListView listViewPossTime;
    private ImageView button_addnew ;
    private static List<PositionJobModel> positionJobModels;
    private static ArrayList<String> arrPositionJob = new ArrayList<>();
    private static ArrayAdapter<String> adp;
    public ExpFragment() {
    }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_exp, container, false);
//        activity = (InfomationActivity)getActivity();
            positionJobModels = new ArrayList<>();
            positionJobModels = mActivity.databaseHelper.getAllPositionJobs();
            for(int i =0;i<positionJobModels.size();i++){
                arrPositionJob.add(positionJobModels.get(i).getPos_value());
            }
            experiencesModels = new ArrayList<>();
            experiencesModels = mActivity.databaseHelper.getAllExperices();
            exp_id.clear();
            for(int i =0;i<experiencesModels.size();i++){
                from.add(DateUtils.convertDateFormat(experiencesModels.get(i).getUex_start_time()));
                to.add(DateUtils.convertDateFormat(experiencesModels.get(i).getUex_end_time()));
                company.add(experiencesModels.get(i).getUex_company_name());
                detail.add(experiencesModels.get(i).getUex_description());
                imageid.add(experiencesModels.get(i).getUex_img_url());
                possition.add(experiencesModels.get(i).getPos_value());
                exp_id.add(String.valueOf(experiencesModels.get(i).getUex_id()));
            }

            positionTimelineModels = new ArrayList<>();
            positionTimelineModels = mActivity.databaseHelper.getAllPositionTimeline();
            for(int i =0;i<positionTimelineModels.size();i++){
                possTime.add(positionTimelineModels.get(i).getPosition_value());
                expTime.add(positionTimelineModels.get(i).getDiff_time());
            }
            listView = (ListView) rootView.findViewById(R.id.lvExp);
            button_addnew = (ImageView)rootView.findViewById(R.id.btnAdd);
            listViewPossTime = (ListView) rootView.findViewById(R.id.lvPositionTimeline);
            ListExpAdapter customList = new ListExpAdapter(mActivity, from,to,company,imageid,detail,possition,exp_id);
            PositionTimelineAdapter positionTimelineAdapter = new PositionTimelineAdapter(getActivity(),possTime,expTime);
            listView.setAdapter(customList);
            listViewPossTime.setAdapter(positionTimelineAdapter);
            showToolBarItem();
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
            button_addnew.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShowDialogExp(mActivity, getString(R.string.add_exp),999);
                }
            });

            if(p.getString(Pref.BOOLEAN_IS_ME,null)!=null){
                if(p.getString(Pref.BOOLEAN_IS_ME,null).equals("1")){
                    button_addnew.setVisibility(View.GONE);
                }else {
                    button_addnew.setVisibility(View.VISIBLE);
                }
            }else {
                button_addnew.setVisibility(View.VISIBLE);
            }
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
    public static void addNewExperices(String token, final Activity activity, String company_name,String start_time,String end_time,String position_name,String skills
            ,String img_url,String video_url,String description,String year_of_working,String company_id){
        HttpAddExperices api = new HttpAddExperices(activity,token);
        api.request(company_name,start_time,end_time,position_name,skills,img_url,video_url,description,year_of_working,company_id,new API.APIDelegate() {
            @Override
            public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                if (httpResponseCode == HttpResponseCode.SUCCESS) {
                    DialogCall.showResponse(mActivity, mActivity.getString(R.string.addExpSuccess), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int id = v.getId();
                            if (id == R.id.btnOk) {
                                mActivity. getPositionTimeline(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),mActivity.p.getString(Pref.USER_ID,null));
                                mActivity.getExpericesInfo(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),mActivity.p.getString(Pref.USER_ID,null),10);
                            }
                        }
                    });
                }else {
                    DialogCall.showResponse(mActivity, mActivity.getString(R.string.addExpError), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int id = v.getId();
                            if (id == R.id.btnOk) {
                            }
                        }
                    });
                }
            }
        });
    }

    public static void ShowDialogExp(Context mContext, String titleStr, final int pos){
        final AlertDialog builder = new AlertDialog.Builder(mContext).create();
        builder.show();
        builder.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        builder.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        builder.setCanceledOnTouchOutside(false);
        builder.setCancelable(false);
        if(adp!=null){
            adp.clear();
            adp.notifyDataSetChanged();
        }
        Window window = builder.getWindow();
        window.setContentView(R.layout.dialog_add_exp);

        TextView TitleTv = (TextView) window.findViewById(R.id.dialog_title);
        TitleTv.setText(titleStr);

        final Spinner spAddMonth = (Spinner) window.findViewById(R.id.spAddMonth);
        final Spinner spAddYear = (Spinner) window.findViewById(R.id.spAddYear);
        final Spinner spAddMonthTo = (Spinner) window.findViewById(R.id.spAddMonthTo);
        final Spinner spAddYearTo = (Spinner) window.findViewById(R.id.spAddYearTo);
        Button btnCancel = (Button) window.findViewById(R.id.btnCancel);
        Button btnSave = (Button) window.findViewById(R.id.btnSave);
        final EditText edtMoreInfo = (EditText)window.findViewById(R.id.edtMoreInfo);
        final AutoCompleteTextView edtPos = (AutoCompleteTextView)window.findViewById(R.id.edtPos);
        final EditText edtWhere = (EditText)window.findViewById(R.id.edtWhere);
        final CheckBox cbExperices = (CheckBox)window.findViewById(R.id.cbExperices);
//        ArrayAdapter<CharSequence> adapterMonth = ArrayAdapter.createFromResource(
//                mContext, R.array.month, R.layout.spinner_item);
//        adapterMonth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        List<String> LinesMonth = Arrays.asList(mActivity.getResources().getStringArray(R.array.month));
        ArrayAdapter<String> adapterMonth = new ArrayAdapter(mActivity, R.layout.spinner_item, LinesMonth);
        spAddMonth.setAdapter(adapterMonth);
        spAddMonthTo.setAdapter(adapterMonth);

        ArrayList<String> arrayListYear = new ArrayList<>();
        List<String> Lines = Arrays.asList(mActivity.getResources().getStringArray(R.array.year));
        int birthYear ;
        if(mActivity.p.getString(Pref.upb_dob,null).length()>5){
            birthYear = Integer.parseInt(mActivity.p.getString(Pref.upb_dob,null).substring(0,4));
        }else {
            birthYear = 1970;
        }
        Calendar c = Calendar.getInstance();
        int currentYear =  c.get(Calendar.YEAR);
        for (int i =0;i<Lines.size();i++){
            int start = Integer.parseInt(Lines.get(i));
            if((start >= birthYear)&&(start<=currentYear)){
                arrayListYear.add(Lines.get(i));
            }
        }
        ArrayAdapter<String> adapterYear = new ArrayAdapter(mActivity, R.layout.spinner_item, arrayListYear);
        spAddYear.setAdapter(adapterYear);
        spAddYearTo.setAdapter(adapterYear);
        if(arrPositionJob.size()>0){
            arrPositionJob.clear();
        }
        positionJobModels = new ArrayList<>();
        positionJobModels = mActivity.databaseHelper.getAllPositionJobs();
        for(int i =0;i<positionJobModels.size();i++){
            arrPositionJob.add(positionJobModels.get(i).getPos_value());
        }
        adp = new ArrayAdapter<String>(mActivity,R.layout.spinner_item, arrPositionJob);
//        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edtPos.setAdapter(adp);
        if(pos!=999){

            String yearto = "";
            String monthto = "";
            if(experiencesModels.get(pos).getUex_end_time().length()>0){
                yearto = experiencesModels.get(pos).getUex_end_time().substring(0,4);
                monthto = experiencesModels.get(pos).getUex_end_time().substring(5,7);
            }
            String yearfrom = experiencesModels.get(pos).getUex_start_time().substring(0,4);
            String monthfrom = experiencesModels.get(pos).getUex_start_time().substring(5,7);

            for (int i = 0; i < adapterMonth.getCount(); i++) {
                if (adapterMonth.getItem(i).toString().replace("Th.","").equals(monthfrom)) {
                    spAddMonth.setSelection(i);
                }
                if (adapterMonth.getItem(i).toString().replace("Th.","").equals(monthto)) {
                    spAddMonthTo.setSelection(i);
                }
            }
            for (int i = 0; i < adapterYear.getCount(); i++) {
                if (adapterYear.getItem(i).toString().contains(yearfrom)) {
                    spAddYear.setSelection(i);
                }
                if (adapterYear.getItem(i).toString().contains(yearto)) {
                    spAddYearTo.setSelection(i);
                }
            }
            if(experiencesModels.get(pos).getUex_end_time().length()<2){
                cbExperices.setChecked(true);
            }
            edtWhere.setText(experiencesModels.get(pos).getUex_company_name());
            edtPos.setText(experiencesModels.get(pos).getPos_value());
            edtMoreInfo.setText(experiencesModels.get(pos).getUex_description());

        }else {
            Calendar newCalendar = Calendar.getInstance();
            for (int i = 0;i<adapterYear.getCount();i++){
                if(spAddYear.getItemAtPosition(i).toString().equals(""+newCalendar.get(Calendar.YEAR))){
                    spAddYear.setSelection(i);
                    spAddYearTo.setSelection(i);
                }
            }
            for (int i = 0;i<adapterMonth.getCount();i++){
                if(spAddMonth.getItemAtPosition(i).toString().replace("Th.","").equals(""+newCalendar.get(Calendar.MONTH))){
                    spAddMonth.setSelection(i);
                    spAddMonthTo.setSelection(i);
                }
            }
        }

        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                String start_time = spAddYear.getSelectedItem().toString()+"-"+spAddMonth.getSelectedItem().toString().replace("Th.","")+"-01";
                if(cbExperices.isChecked()){
                    end_time = "";
                }else {
                    end_time = spAddYearTo.getSelectedItem().toString()+"-"+spAddMonthTo.getSelectedItem().toString().replace("Th.","")+"-01";
                }
                String position_name = edtPos.getText().toString();
                String img_url = "";
                String description = edtMoreInfo.getText().toString();
                String skills = "";
                String video_url = "";
                String company_name = edtWhere.getText().toString();
                String year_of_working = "";
                String company_id = "";
                if(pos == 999){
                    addNewExperices(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),mActivity,company_name,start_time,end_time,position_name,skills,img_url,video_url,description,year_of_working,company_id);
                }else {
                    int res = HttpUpdateInfoUser.updateExp(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),exp_id.get(pos),company_name,start_time,end_time,position_name,skills,img_url,video_url,description,year_of_working,company_id);
                    if (res == 200) {
                        DialogCall.showResponse(mActivity, mActivity.getString(R.string.editExpSuccess), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int id = v.getId();
                                if (id == R.id.btnOk) {
                                    mActivity. getPositionTimeline(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),mActivity.p.getString(Pref.USER_ID,null));
                                    mActivity.getExpericesInfo(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),mActivity.p.getString(Pref.USER_ID,null),10);
                                }
                            }
                        });
                    }else {
                        DialogCall.showResponse(mActivity, mActivity.getString(R.string.editExpError), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int id = v.getId();
                                if (id == R.id.btnOk) {
                                }
                            }
                        });
                    }
                }

                builder.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                builder.dismiss();
            }
        });

    }

}
