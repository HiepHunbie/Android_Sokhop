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
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ev.SoKhop.Activity.InfomationActivity;
import com.example.ev.SoKhop.Api.HttpAddNewJobExpect;
import com.example.ev.SoKhop.Api.HttpUpdateInfoUser;
import com.example.ev.SoKhop.Api.core.API;
import com.example.ev.SoKhop.Api.core.HttpResponseCode;
import com.example.ev.SoKhop.Base.BaseFragment;
import com.example.ev.SoKhop.Dialog.DialogCall;
import com.example.ev.SoKhop.Model.ExpectJobModel;
import com.example.ev.SoKhop.Model.LocationModel;
import com.example.ev.SoKhop.Model.PositionJobModel;
import com.example.ev.SoKhop.R;
import com.example.ev.SoKhop.Utils.Edittext;
import com.example.ev.SoKhop.Utils.Pref;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GMAN on 9/29/2016.
 */

public class ExpectJobFragment extends BaseFragment {
    private static String salary_basic;
    private static ArrayAdapter<String> adps,adp,adp2;
    private InfomationActivity activity;
    public ExpectJobFragment() {
    }
    private static ArrayList<String> arrPositionJob = new ArrayList<>();
    private static ArrayList<String> arrLocation = new ArrayList<>();
    private ArrayList<String> arrExpectJob = new ArrayList<>();
    private static List<PositionJobModel> positionJobModels;
    private static List<LocationModel> locationModels;
    private static List<ExpectJobModel> expectJobModels;
    private ImageView btnAdd;
    private TextView txtThoiGianLamViec;
    private ScrollView scrollExpectJobs;

    private TextView txtSuNghiep,txtMuctieunghenghiep,txtWorkPlaces,txtPosition,txtLuongcoban,txtThuong,txtPhucap,txtTongThuNhap;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_desired_job, container, false);
//        activity = (InfomationActivity)getActivity();
//        mActivity.databaseHelper.deleteAllExpectJob();
//        mActivity.databaseHelper.deleteAllLocation();
//        mActivity.databaseHelper.deleteAllPositionJobs();
        positionJobModels = new ArrayList<>();
        positionJobModels = mActivity.databaseHelper.getAllPositionJobs();
        for(int i =0;i<positionJobModels.size();i++){
            arrPositionJob.add(positionJobModels.get(i).getPos_value());
        }

        locationModels = new ArrayList<>();
        locationModels = mActivity.databaseHelper.getAllLocation();
        for(int i =0;i<locationModels.size();i++){
            arrLocation.add(locationModels.get(i).getLocat_value());
        }

        adp = new ArrayAdapter<String>(mActivity,R.layout.spinner_item, arrPositionJob);
//        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adp2 = new ArrayAdapter<String>(mActivity,R.layout.spinner_item, arrLocation);
//        adp2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        expectJobModels = new ArrayList<>();
        expectJobModels = mActivity.databaseHelper.getAllExpectJob();

        btnAdd = (ImageView)rootView.findViewById(R.id.btnAdd);

        txtPosition = (TextView)rootView.findViewById(R.id.txtPosition);
        txtWorkPlaces = (TextView)rootView.findViewById(R.id.txtWorkPlaces);
        txtMuctieunghenghiep = (TextView)rootView.findViewById(R.id.txtMuctieunghenghiep);
        txtLuongcoban = (TextView)rootView.findViewById(R.id.txtLuongcoban);
        txtSuNghiep = (TextView)rootView.findViewById(R.id.txtSuNghiep);
        txtThuong = (TextView)rootView.findViewById(R.id.txtThuong);
        txtPhucap = (TextView)rootView.findViewById(R.id.txtPhucap);
        txtTongThuNhap = (TextView)rootView.findViewById(R.id.txtTongThuNhap);
        txtThoiGianLamViec = (TextView) rootView.findViewById(R.id.txtThoiGianLamViec);
        scrollExpectJobs = (ScrollView)rootView.findViewById(R.id.scrollExpectJobs);

        if(expectJobModels.size()>0){
            txtPosition.setText(expectJobModels.get(0).getPos_value());
            String places = "";
            if(expectJobModels.get(0).getDistrict().length()>0){
                places = expectJobModels.get(0).getDistrict()+" "+expectJobModels.get(0).getProvince_value();
            }else {
                places = expectJobModels.get(0).getProvince_value();
            }
            txtWorkPlaces.setText(places);
            txtMuctieunghenghiep.setText(expectJobModels.get(0).getObjective());

            txtLuongcoban.setText(Edittext.convertTextToCommas(expectJobModels.get(0).getSalary_basic()));
            Log.d("autocomplete","ss"+expectJobModels.get(0).getSalary_basic());
            txtSuNghiep.setText(expectJobModels.get(0).getDescription());
            txtThuong.setText(Edittext.convertTextToCommas(expectJobModels.get(0).getBonus()));
            txtPhucap.setText(Edittext.convertTextToCommas(expectJobModels.get(0).getAllowance()));

            String salarybasic = expectJobModels.get(0).getSalary_basic().replace("> ","");
            String bonuss = expectJobModels.get(0).getBonus();
            String allowances= expectJobModels.get(0).getAllowance();
            if(expectJobModels.get(0).getSalary_basic().length()<=2){
                salarybasic = "0";
            }
            if(expectJobModels.get(0).getBonus().length()<=2){
                bonuss = "0";
            }
            if(expectJobModels.get(0).getAllowance().length()<=2){
                allowances = "0";
            }
            if(expectJobModels.get(0).getSalary_basic().contains(">")){
                txtTongThuNhap.setText(Edittext.convertTextToCommas("> "+String.valueOf(Long.parseLong(salarybasic.replace("> ","").replaceAll(",", "").replaceAll("\\.", ""))+Long.parseLong(bonuss)+Long.parseLong(allowances.replace("> ","").replaceAll(",", "").replaceAll("\\.", "")))));
            }else if(expectJobModels.get(0).getSalary_basic().contains("<")){
                txtTongThuNhap.setText(Edittext.convertTextToCommas("< "+String.valueOf(Long.parseLong(salarybasic.replace("< ","").replaceAll(",", "").replaceAll("\\.", ""))+Long.parseLong(bonuss)+Long.parseLong(allowances.replace("< ","").replaceAll(",", "").replaceAll("\\.", "")))));
            }else if(expectJobModels.get(0).getSalary_basic().contains("-")){
                String[] parts = expectJobModels.get(0).getSalary_basic().split(" - ");
                if(parts.length>1){
                    txtTongThuNhap.setText(Edittext.convertTextToCommas("> "+String.valueOf(Long.parseLong(parts[0].replaceAll(",", "").replaceAll("\\.", ""))+Long.parseLong(bonuss)+Long.parseLong(allowances.replace("> ","").replaceAll(",", "").replaceAll("\\.", "")))));
                }else {
                    txtTongThuNhap.setText(Edittext.convertTextToCommas("> "+Long.parseLong(bonuss)+Long.parseLong(allowances.replace("> ","").replaceAll(",", "").replaceAll("\\.", ""))));
                }
            }else {
                txtTongThuNhap.setText(Edittext.convertTextToCommas(String.valueOf(Long.parseLong(salarybasic.replace("> ","").replaceAll(",", "").replaceAll("\\.", ""))+Long.parseLong(bonuss)+Long.parseLong(allowances.replace("> ","").replaceAll(",", "").replaceAll("\\.", "")))));
            }

            if(expectJobModels.get(0).getTime_type().equals("1")){
                txtThoiGianLamViec.setText(mActivity.getString(R.string.parttime));
            }else {
                txtThoiGianLamViec.setText(mActivity.getString(R.string.fulltime));
            }

        }
            if(expectJobModels.size()>0){
                scrollExpectJobs.setVisibility(View.VISIBLE);
                btnAdd.setVisibility(View.GONE);
                if (mActivity.p.getString(Pref.BOOLEAN_IS_ME, null) != null) {
                    if (mActivity.p.getString(Pref.BOOLEAN_IS_ME, null).equals("1")) {
                        showToolBarItem(false);
                    }else {
                        showToolBarItem(true);
                    }
                }else {
                    showToolBarItem(true);
                }
            }else {
                scrollExpectJobs.setVisibility(View.INVISIBLE);
                showToolBarItem(false);
                btnAdd.setVisibility(View.VISIBLE);
            }
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

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialogAddNewHopeJobs(mActivity, mActivity.getString(R.string.add_desired_job),arrPositionJob,arrLocation,false);
            }
        });
        return rootView;
    }
    public void showToolBarItem(boolean isMe){
        if(isMe){
            mActivity.showItemToolBar(true, false, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = v.getId();
                    if (id == R.id.btnCreate) {
//                    mActivity.selectItem(19);
                        ShowDialogAddNewHopeJobs(mActivity, mActivity.getString(R.string.fix_desired_job),arrPositionJob,arrLocation,true);
                    }
                }
            });
        }else {
            mActivity.showItemToolBar(false, false, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = v.getId();
                    if (id == R.id.btnCreate) {
//                    mActivity.selectItem(19);
                    }
                }
            });
        }

    }
    public static void ShowDialogAddNewHopeJobs(final Context mContext, String titleStr, ArrayList<String> list, ArrayList<String> listLocation, final boolean isFix){
        final AlertDialog builder = new AlertDialog.Builder(mContext).create();
        builder.show();
        builder.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        builder.setCanceledOnTouchOutside(false);
        builder.setCancelable(false);

        if(adp!=null){
            adp.clear();
            Log.d("autocomplete","ss"+adp.getCount());
            adp.notifyDataSetChanged();
        }
        if(adps!=null){
            adps.clear();
            adps.notifyDataSetChanged();
        }
        if(adp2!=null){
            adp2.clear();
            adp2.notifyDataSetChanged();
        }
        Window window = builder.getWindow();
        window.setContentView(R.layout.dialog_add_new_expect_jobs);

        TextView TitleTv = (TextView) window.findViewById(R.id.dialog_title);
        TitleTv.setText(titleStr);
        Button btnCancel = (Button) window.findViewById(R.id.btnCancel);
        Button btnAdd = (Button) window.findViewById(R.id.btnAdd);
//        final Spinner spCity = (Spinner)window.findViewById(R.id.spCity);
        final Spinner spThoiGianLamViec = (Spinner)window.findViewById(R.id.spThoiGianLamViec);
        final EditText edtTown = (EditText)window.findViewById(R.id.edtTown);
        final EditText edtStreet = (EditText)window.findViewById(R.id.edtStreet);
        final EditText edtNumber = (EditText)window.findViewById(R.id.edtNumber);
        final CheckBox cbUpLuongCoBan = (CheckBox)window.findViewById(R.id.cbUpLuongCoBan);
        final CheckBox cbExactlyLuongCoBan = (CheckBox)window.findViewById(R.id.cbExactlyLuongCoBan);
        final CheckBox cbDownLuongCoBan = (CheckBox)window.findViewById(R.id.cbDownLuongCoBan);
        final CheckBox cbBetweenLuongCoBan = (CheckBox)window.findViewById(R.id.cbBetweenLuongCoBan);
        final EditText edtUpLuongCoBan = (EditText)window.findViewById(R.id.edtUpLuongCoBan);
        final EditText edtBetweenLuongCoBanFrom = (EditText)window.findViewById(R.id.edtBetweenLuongCoBanFrom);
        final EditText edtBetweenLuongCoBanTo = (EditText)window.findViewById(R.id.edtBetweenLuongCoBanTo);
        final EditText edtDownLuongCoBan = (EditText)window.findViewById(R.id.edtDownLuongCoBan);
        final EditText edtExactlyLuongCoBan = (EditText)window.findViewById(R.id.edtExactlyLuongCoBan);
        final EditText edt_thuong = (EditText)window.findViewById(R.id.edt_thuong);
        final EditText edt_phucap = (EditText)window.findViewById(R.id.edt_phucap);
        final EditText edtMucTieuNgheNghiep = (EditText)window.findViewById(R.id.edtMucTieuNgheNghiep);
        final EditText edtSuNghiep = (EditText)window.findViewById(R.id.edtSuNghiep);
        final EditText edt_tongthunhap = (EditText)window.findViewById(R.id.edt_tongthunhap);
        final AutoCompleteTextView autocomplete_vitri =
                (AutoCompleteTextView) window.findViewById(R.id.autocomplete_Vitri);
        final AutoCompleteTextView autocomplete_location =
                (AutoCompleteTextView) window.findViewById(R.id.autocomplete_Location);
//
        if(arrPositionJob.size()>0){
            arrPositionJob.clear();
        }
        if(arrLocation.size()>0){
            arrLocation.clear();
        }
        positionJobModels = new ArrayList<>();
        positionJobModels = mActivity.databaseHelper.getAllPositionJobs();
        for(int i =0;i<positionJobModels.size();i++){
            arrPositionJob.add(positionJobModels.get(i).getPos_value());
        }

        locationModels = new ArrayList<>();
        locationModels = mActivity.databaseHelper.getAllLocation();
        for(int i =0;i<locationModels.size();i++){
            arrLocation.add(locationModels.get(i).getLocat_value());
        }
        adp = new ArrayAdapter<String>(mActivity,R.layout.spinner_item, arrPositionJob);
//        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adp2 = new ArrayAdapter<String>(mActivity,R.layout.spinner_item, arrLocation);
//        adp2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayList<String> arrTimeWork = new ArrayList<>();
        arrTimeWork.add(mActivity.getString(R.string.fulltime));
        arrTimeWork.add(mActivity.getString(R.string.parttime));
        adps = new ArrayAdapter<String>(mActivity,R.layout.spinner_item, arrTimeWork);
//        adps.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spThoiGianLamViec.setAdapter(adps);

        checkBoxSet(cbUpLuongCoBan,cbBetweenLuongCoBan,cbDownLuongCoBan,cbExactlyLuongCoBan);
        if(autocomplete_vitri.getAdapter()== null){
            autocomplete_vitri.setAdapter(adp);
        }
        if(autocomplete_location.getAdapter()== null) {
            autocomplete_location.setAdapter(adp2);
        }

        if(isFix){
            btnAdd.setText(mContext.getString(R.string.cap_nhat));
            if(expectJobModels.size()>0) {
//                for (int i = 0; i < arrPositionJob.size(); i++) {
//                    if (arrPositionJob.get(i).equals(expectJobModels.get(0).getPos_value())) {
//                        spPosition.setSelection(i);
//                        autocomplete_vitri.setText(expectJobModels.get(0).getPos_value());
//                    }
//                }
                autocomplete_vitri.setText(expectJobModels.get(0).getPos_value());
                autocomplete_location.setText(expectJobModels.get(0).getProvince_value());
                edtTown.setText(expectJobModels.get(0).getDistrict());
                Log.d("autocomplete","ss"+expectJobModels.get(0).getDistrict());
            }
                if(locationModels.size()>0) {
//                    for (int i = 0; i < arrLocation.size(); i++) {
//                        if (arrLocation.get(i).equals(expectJobModels.get(0).getProvince_value())) {
//                            spCity.setSelection(i);
//                        }
//                    }
//                    autocomplete_location.setText(locationModels.get(0).getLocat_value());
                }
            if(expectJobModels.size()>0){
                if( expectJobModels.get(0).getSalary_basic().contains(">")){
                    cbUpLuongCoBan.setChecked(true);
                    edtUpLuongCoBan.setText(expectJobModels.get(0).getSalary_basic().replace("> ",""));
                }else if( expectJobModels.get(0).getSalary_basic().contains("-")){
                    String[] parts = expectJobModels.get(0).getSalary_basic().split(" - ");
                    if(parts.length>1){
                        edtBetweenLuongCoBanFrom.setText(parts[0]);
                        edtBetweenLuongCoBanTo.setText(parts[1]);
                    }
                    cbBetweenLuongCoBan.setChecked(true);
                }else if( expectJobModels.get(0).getSalary_basic().contains("<")){
                    cbDownLuongCoBan.setChecked(true);
                    edtDownLuongCoBan.setText(expectJobModels.get(0).getSalary_basic().replace("< ",""));
                }else{
                    cbExactlyLuongCoBan.setChecked(true);
                    edtExactlyLuongCoBan.setText(expectJobModels.get(0).getSalary_basic().replace("> ",""));
                }

                edt_thuong.setText(expectJobModels.get(0).getBonus());
                edt_phucap.setText(expectJobModels.get(0).getAllowance());
                edtMucTieuNgheNghiep.setText(expectJobModels.get(0).getObjective());
                edtSuNghiep.setText(expectJobModels.get(0).getDescription());


                if(expectJobModels.get(0).getTime_type().equals("1")){
                    spThoiGianLamViec.setSelection(1);
                }else {
                    spThoiGianLamViec.setSelection(0);
                }
            }

//            edt_thuong.setText(expectJobModels.get(0).getBonus());
        }else {
            btnAdd.setText(mContext.getString(R.string.themmoi));
        }

        Edittext.addCommasToEdittext(edtUpLuongCoBan);
        Edittext.addCommasToEdittext(edtBetweenLuongCoBanFrom);
        Edittext.addCommasToEdittext(edtBetweenLuongCoBanTo);
        Edittext.addCommasToEdittext(edtDownLuongCoBan);
        Edittext.addCommasToEdittext(edtExactlyLuongCoBan);
        Edittext.addCommasToEdittext(edt_thuong);
        Edittext.addCommasToEdittext(edt_phucap);
        Edittext.setCheckBox(cbUpLuongCoBan,edtUpLuongCoBan,edtDownLuongCoBan,edtBetweenLuongCoBanFrom,edtBetweenLuongCoBanTo,edtExactlyLuongCoBan,null);
        Edittext.setCheckBox(cbDownLuongCoBan,edtDownLuongCoBan,edtUpLuongCoBan,edtBetweenLuongCoBanFrom,edtBetweenLuongCoBanTo,edtExactlyLuongCoBan,null);
        Edittext.setCheckBox(cbBetweenLuongCoBan,edtBetweenLuongCoBanFrom,edtDownLuongCoBan,edtUpLuongCoBan,edtExactlyLuongCoBan,edtExactlyLuongCoBan,edtBetweenLuongCoBanTo);
        Edittext.setCheckBox(cbBetweenLuongCoBan,edtBetweenLuongCoBanTo,edtDownLuongCoBan,edtExactlyLuongCoBan,edtUpLuongCoBan,edtExactlyLuongCoBan,edtBetweenLuongCoBanFrom);
        Edittext.setCheckBox(cbExactlyLuongCoBan,edtExactlyLuongCoBan,edtDownLuongCoBan,edtBetweenLuongCoBanFrom,edtBetweenLuongCoBanTo,edtUpLuongCoBan,null);

        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                builder.dismiss();
                if(cbUpLuongCoBan.isChecked()){
                    salary_basic = "> "+edtUpLuongCoBan.getText().toString().replaceAll(",","").replaceAll("\\.", "");
                }else
                if(cbBetweenLuongCoBan.isChecked()){
                    salary_basic = edtBetweenLuongCoBanFrom.getText().toString().replaceAll(",","").replaceAll("\\.","") + " - " + edtBetweenLuongCoBanTo.getText().toString().replaceAll(",","").replaceAll("\\.","");
                }else
                if(cbDownLuongCoBan.isChecked()){
                    salary_basic = "< "+edtDownLuongCoBan.getText().toString().replaceAll(",","").replaceAll("\\.","");
                }else
                if(cbExactlyLuongCoBan.isChecked()){
                    salary_basic = edtExactlyLuongCoBan.getText().toString().replaceAll(",","").replaceAll("\\.","");
                }else {
                    salary_basic = "";
                }
                String bonus= edt_thuong.getText().toString().replaceAll(",","").replaceAll("\\.","");
                String allowance= edt_phucap.getText().toString().replaceAll(",","").replaceAll("\\.","");
                String objective= edtMucTieuNgheNghiep.getText().toString();
                String description= edtSuNghiep.getText().toString();

                JSONArray jsonArrayPos = new JSONArray();
                JSONObject jsonObjectPos = new JSONObject();
                try {
                    String poss = "";
                    String pos_name = autocomplete_vitri.getText().toString();
                    String pos_value = autocomplete_vitri.getText().toString();
                    for(int i =0;i<positionJobModels.size();i++){
                        if(positionJobModels.get(i).getPos_value().equals(autocomplete_vitri.getText().toString())){
                            poss = String.valueOf(positionJobModels.get(i).getPos_id());
                            pos_name = autocomplete_vitri.getText().toString();
                            pos_value = autocomplete_vitri.getText().toString();
                        }
                    }
                    jsonObjectPos.put("pos_id",poss);
                    jsonObjectPos.put("pos_name",pos_name);
                    jsonObjectPos.put("pos_value",pos_value);
                    jsonArrayPos.put(jsonObjectPos);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String position= jsonArrayPos.toString();
                JSONArray jsonArrayLocationss = new JSONArray();
                JSONObject jsonObjectLocationss = new JSONObject();
                try {
                    String province_id = "";
                    for(int i =0;i<locationModels.size();i++){
                        if(locationModels.get(i).getLocat_value().equals(autocomplete_location.getText().toString())){
                            province_id = String.valueOf(locationModels.get(i).getLocat_id());
//                            jsonObjectLocationss.put("province_id", locationModels.get(i).getLocat_id());
                        }
                    }
                    jsonObjectLocationss.put("province_id",province_id);
//                    jsonObjectLocationss.put("province_id", String.valueOf(locationModels.get(spCity.getSelectedItemPosition()).getLocat_id()));
                    jsonObjectLocationss.put("province", autocomplete_location.getText().toString());
                    jsonObjectLocationss.put("district", edtTown.getText().toString());
                    jsonObjectLocationss.put("street", edtStreet.getText().toString());
                    jsonObjectLocationss.put("number", edtNumber.getText().toString());
                    jsonArrayLocationss.put(jsonObjectLocationss);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String location = jsonArrayLocationss.toString();
                String position_ids= "";
                String location_ids= "";
                String range_salary_id= "";
                String range_allowance_id= "";
                String range_bonus_id= "";
                String time_type ="1";
                if(spThoiGianLamViec.getSelectedItem().toString().equals(mActivity.getString(R.string.fulltime))){
                    time_type ="2";
                }else {
                    time_type ="1";
                }
                if(!isFix){
                    addNewJobExpect(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),mActivity,salary_basic,bonus,allowance,objective,description,position,location,position_ids,location_ids,range_salary_id,range_allowance_id,range_bonus_id,time_type);
                }else {
                    int res = HttpUpdateInfoUser.updateExpectJob(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null), String.valueOf(expectJobModels.get(0).getExpect_job()),salary_basic,bonus,allowance,objective,description,position,location,position_ids,location_ids,range_salary_id,range_allowance_id,range_bonus_id,time_type);
                    if(res == 200){
                        DialogCall.showResponse(mActivity, mActivity.getString(R.string.editExpectJobSuccess), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int id = v.getId();
                                if (id == R.id.btnOk) {
                                    mActivity.getPositionJobs(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null));
                                    mActivity.getLocation(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null));
                                    mActivity.p.putString(Pref.EXPECT_JOB,"1");
                                    mActivity.getExpectJob(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),mActivity.p.getString(Pref.USER_ID,null),11);
                                }
                            }
                        });
                    }else {
                        DialogCall.showResponse(mActivity, mActivity.getString(R.string.editExpectJobError), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int id = v.getId();
                                if (id == R.id.btnOk) {
                                }
                            }
                        });
                    }
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                builder.dismiss();
            }
        });

    }

    private static void  checkBoxSet(final CheckBox cbups, final CheckBox cbBet, final CheckBox cbDowns, final CheckBox cbEx){
        cbups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cbBet.setChecked(false);
                cbDowns.setChecked(false);
                cbEx.setChecked(false);
            }
        });
        cbBet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cbups.setChecked(false);
                cbDowns.setChecked(false);
                cbEx.setChecked(false);
            }
        });
        cbDowns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cbBet.setChecked(false);
                cbups.setChecked(false);
                cbEx.setChecked(false);
            }
        });
        cbEx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cbBet.setChecked(false);
                cbDowns.setChecked(false);
                cbups.setChecked(false);
            }
        });
    }

    public static void addNewJobExpect(String token, final Activity activity, String salary_basic,String bonus,String allowance,String objective,String description
            ,String position,String location,String position_ids,String location_ids,String range_salary_id
            ,String range_allowance_id,String range_bonus_id,String time_type){
            HttpAddNewJobExpect api = new HttpAddNewJobExpect(mActivity,token);
            api.request(salary_basic,bonus,allowance,objective,description,position,location,position_ids,location_ids,range_salary_id,range_allowance_id,range_bonus_id,time_type,new API.APIDelegate() {
            @Override
            public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                if (httpResponseCode == HttpResponseCode.SUCCESS) {
                    DialogCall.showResponse(mActivity, mActivity.getString(R.string.addExpectJobSuccess), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int id = v.getId();
                            if (id == R.id.btnOk) {
                                mActivity.getPositionJobs(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null));
                                mActivity.getLocation(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null));
                                mActivity.p.putString(Pref.EXPECT_JOB,"1");
                                mActivity.getExpectJob(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),mActivity.p.getString(Pref.USER_ID,null),11);
                            }
                        }
                    });
                }else {
                    DialogCall.showResponse(mActivity, mActivity.getString(R.string.addExpectJobError), new View.OnClickListener() {
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

}
