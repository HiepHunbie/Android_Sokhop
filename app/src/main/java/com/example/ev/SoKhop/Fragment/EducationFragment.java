package com.example.ev.SoKhop.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ev.SoKhop.Activity.InfomationActivity;
import com.example.ev.SoKhop.Adapter.ListEducationAdapter;
import com.example.ev.SoKhop.Api.HttpAddEducation;
import com.example.ev.SoKhop.Api.HttpUpdateInfoUser;
import com.example.ev.SoKhop.Api.core.API;
import com.example.ev.SoKhop.Api.core.HttpResponseCode;
import com.example.ev.SoKhop.Base.BaseFragment;
import com.example.ev.SoKhop.Dialog.DialogCall;
import com.example.ev.SoKhop.Model.EducationModel;
import com.example.ev.SoKhop.R;
import com.example.ev.SoKhop.Utils.DateUtils;
import com.example.ev.SoKhop.Utils.Pref;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by GMAN on 9/28/2016.
 */

public class EducationFragment extends BaseFragment {
    private static String end_time;
    private static ArrayAdapter<CharSequence> adapterMonth;
    private static ArrayAdapter<CharSequence> adapterYear;
    private InfomationActivity activity;
    private ListView listView;

    private ArrayList<String> from = new ArrayList<>();
    private ArrayList<String> to = new ArrayList<>();
    private ArrayList<String> school = new ArrayList<>();
    private ArrayList<String> detail = new ArrayList<>();
    private ArrayList<String> imageid = new ArrayList<>();
    private ArrayList<String> classification = new ArrayList<>();
    private static ArrayList<String> edu_id = new ArrayList<>();
    private static List<EducationModel> educationModels;
    private static List<EducationModel> educationModelsItem;
    private ImageView button_addnew ;
    public EducationFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_education, container, false);
//        activity = (InfomationActivity)getActivity();
        educationModels = new ArrayList<>();
        educationModels = mActivity.databaseHelper.getAllEducation();
        edu_id.clear();

       for(int i =0;i<educationModels.size();i++){
           from.add(DateUtils.convertDateFormat(educationModels.get(i).getUpe_start_time()));
           to.add(DateUtils.convertDateFormat(educationModels.get(i).getUpe_end_time()));
           school.add(educationModels.get(i).getSch_name());
           detail.add(educationModels.get(i).getUpe_description());
           imageid.add(educationModels.get(i).getUpe_img_url());
           classification.add(educationModels.get(i).getUpe_degree());
           edu_id.add(String.valueOf(educationModels.get(i).getUpe_id()));

       }
        listView = (ListView) rootView.findViewById(R.id.lvEducation);
        button_addnew = (ImageView)rootView.findViewById(R.id.btnAdd);
        ListEducationAdapter customList = new ListEducationAdapter(mActivity, from,to,school,imageid,detail,classification,edu_id);
        listView.setAdapter(customList);
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
                ShowDialogEducation(mActivity, getString(R.string.add_education),999);
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
    public static void addNewEducation(String token, final Activity activity,String start_time,String end_time,String degree,String img_url,String video_url,String description,String school_name,String major){
        HttpAddEducation api = new HttpAddEducation(activity,token);
        api.request(start_time,end_time,degree,img_url,video_url,description,school_name,major,new API.APIDelegate() {
            @Override
            public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                if (httpResponseCode == HttpResponseCode.SUCCESS) {
                    DialogCall.showResponse(mActivity, mActivity.getString(R.string.addEducationSuccess), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int id = v.getId();
                            if (id == R.id.btnOk) {
                                mActivity.getEducationInfo(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),mActivity.p.getString(Pref.USER_ID,null),9);
                            }
                        }
                    });
                }else {
                    DialogCall.showResponse(mActivity, mActivity.getString(R.string.addEducationError), new View.OnClickListener() {
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
    public static void ShowDialogEducation(Context mContext, String titleStr , final int pos){
        final AlertDialog builder = new AlertDialog.Builder(mContext).create();
        builder.show();
        builder.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        builder.setCanceledOnTouchOutside(false);
        builder.setCancelable(false);

        Window window = builder.getWindow();
        window.setContentView(R.layout.dialog_add_education);

        TextView TitleTv = (TextView) window.findViewById(R.id.dialog_title);
        TitleTv.setText(titleStr);

        final Spinner spAddMonth = (Spinner) window.findViewById(R.id.spAddMonth);
        final Spinner spAddYear = (Spinner) window.findViewById(R.id.spAddYear);
        final Spinner spAddMonthTo = (Spinner) window.findViewById(R.id.spAddMonthTo);
        final Spinner spAddYearTo = (Spinner) window.findViewById(R.id.spAddYearTo);
        Button btnCancel = (Button) window.findViewById(R.id.btnCancel);
        Button btnSave = (Button) window.findViewById(R.id.btnSave);
        final EditText edtMoreInfo = (EditText)window.findViewById(R.id.edtMoreInfo);
        final EditText edtValues = (EditText)window.findViewById(R.id.edtValues);
        final EditText edtWhere = (EditText)window.findViewById(R.id.edtWhere);
        final CheckBox cbEducation = (CheckBox)window.findViewById(R.id.cbEducation);

//        adapterMonth = ArrayAdapter.createFromResource(
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

        if(pos != 999){

            String yearto = "";
            String monthto = "";
            if(educationModels.get(pos).getUpe_end_time().length()>0){
                yearto = educationModels.get(pos).getUpe_end_time().substring(0,4);
                monthto = educationModels.get(pos).getUpe_end_time().substring(5,7);
            }
            String yearfrom = educationModels.get(pos).getUpe_start_time().substring(0,4);
            String monthfrom = educationModels.get(pos).getUpe_start_time().substring(5,7);

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
            if(educationModels.get(pos).getUpe_end_time().length()<2){
                cbEducation.setChecked(true);
            }

            edtWhere.setText(educationModels.get(pos).getSch_name());
            edtValues.setText(educationModels.get(pos).getUpe_degree());
            edtMoreInfo.setText(educationModels.get(pos).getUpe_description());

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
                if(cbEducation.isChecked()){
                    end_time = "";
                }else {
                    end_time = spAddYearTo.getSelectedItem().toString()+"-"+spAddMonthTo.getSelectedItem().toString().replace("Th.","")+"-01";
                }
                String degree = edtValues.getText().toString();
                String img_url = "";
                String description = edtMoreInfo.getText().toString();
                String video_url = "";
                String school_name = edtWhere.getText().toString();
                String major = "";
                if(pos == 999){
                    addNewEducation(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),mActivity,start_time,end_time,degree,img_url,video_url,description,school_name,major);
                }else {
                    int res = HttpUpdateInfoUser.updateEducation(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),edu_id.get(pos),start_time,end_time,degree,img_url,video_url,description,school_name,major);
                    if(res == 200){
                        DialogCall.showResponse(mActivity, mActivity.getString(R.string.editEducationSuccess), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int id = v.getId();
                                if (id == R.id.btnOk) {
                                    mActivity.getEducationInfo(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),mActivity.p.getString(Pref.USER_ID,null),9);
                                }
                            }
                        });
                    }else {
                        DialogCall.showResponse(mActivity, mActivity.getString(R.string.editEducationError), new View.OnClickListener() {
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
                // TODO Auto-generated method stub
                builder.dismiss();
            }
        });

    }
}
