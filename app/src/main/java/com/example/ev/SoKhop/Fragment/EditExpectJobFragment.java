package com.example.ev.SoKhop.Fragment;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ev.SoKhop.Activity.InfomationActivity;
import com.example.ev.SoKhop.Base.BaseFragment;
import com.example.ev.SoKhop.Model.ExpectJobModel;
import com.example.ev.SoKhop.Model.LocationModel;
import com.example.ev.SoKhop.Model.PositionJobModel;
import com.example.ev.SoKhop.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MSI on 10/17/2016.
 */

public class EditExpectJobFragment extends BaseFragment {
    private static String salary_basic;
    private InfomationActivity activity;
    private String exp_id;

    public EditExpectJobFragment() {
    }

    private ArrayList<String> arrPositionJob = new ArrayList<>();
    private ArrayList<String> arrLocation = new ArrayList<>();
    private ArrayList<String> arrExpectJob = new ArrayList<>();
    private static List<PositionJobModel> positionJobModels;
    private static List<LocationModel> locationModels;
    private static List<ExpectJobModel> expectJobModels;
    private Button btnAdd,btnCancel;

    private TextView txtSuNghiep, txtMuctieunghenghiep, txtWorkPlaces, txtPosition, txtLuongcoban, txtThuong, txtPhucap, txtTongThuNhap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_edit_expect_jobs, container, false);
//        activity = (InfomationActivity)getActivity();
        positionJobModels = new ArrayList<>();
        positionJobModels = mActivity.databaseHelper.getAllPositionJobs();
        for (int i = 0; i < positionJobModels.size(); i++) {
            arrPositionJob.add(positionJobModels.get(i).getPos_value());
        }
        locationModels = new ArrayList<>();
        locationModels = mActivity.databaseHelper.getAllLocation();
        for (int i = 0; i < locationModels.size(); i++) {
            arrLocation.add(locationModels.get(i).getLocat_value());
        }

        expectJobModels = new ArrayList<>();
        expectJobModels = mActivity.databaseHelper.getAllExpectJob();

        btnCancel = (Button) rootView.findViewById(R.id.btnCancel);
        btnAdd = (Button) rootView.findViewById(R.id.btnAdd);
        final Spinner spPosition = (Spinner) rootView.findViewById(R.id.spPosition);
        final Spinner spCity = (Spinner)rootView.findViewById(R.id.spCity);
        final EditText edtTown = (EditText)rootView.findViewById(R.id.edtTown);
        final EditText edtStreet = (EditText)rootView.findViewById(R.id.edtStreet);
        final EditText edtNumber = (EditText)rootView.findViewById(R.id.edtNumber);
        final CheckBox cbUpLuongCoBan = (CheckBox)rootView.findViewById(R.id.cbUpLuongCoBan);
        final CheckBox cbExactlyLuongCoBan = (CheckBox)rootView.findViewById(R.id.cbExactlyLuongCoBan);
        final CheckBox cbDownLuongCoBan = (CheckBox)rootView.findViewById(R.id.cbDownLuongCoBan);
        final CheckBox cbBetweenLuongCoBan = (CheckBox)rootView.findViewById(R.id.cbBetweenLuongCoBan);
        final EditText edtUpLuongCoBan = (EditText)rootView.findViewById(R.id.edtUpLuongCoBan);
        final EditText edtBetweenLuongCoBanFrom = (EditText)rootView.findViewById(R.id.edtBetweenLuongCoBanFrom);
        final EditText edtBetweenLuongCoBanTo = (EditText)rootView.findViewById(R.id.edtBetweenLuongCoBanTo);
        final EditText edtDownLuongCoBan = (EditText)rootView.findViewById(R.id.edtDownLuongCoBan);
        final EditText edtExactlyLuongCoBan = (EditText)rootView.findViewById(R.id.edtExactlyLuongCoBan);
        final EditText edt_thuong = (EditText)rootView.findViewById(R.id.edt_thuong);
        final EditText edt_phucap = (EditText)rootView.findViewById(R.id.edt_phucap);
        final EditText edtMucTieuNgheNghiep = (EditText)rootView.findViewById(R.id.edtMucTieuNgheNghiep);
        final EditText edtSuNghiep = (EditText)rootView.findViewById(R.id.edtSuNghiep);
        EditText edt_tongthunhap = (EditText)rootView.findViewById(R.id.edt_tongthunhap);

        ArrayAdapter<String> adp = new ArrayAdapter<String>(mActivity,R.layout.spinner_item, arrPositionJob);
//        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPosition.setAdapter(adp);

        ArrayAdapter<String> adp2 = new ArrayAdapter<String>(mActivity,R.layout.spinner_item, arrLocation);
//        adp2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCity.setAdapter(adp2);

        showToolBarItem();
        rootView.setFocusableInTouchMode(true);
        rootView.requestFocus();
        rootView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    mActivity.selectItem(11);
                    return true;
                }
                return false;
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        return rootView;
    }

    public void showToolBarItem() {
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
//                    if(checkBoxSex1.isChecked()){
//                        gioitinh = "2";
//                    }else if(checkBoxSex2.isChecked()){
//                        gioitinh = "1";
//                    }else if(checkBoxSex3.isChecked()){
//                        gioitinh = "3";
//                    }
//                    if(checkBoxNgoaiHinh1.isChecked()){
//                        ngoaihinh = "1";
//                    }else if(checkBoxNgoaiHinh2.isChecked()){
//                        ngoaihinh = "2";
//                    }else if(checkBoxNgoaiHinh3.isChecked()){
//                        ngoaihinh = "3";
//                    }
//                    Log.d("TOKENSS","s"+ngoaihinh);
//                    HttpUpdateProfileUser.updateProfileUser(txtInfoTen.getText().toString(),txtInfoHo.getText().toString(),txtInfoStatus.getText().toString(),p.getString(Pref.PREF_KEY_TOKEN, null),txtInfoEmail.getText().toString(),gioitinh,txtInfoBirthday.getText().toString(),
//                            txtInfoPhone.getText().toString(),txtInfoPlaces.getText().toString(),txtInfoPlacesBirth.getText().toString()
//                            ,txtInfoHeight.getText().toString(),txtInfoWeight.getText().toString(),txtInfoSoDo3Vong.getText().toString()
//                            ,txtInfoHobbies.getText().toString(),txtInfoSkills.getText().toString(),txtInfoNoiDuocGiong.getText().toString()
//                            ,null,null,txtInfoHatDuocGiong.getText().toString(),null,txtInfoKhaNangDacBiet.getText().toString(),null,
//                            null,null,null,null,null,null,ngoaihinh,mActivity,p.getString(Pref.USER_ID, null),txtInfoDem.getText().toString());
                }
            }
        });
    }
}
