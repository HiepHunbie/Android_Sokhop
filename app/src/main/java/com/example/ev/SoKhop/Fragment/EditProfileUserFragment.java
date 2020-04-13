package com.example.ev.SoKhop.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ev.SoKhop.Activity.InfomationActivity;
import com.example.ev.SoKhop.Api.HttpUpdateProfileUser;
import com.example.ev.SoKhop.Base.BaseFragment;
import com.example.ev.SoKhop.R;
import com.example.ev.SoKhop.Utils.DateUtils;
import com.example.ev.SoKhop.Utils.Pref;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by MSI on 10/10/2016.
 */

public class EditProfileUserFragment extends BaseFragment {
    private InfomationActivity activity;
    private String dayBirth,monthBirth,yearBirth;

    public EditProfileUserFragment() {
    }
    private Pref p;
    private TextView txtInfoBirthday,txtInfoEmail,txtInfoMenh,txtInfoPhone;
    private EditText txtInfoSex,txtInfoPlacesBirth,txtInfoPlaces,
            txtInfoMangXaHoi,txtNgoaiHinh,txtInfoHeight,txtInfoWeight,txtv1,txtv2,txtv3,txtInfoHobbies,txtInfoNoiDuocGiong,
            txtInfoHatDuocGiong,txtInfoSkills,txtInfoKhaNangDacBiet,txtInfoStatus,txtInfoHo,txtInfoDem,txtInfoTen;

    private CheckBox checkBoxSex1,checkBoxSex2,checkBoxSex3,checkBoxNgoaiHinh1,checkBoxNgoaiHinh2,checkBoxNgoaiHinh3;
    private String gioitinh,ngoaihinh,lastname;
    private SimpleDateFormat dateFormatter;
    private DatePickerDialog toDatePickerDialog;
    private LinearLayout layout_add_more;
    private TextView txtTextShowMore;
    private boolean isShowMore = false;
    private String birthday;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_edit_profile_user, container, false);
//        activity = (InfomationActivity)getActivity();
        p = new Pref(getActivity());
        showToolBarItem();
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        layout_add_more = (LinearLayout)rootView.findViewById(R.id.layout_add_more);
        txtTextShowMore = (TextView)rootView.findViewById(R.id.txtTextShowMore);
        txtInfoBirthday = (TextView)rootView.findViewById(R.id.txtInfoBirthday);
        txtInfoMenh = (TextView) rootView.findViewById(R.id.txtInfoMenh);
//        txtInfoSex = (EditText)rootView.findViewById(R.id.txtInfoSex);
        txtInfoPhone = (TextView) rootView.findViewById(R.id.txtInfoPhone);
        txtInfoEmail = (TextView) rootView.findViewById(R.id.txtInfoEmail);
        txtInfoPlacesBirth = (EditText)rootView.findViewById(R.id.txtInfoPlacesBirth);
        txtInfoPlaces = (EditText)rootView.findViewById(R.id.txtInfoPlaces);
        txtInfoMangXaHoi = (EditText)rootView.findViewById(R.id.txtInfoMangXaHoi);
//        txtNgoaiHinh = (EditText)rootView.findViewById(R.id.txtInfoNgoaiHinh);
        txtInfoHeight = (EditText)rootView.findViewById(R.id.txtInfoHeight);
        txtInfoWeight = (EditText)rootView.findViewById(R.id.txtInfoWeight);
        txtv3 = (EditText)rootView.findViewById(R.id.txtv3);
        txtv2 = (EditText)rootView.findViewById(R.id.txtv2);
        txtv1 = (EditText)rootView.findViewById(R.id.txtv1);
        txtInfoHobbies = (EditText)rootView.findViewById(R.id.txtInfoHobbies);
        txtInfoNoiDuocGiong = (EditText)rootView.findViewById(R.id.txtInfoNoiDuocGiong);
        txtInfoHatDuocGiong = (EditText)rootView.findViewById(R.id.txtInfoHatDuocGiong);
        txtInfoSkills = (EditText)rootView.findViewById(R.id.txtInfoSkills);
        txtInfoKhaNangDacBiet = (EditText)rootView.findViewById(R.id.txtInfoKhaNangDacBiet);
        txtInfoStatus = (EditText)rootView.findViewById(R.id.txtInfoStatus);
        txtInfoHo = (EditText)rootView.findViewById(R.id.txtInfoHo);
        txtInfoDem = (EditText)rootView.findViewById(R.id.txtInfoDem);
        txtInfoTen = (EditText)rootView.findViewById(R.id.txtInfoTen);
        checkBoxSex1 = (CheckBox)rootView.findViewById(R.id.checkBoxSex1);
        checkBoxSex2 = (CheckBox)rootView.findViewById(R.id.checkBoxSex2);
        checkBoxSex3 = (CheckBox)rootView.findViewById(R.id.checkBoxSex3);
        checkBoxNgoaiHinh1 = (CheckBox)rootView.findViewById(R.id.checkBoxNgoaiHinh1);
        checkBoxNgoaiHinh2 = (CheckBox)rootView.findViewById(R.id.checkBoxNgoaiHinh2);
        checkBoxNgoaiHinh3 = (CheckBox)rootView.findViewById(R.id.checkBoxNgoaiHinh3);

        if(p.getString(Pref.upb_status,null)!=null){
            txtInfoStatus.setText(p.getString(Pref.upb_status,null));
        }
        if(p.getString(Pref.upb_last_name,null)!=null){
            txtInfoHo.setText(p.getString(Pref.upb_last_name,null));
        }
        if(p.getString(Pref.upb_middle_name,null)!=null){
            txtInfoDem.setText(p.getString(Pref.upb_middle_name,null));
        }
        if(p.getString(Pref.upb_first_name,null)!=null){
            txtInfoTen.setText(p.getString(Pref.upb_first_name,null));
        }
        if(p.getString(Pref.upb_dob,null)!=null){
            txtInfoBirthday.setText(DateUtils.convertDateFormat(p.getString(Pref.upb_dob,null)));
        }
        if(p.getString(Pref.upb_fate,null)!=null){
            txtInfoMenh.setText(p.getString(Pref.upb_fate,null));
        }
        if(p.getString(Pref.upb_gender,null)!=null){
            if(p.getString(Pref.upb_gender,null).equals("1")){
                checkBoxSex2.setChecked(true);
            }else if(p.getString(Pref.upb_gender,null).equals("2")){
                checkBoxSex1.setChecked(true);
            }else if(p.getString(Pref.upb_gender,null).equals("3")){
                checkBoxSex3.setChecked(true);
            }
        }

        if(p.getString(Pref.upb_phone,null)!=null){
            txtInfoPhone.setText(p.getString(Pref.upb_phone,null));
        }
        if(p.getString(Pref.upb_email,null)!=null){
            txtInfoEmail.setText(p.getString(Pref.upb_email,null));
        }
        if(p.getString(Pref.upb_address,null)!=null){
            txtInfoPlacesBirth.setText(p.getString(Pref.upb_address,null));
        }
        if(p.getString(Pref.upb_address_temp,null)!=null){
            txtInfoPlaces.setText(p.getString(Pref.upb_address_temp,null));
        }
        if(Pref.upb_website!=null){
            txtInfoMangXaHoi.setText(p.getString(Pref.upb_website,null));
        }
        //// TODO: 10/10/2016 Chốt lại giá trị ngoại hình với server
//        if(Pref.PREF_KEY_AVATAR!=null){
//            txtNgoaiHinh.setText(p.getString(Pref.PREF_KEY_AVATAR,null));
//        }
        if(p.getString(Pref.upb_appearance,null)!=null){
            if(p.getString(Pref.upb_appearance,null).equals("1")){
                checkBoxNgoaiHinh1.setChecked(true);
            }else if(p.getString(Pref.upb_appearance,null).equals("2")){
                checkBoxNgoaiHinh2.setChecked(true);
            }else if(p.getString(Pref.upb_appearance,null).equals("3")){
                checkBoxNgoaiHinh3.setChecked(true);
            }
        }
        Calendar newCalendar = Calendar.getInstance();
        if(p.getString(Pref.upb_dob,null).length()==10){
             yearBirth = p.getString(Pref.upb_dob,null).substring(0,4);
             monthBirth = p.getString(Pref.upb_dob,null).substring(5,7);
             dayBirth = p.getString(Pref.upb_dob,null).substring(8,10);
        }else {
             yearBirth = String.valueOf(newCalendar.get(Calendar.YEAR));
             monthBirth = String.valueOf(newCalendar.get(Calendar.MONTH));
             dayBirth = String.valueOf(newCalendar.get(Calendar.DAY_OF_MONTH));
        }
        toDatePickerDialog = new DatePickerDialog(mActivity, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                birthday = dateFormatter.format(newDate.getTime());
                txtInfoBirthday.setText(DateUtils.convertDateFormat(dateFormatter.format(newDate.getTime())));
            }

        },Integer.parseInt(yearBirth), Integer.parseInt(monthBirth)-1, Integer.parseInt(dayBirth));
        if(p.getString(Pref.upb_height,null)!=null){
            txtInfoHeight.setText(p.getString(Pref.upb_height,null));
        }
        if(p.getString(Pref.upb_weight,null)!=null){
            txtInfoWeight.setText(p.getString(Pref.upb_weight,null));
        }
        if(p.getString(Pref.upb_measurements,null)!=null){
//            txtInfoSoDo3Vong.setText(p.getString(Pref.upb_measurements,null));
            try {
                JSONObject jsonObjectSodo = new JSONObject(p.getString(Pref.upb_measurements,null));
                txtv1.setText(jsonObjectSodo.getString("v1"));
                txtv2.setText(jsonObjectSodo.getString("v2"));
                txtv3.setText(jsonObjectSodo.getString("v3"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if(p.getString(Pref.upb_hobbies,null)!=null){
            txtInfoHobbies.setText(p.getString(Pref.upb_hobbies,null));
        }
        if(p.getString(Pref.upb_speaks,null)!=null){
            txtInfoNoiDuocGiong.setText(p.getString(Pref.upb_speaks,null));
        }
        if(p.getString(Pref.upb_sings,null)!=null){
            txtInfoHatDuocGiong.setText(p.getString(Pref.upb_sings,null));
        }
        if(p.getString(Pref.upb_skills,null)!=null){
            txtInfoSkills.setText(p.getString(Pref.upb_skills,null));
        }
        if(p.getString(Pref.upb_special_skills,null)!=null){
            txtInfoKhaNangDacBiet.setText(p.getString(Pref.upb_special_skills,null));
        }

        txtInfoBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDatePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
                toDatePickerDialog.show();
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
                    mActivity.selectItem(7);
                    return true;
                }
                return false;
            }
        } );

        checkBoxSex1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxSex2.setChecked(false);
                checkBoxSex3.setChecked(false);
            }
        });
        checkBoxSex2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxSex1.setChecked(false);
                checkBoxSex3.setChecked(false);
            }
        });
        checkBoxSex3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxSex2.setChecked(false);
                checkBoxSex1.setChecked(false);
            }
        });
        checkBoxSex1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxSex2.setChecked(false);
                checkBoxSex3.setChecked(false);
            }
        });
        checkBoxSex2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxSex1.setChecked(false);
                checkBoxSex3.setChecked(false);
            }
        });
        checkBoxSex3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxSex2.setChecked(false);
                checkBoxSex1.setChecked(false);
            }
        });
        checkBoxSex1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxSex2.setChecked(false);
                checkBoxSex3.setChecked(false);
            }
        });
        checkBoxSex2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxSex1.setChecked(false);
                checkBoxSex3.setChecked(false);
            }
        });
        checkBoxSex3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxSex2.setChecked(false);
                checkBoxSex1.setChecked(false);
            }
        });
        checkBoxNgoaiHinh1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxNgoaiHinh2.setChecked(false);
                checkBoxNgoaiHinh3.setChecked(false);
            }
        });
        checkBoxNgoaiHinh2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxNgoaiHinh1.setChecked(false);
                checkBoxNgoaiHinh3.setChecked(false);
            }
        });
        checkBoxNgoaiHinh3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxNgoaiHinh2.setChecked(false);
                checkBoxNgoaiHinh1.setChecked(false);
            }
        });
        txtTextShowMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isShowMore){
                    layout_add_more.setVisibility(View.GONE);
                    txtTextShowMore.setText(mActivity.getString(R.string.show_more));
                    isShowMore = false;
                }else {
                    isShowMore = true;
                    layout_add_more.setVisibility(View.VISIBLE);
                    txtTextShowMore.setText(mActivity.getString(R.string.hideDetail));
                }
            }
        });
        return rootView;
    }
    public void showToolBarItem(){
        mActivity.showItemToolBar(false, false, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mActivity.showSaveItem(false,new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                if (id == R.id.txtSaveToolBar) {
                    if(checkBoxSex1.isChecked()){
                        gioitinh = "2";
                    }else if(checkBoxSex2.isChecked()){
                        gioitinh = "1";
                    }else if(checkBoxSex3.isChecked()){
                        gioitinh = "3";
                    }
                    if(checkBoxNgoaiHinh1.isChecked()){
                        ngoaihinh = "1";
                    }else if(checkBoxNgoaiHinh2.isChecked()){
                        ngoaihinh = "2";
                    }else if(checkBoxNgoaiHinh3.isChecked()){
                        ngoaihinh = "3";
                    }
                    JSONObject jsonObjectSodo = new JSONObject();
                    try {
                        jsonObjectSodo.put("v1",txtv1.getText().toString());
                        jsonObjectSodo.put("v2",txtv2.getText().toString());
                        jsonObjectSodo.put("v3",txtv3.getText().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    HttpUpdateProfileUser.updateProfileUser(txtInfoTen.getText().toString(),txtInfoHo.getText().toString(),txtInfoStatus.getText().toString(),p.getString(Pref.PREF_KEY_TOKEN, null),txtInfoEmail.getText().toString(),gioitinh,birthday,
                            txtInfoPhone.getText().toString(),txtInfoPlacesBirth.getText().toString(),txtInfoPlaces.getText().toString()
                            ,txtInfoHeight.getText().toString(),txtInfoWeight.getText().toString(),jsonObjectSodo.toString()
                            ,txtInfoHobbies.getText().toString(),txtInfoSkills.getText().toString(),txtInfoNoiDuocGiong.getText().toString()
                            ,null,null,txtInfoHatDuocGiong.getText().toString(),null,txtInfoKhaNangDacBiet.getText().toString(),null,
                            null,null,null,null,null,null,ngoaihinh,mActivity,p.getString(Pref.USER_ID, null),txtInfoDem.getText().toString());
                }
            }
        });
    }
}
