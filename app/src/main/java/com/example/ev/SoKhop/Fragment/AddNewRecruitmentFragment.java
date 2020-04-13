package com.example.ev.SoKhop.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ev.SoKhop.Api.HttpGetJobExcludes;
import com.example.ev.SoKhop.Api.HttpPostNewRecruitment;
import com.example.ev.SoKhop.Api.HttpUpdateStatusRecruitment;
import com.example.ev.SoKhop.Api.core.API;
import com.example.ev.SoKhop.Api.core.HttpResponseCode;
import com.example.ev.SoKhop.Base.BaseFragment;
import com.example.ev.SoKhop.Dialog.DialogCall;
import com.example.ev.SoKhop.Model.ExcludesModel;
import com.example.ev.SoKhop.Model.LanguageModel;
import com.example.ev.SoKhop.Model.LocationModel;
import com.example.ev.SoKhop.Model.PositionJobModel;
import com.example.ev.SoKhop.Model.RecruitmentModel;
import com.example.ev.SoKhop.R;
import com.example.ev.SoKhop.Utils.DateUtils;
import com.example.ev.SoKhop.Utils.Edittext;
import com.example.ev.SoKhop.Utils.GetTime;
import com.example.ev.SoKhop.Utils.Pref;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Created by MSI on 10/13/2016.
 */

public class AddNewRecruitmentFragment extends BaseFragment {
    private String statusCheck;
    private String quantity;
    private ArrayAdapter<CharSequence> adapterMonth,adapterYear,adapterDay;
    private String jcont_id = "";
    private String company_id;
    private Calendar newCalendar;
    private AutoCompleteTextView autocomplete_vitri,autocomplete_location;
    private String positionXemTruoc = "0";
    private boolean isDangTin = false;

    public AddNewRecruitmentFragment() {
    }

    private Spinner spXem,spUngTuyen,spPosition,spThoiGianLamViec,spSex,spNgoaiHinh,spKinhNghiem,spSucKhoe
            ,spNgay,spThang,spNam,spNgonNguHoSo,spCity;
    private Button btnDangTin,btnXemTruoc,btnLuuTinNhap,btnHuyTaoTin;
    private CheckBox cbExactlyPhuCap,cbDownPhuCap,cbBetweenPhuCap,cbUpPhuCap,cbExactlyThuong,cbDownThuong
            ,cbBetweenThuong,cbUpThuong,cbExactlyLuongCoBan,cbDownLuongCoBan,cbBetweenLuongCoBan,cbUpLuongCoBan
            ,cbExactlyHeight,cbDownHeight,cbBetweenHeight,cbUpHeight,cbExactly,cbDown,cbBetween,cbUp;
    private EditText title_recruitment,edtInfoRecruitment,edtSoLuongCanTuyen,edtCity,edtTown,edtStreet,edtNumber,edtMoTaCongViec
            ,edtUp,edtBetweenFrom,edtBetweenTo,edtDown,edtExactly,edtNoiSinh,edtHienTruTai,edtUpHeight,edtBetweenHeightFrom
            ,edtBetweenHeightTo,edtDownHeight,edtExactlyHeight,edtUpLuongCoBan,edtBetweenLuongCoBanFrom,edtBetweenLuongCoBanTo
            ,edtDownLuongCoBan,edtExactlyLuongCoBan,edtUpThuong,edtBetweenThuongFrom,edtBetweenThuongTo,edtDownThuong,edtExactlyThuong
            ,edtUpPhuCap,edtBetweenPhuCapFrom,edtBetweenPhuCapTo,edtDownPhuCap,edtExactlyPhuCap,edtSDTNguoiLienHe,edtTenNguoiLienHe,edtEmailNguoiLienHe,edtInfoPhucLoi
            ,edtExpYear,edtExpMonth;
    private TextView txtCheckedLanguage;
    private com.example.ev.SoKhop.Utils.AutoResizeTextView txtCheckedPosition;
    private ArrayList<String> arrCheDo = new ArrayList<>();
    private ArrayList<String> arrTimeWork = new ArrayList<>();
    private ArrayList<String> arrSex = new ArrayList<>();
    private ArrayList<String> arrNgoaiHinh = new ArrayList<>();
    private ArrayList<String> arrSucKhoe = new ArrayList<>();
    private ArrayList<String> arrExp = new ArrayList<>();
    private ArrayList<String> arrPositionJob = new ArrayList<>();
    private ArrayList<String> arrPositionJobId = new ArrayList<>();
    private ArrayList<String> arrLocation = new ArrayList<>();
    private ArrayList<String> arrLanguage = new ArrayList<>();
    private ArrayList<String> arrLanguage_id = new ArrayList<>();
    private String bonus,allowance,gender,salary,time_type,categories;
    private String job_id_check;
    private List<PositionJobModel> positionJobModels;
    private List<LanguageModel> languageModels;
    private List<LocationModel> locationModels;
    private List<RecruitmentModel> recruitmentModels ;
    private boolean isEdit = false;
    private ArrayAdapter<String> adapterCheDo,adapterTimeWork,adapterSex,adapterSucKhoe,adapterNgoaiHinh,adapterExp,adapterPosition,adapterNgonNguHoSo,adapterLocation;
    private RadioGroup rdbGrGioitinh,rdbGrTuoiTac,rdbGrKinhNghiem,rdbGrNoiSinh;
    private RadioButton rdbYeuCauNoiSinh,rdbLoaiTruNoiSinh,rdbYeuCauGioitinh,rdbLoaiTruGioitinh,rdbYeuCauTuoiTac,rdbLoaiTruTuoiTac,rdbYeuCauKinhNghiem,rdbLoaiTruKinhNghiem;
    private List<ExcludesModel> excludesModels;
    private String owner_id,owner_avatar,owner_name,owner_website,owner_phone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_add_new_recruitment, container, false);
        excludesModels = new ArrayList<>();
        recruitmentModels = new ArrayList<>();
        getExcludeJob(p.getString(Pref.PREF_KEY_TOKEN,null));

        newCalendar = Calendar.getInstance();

        txtCheckedLanguage = (TextView)rootView.findViewById(R.id.txtCheckedLanguage);
        txtCheckedPosition = (com.example.ev.SoKhop.Utils.AutoResizeTextView)rootView.findViewById(R.id.txtCheckedPosition);
        spXem = (Spinner)rootView.findViewById(R.id.spXem);
        spUngTuyen = (Spinner)rootView.findViewById(R.id.spUngTuyen);
        spPosition = (Spinner)rootView.findViewById(R.id.spPosition);
        spThoiGianLamViec = (Spinner)rootView.findViewById(R.id.spThoiGianLamViec);
        spSex = (Spinner)rootView.findViewById(R.id.spSex);
        spNgoaiHinh = (Spinner)rootView.findViewById(R.id.spNgoaiHinh);
        spKinhNghiem = (Spinner)rootView.findViewById(R.id.spKinhNghiem);
        spSucKhoe = (Spinner)rootView.findViewById(R.id.spSucKhoe);
        spNgay = (Spinner)rootView.findViewById(R.id.spNgay);
        spThang = (Spinner)rootView.findViewById(R.id.spThang);
        spNam = (Spinner)rootView.findViewById(R.id.spNam);
        spNgonNguHoSo = (Spinner)rootView.findViewById(R.id.spNgonNguHoSo);
        btnDangTin = (Button)rootView.findViewById(R.id.btnDangTin);
        btnXemTruoc = (Button)rootView.findViewById(R.id.btnXemTruoc);
        btnLuuTinNhap = (Button)rootView.findViewById(R.id.btnLuuTinNhap);
        btnHuyTaoTin = (Button)rootView.findViewById(R.id.btnHuyTaoTin);
        cbExactlyPhuCap = (CheckBox)rootView.findViewById(R.id.cbExactlyPhuCap);
        cbDownPhuCap = (CheckBox)rootView.findViewById(R.id.cbDownPhuCap);
        cbBetweenPhuCap = (CheckBox)rootView.findViewById(R.id.cbBetweenPhuCap);
        cbUpPhuCap = (CheckBox)rootView.findViewById(R.id.cbUpPhuCap);
        cbExactlyThuong = (CheckBox)rootView.findViewById(R.id.cbExactlyThuong);
        cbDownThuong = (CheckBox)rootView.findViewById(R.id.cbDownThuong);
        cbBetweenThuong = (CheckBox)rootView.findViewById(R.id.cbBetweenThuong);
        cbUpThuong = (CheckBox)rootView.findViewById(R.id.cbUpThuong);
        cbExactlyLuongCoBan = (CheckBox)rootView.findViewById(R.id.cbExactlyLuongCoBan);
        cbDownLuongCoBan = (CheckBox)rootView.findViewById(R.id.cbDownLuongCoBan);
        cbBetweenLuongCoBan = (CheckBox)rootView.findViewById(R.id.cbBetweenLuongCoBan);
        cbUpLuongCoBan = (CheckBox)rootView.findViewById(R.id.cbUpLuongCoBan);
        cbExactlyHeight = (CheckBox)rootView.findViewById(R.id.cbExactlyHeight);
        cbDownHeight = (CheckBox)rootView.findViewById(R.id.cbDownHeight);
        cbBetweenHeight = (CheckBox)rootView.findViewById(R.id.cbBetweenHeight);
        cbUpHeight = (CheckBox)rootView.findViewById(R.id.cbUpHeight);
        cbExactly = (CheckBox)rootView.findViewById(R.id.cbExactly);
        cbDown = (CheckBox)rootView.findViewById(R.id.cbDown);
        cbBetween = (CheckBox)rootView.findViewById(R.id.cbBetween);
        cbUp = (CheckBox)rootView.findViewById(R.id.cbUp);
        title_recruitment = (EditText)rootView.findViewById(R.id.title_recruitment);
        edtInfoRecruitment = (EditText)rootView.findViewById(R.id.edtInfoRecruitment);
        edtSoLuongCanTuyen = (EditText)rootView.findViewById(R.id.edtSoLuongCanTuyen);
        spCity = (Spinner) rootView.findViewById(R.id.spCity);
        edtTown = (EditText)rootView.findViewById(R.id.edtTown);
        edtStreet = (EditText)rootView.findViewById(R.id.edtStreet);
        edtNumber = (EditText)rootView.findViewById(R.id.edtNumber);
        edtMoTaCongViec = (EditText)rootView.findViewById(R.id.edtMoTaCongViec);
        edtUp = (EditText)rootView.findViewById(R.id.edtUp);
        edtBetweenFrom = (EditText)rootView.findViewById(R.id.edtBetweenFrom);
        edtBetweenTo = (EditText)rootView.findViewById(R.id.edtBetweenTo);
        edtDown = (EditText)rootView.findViewById(R.id.edtDown);
        edtExactly = (EditText)rootView.findViewById(R.id.edtExactly);
        edtNoiSinh = (EditText)rootView.findViewById(R.id.edtNoiSinh);
        edtHienTruTai = (EditText)rootView.findViewById(R.id.edtHienTruTai);
        edtUpHeight = (EditText)rootView.findViewById(R.id.edtUpHeight);
        edtBetweenHeightFrom = (EditText)rootView.findViewById(R.id.edtBetweenHeightFrom);
        edtBetweenHeightTo = (EditText)rootView.findViewById(R.id.edtBetweenHeightTo);
        edtDownHeight = (EditText)rootView.findViewById(R.id.edtDownHeight);
        edtExactlyHeight = (EditText)rootView.findViewById(R.id.edtExactlyHeight);
        edtUpLuongCoBan = (EditText)rootView.findViewById(R.id.edtUpLuongCoBan);
        edtBetweenLuongCoBanFrom = (EditText)rootView.findViewById(R.id.edtBetweenLuongCoBanFrom);
        edtBetweenLuongCoBanTo = (EditText)rootView.findViewById(R.id.edtBetweenLuongCoBanTo);
        edtDownLuongCoBan = (EditText)rootView.findViewById(R.id.edtDownLuongCoBan);
        edtExactlyLuongCoBan = (EditText)rootView.findViewById(R.id.edtExactlyLuongCoBan);
        edtUpThuong = (EditText)rootView.findViewById(R.id.edtUpThuong);
        edtBetweenThuongFrom = (EditText)rootView.findViewById(R.id.edtBetweenThuongFrom);
        edtBetweenThuongTo = (EditText)rootView.findViewById(R.id.edtBetweenThuongTo);
        edtDownThuong = (EditText)rootView.findViewById(R.id.edtDownThuong);
        edtExactlyThuong = (EditText)rootView.findViewById(R.id.edtExactlyThuong);
        edtUpPhuCap = (EditText)rootView.findViewById(R.id.edtUpPhuCap);
        edtBetweenPhuCapFrom = (EditText)rootView.findViewById(R.id.edtBetweenPhuCapFrom);
        edtBetweenPhuCapTo = (EditText)rootView.findViewById(R.id.edtBetweenPhuCapTo);
        edtDownPhuCap = (EditText)rootView.findViewById(R.id.edtDownPhuCap);
        edtExactlyPhuCap = (EditText)rootView.findViewById(R.id.edtExactlyPhuCap);
        edtSDTNguoiLienHe = (EditText)rootView.findViewById(R.id.edtSDTNguoiLienHe);
        edtTenNguoiLienHe = (EditText)rootView.findViewById(R.id.edtTenNguoiLienHe);
        edtEmailNguoiLienHe = (EditText)rootView.findViewById(R.id.edtEmailNguoiLienHe);
        edtInfoPhucLoi = (EditText)rootView.findViewById(R.id.edtInfoPhucLoi);

        rdbGrGioitinh = (RadioGroup) rootView.findViewById(R.id.rdbGrGioitinh);
        rdbGrTuoiTac = (RadioGroup) rootView.findViewById(R.id.rdbGrTuoiTac);
        rdbGrKinhNghiem = (RadioGroup) rootView.findViewById(R.id.rdbGrKinhNghiem);
        rdbGrNoiSinh = (RadioGroup) rootView.findViewById(R.id.rdbGrNoiSinh);
        rdbYeuCauNoiSinh = (RadioButton) rootView.findViewById(R.id.rdbYeuCauNoiSinh);
        rdbLoaiTruNoiSinh = (RadioButton) rootView.findViewById(R.id.rdbLoaiTruNoiSinh);
        rdbYeuCauGioitinh = (RadioButton) rootView.findViewById(R.id.rdbYeuCauGioitinh);
        rdbLoaiTruGioitinh = (RadioButton) rootView.findViewById(R.id.rdbLoaiTruGioitinh);
        rdbYeuCauTuoiTac = (RadioButton) rootView.findViewById(R.id.rdbYeuCauTuoiTac);
        rdbLoaiTruTuoiTac = (RadioButton) rootView.findViewById(R.id.rdbLoaiTruTuoiTac);
        rdbYeuCauKinhNghiem = (RadioButton) rootView.findViewById(R.id.rdbYeuCauKinhNghiem);
        rdbLoaiTruKinhNghiem = (RadioButton) rootView.findViewById(R.id.rdbLoaiTruKinhNghiem);
        autocomplete_vitri = (AutoCompleteTextView) rootView.findViewById(R.id.autocomplete_Vitri);
        autocomplete_location = (AutoCompleteTextView) rootView.findViewById(R.id.autocomplete_Loca);
        edtExpYear = (EditText)rootView.findViewById(R.id.edtExpYear);
        edtExpMonth = (EditText)rootView.findViewById(R.id.edtExpMonth);

        Edittext.addCommasToEdittext(edtUpLuongCoBan);
        Edittext.addCommasToEdittext(edtBetweenLuongCoBanFrom);
        Edittext.addCommasToEdittext(edtBetweenLuongCoBanTo);
        Edittext.addCommasToEdittext(edtDownLuongCoBan);
        Edittext.addCommasToEdittext(edtExactlyLuongCoBan);
        Edittext.addCommasToEdittext(edtUpThuong);
        Edittext.addCommasToEdittext(edtBetweenThuongFrom);
        Edittext.addCommasToEdittext(edtBetweenThuongTo);
        Edittext.addCommasToEdittext(edtDownThuong);
        Edittext.addCommasToEdittext(edtExactlyThuong);
        Edittext.addCommasToEdittext(edtUpPhuCap);
        Edittext.addCommasToEdittext(edtBetweenPhuCapFrom);
        Edittext.addCommasToEdittext(edtBetweenPhuCapTo);
        Edittext.addCommasToEdittext(edtDownPhuCap);
        Edittext.addCommasToEdittext(edtExactlyPhuCap);

        autocomplete_vitri.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                txtCheckedPosition.setText(autocomplete_vitri.getText().toString());
            }
        });
//        spPosition.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                txtCheckedPosition.setText(spPosition.getSelectedItem().toString());
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
        spNgonNguHoSo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                txtCheckedLanguage.setText(spNgonNguHoSo.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        positionJobModels = new ArrayList<>();
        positionJobModels = mActivity.databaseHelper.getAllPositionJobs();
        for(int i =0;i<positionJobModels.size();i++){
            arrPositionJob.add(positionJobModels.get(i).getPos_value());
            arrPositionJobId.add(String.valueOf(positionJobModels.get(i).getPos_id()));
        }

        languageModels = new ArrayList<>();
        languageModels = mActivity.databaseHelper.getAllLanguage();
        for(int i =0;i<languageModels.size();i++){
            arrLanguage.add(languageModels.get(i).getLang_name());
            arrLanguage_id.add(languageModels.get(i).getLang_code());
        }
        locationModels = new ArrayList<>();
        locationModels = mActivity.databaseHelper.getAllLocation();
        for(int i =0;i<locationModels.size();i++){
            arrLocation.add(locationModels.get(i).getLocat_value());
        }
        init();

        Edittext.setCheckBox(cbUpLuongCoBan,edtUpLuongCoBan,edtDownLuongCoBan,edtBetweenLuongCoBanFrom,edtBetweenLuongCoBanTo,edtExactlyLuongCoBan,null);
        Edittext.setCheckBox(cbUp,edtUp,edtDown,edtBetweenFrom,edtBetweenTo,edtExactly,null);
        Edittext.setCheckBox(cbUpHeight,edtUpHeight,edtDownHeight,edtBetweenHeightFrom,edtBetweenHeightTo,edtExactlyHeight,null);
        Edittext.setCheckBox(cbUpPhuCap,edtUpPhuCap,edtDownPhuCap,edtBetweenPhuCapFrom,edtBetweenPhuCapTo,edtExactlyPhuCap,null);
        Edittext.setCheckBox(cbUpThuong,edtUpThuong,edtDownThuong,edtBetweenThuongFrom,edtBetweenThuongTo,edtExactlyThuong,null);
        Edittext.setCheckBox(cbDown,edtDown,edtUp,edtBetweenFrom,edtBetweenTo,edtExactly,null);
        Edittext.setCheckBox(cbDownHeight,edtDownHeight,edtUpHeight,edtBetweenHeightFrom,edtBetweenHeightTo,edtExactlyHeight,null);
        Edittext.setCheckBox(cbDownLuongCoBan,edtDownLuongCoBan,edtUpLuongCoBan,edtBetweenLuongCoBanFrom,edtBetweenLuongCoBanTo,edtExactlyLuongCoBan,null);
        Edittext.setCheckBox(cbDownPhuCap,edtDownPhuCap,edtUpPhuCap,edtBetweenPhuCapFrom,edtBetweenPhuCapTo,edtExactlyPhuCap,null);
        Edittext.setCheckBox(cbDownThuong,edtDownThuong,edtUpThuong,edtBetweenThuongFrom,edtBetweenThuongTo,edtExactlyThuong,null);
        Edittext.setCheckBox(cbExactly,edtExactly,edtDown,edtBetweenFrom,edtBetweenTo,edtUp,null);
        Edittext.setCheckBox(cbExactlyHeight,edtExactlyHeight,edtDownHeight,edtBetweenHeightFrom,edtBetweenHeightTo,edtUpHeight,null);
        Edittext.setCheckBox(cbExactlyLuongCoBan,edtExactlyLuongCoBan,edtDownLuongCoBan,edtBetweenLuongCoBanFrom,edtBetweenLuongCoBanTo,edtUpLuongCoBan,null);
        Edittext.setCheckBox(cbExactlyPhuCap,edtExactlyPhuCap,edtUpPhuCap,edtDownPhuCap,edtBetweenPhuCapFrom,edtBetweenPhuCapTo,null);
        Edittext.setCheckBox(cbExactlyThuong,edtExactlyThuong,edtDownThuong,edtBetweenThuongFrom,edtBetweenThuongTo,edtUpThuong,null);
        Edittext.setCheckBox(cbBetween,edtBetweenFrom,edtDown,edtUp,edtExactly,edtExactly,edtBetweenTo);
        Edittext.setCheckBox(cbBetweenHeight,edtBetweenHeightFrom,edtDownHeight,edtUpHeight,edtExactlyHeight,edtExactlyHeight,edtBetweenHeightTo);
        Edittext.setCheckBox(cbBetweenLuongCoBan,edtBetweenLuongCoBanFrom,edtDownLuongCoBan,edtUpLuongCoBan,edtExactlyLuongCoBan,edtExactlyLuongCoBan,edtBetweenLuongCoBanTo);
        Edittext.setCheckBox(cbBetweenPhuCap,edtBetweenPhuCapFrom,edtUpPhuCap,edtDownPhuCap,edtExactlyPhuCap,edtExactlyPhuCap,edtBetweenPhuCapTo);
        Edittext.setCheckBox(cbBetweenThuong,edtBetweenThuongFrom,edtDownThuong,edtUpThuong,edtExactlyThuong,edtExactlyThuong,edtBetweenThuongTo);
        Edittext.setCheckBox(cbBetween,edtBetweenTo,edtDown,edtExactly,edtUp,edtExactly,edtBetweenFrom);
        Edittext.setCheckBox(cbBetweenHeight,edtBetweenHeightTo,edtDownHeight,edtExactlyHeight,edtUpHeight,edtExactlyHeight,edtBetweenHeightFrom);
        Edittext.setCheckBox(cbBetweenLuongCoBan,edtBetweenLuongCoBanTo,edtDownLuongCoBan,edtExactlyLuongCoBan,edtUpLuongCoBan,edtExactlyLuongCoBan,edtBetweenLuongCoBanFrom);
        Edittext.setCheckBox(cbBetweenPhuCap,edtBetweenPhuCapTo,edtUpPhuCap,edtDownPhuCap,edtExactlyPhuCap,edtExactlyPhuCap,edtBetweenPhuCapFrom);
        Edittext.setCheckBox(cbBetweenThuong,edtBetweenThuongTo,edtDownThuong,edtExactlyThuong,edtUpThuong,edtExactlyThuong,edtBetweenThuongFrom);
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
                    mActivity.selectItem(17);
                    return true;
                }
                return false;
            }
        } );
        checkBoxSet(cbUpPhuCap,cbBetweenPhuCap,cbDownPhuCap,cbExactlyPhuCap);
        checkBoxSet(cbUp,cbBetween,cbDown,cbExactly);
        checkBoxSet(cbUpHeight,cbBetweenHeight,cbDownHeight,cbExactlyHeight);
        checkBoxSet(cbUpLuongCoBan,cbBetweenLuongCoBan,cbDownLuongCoBan,cbExactlyLuongCoBan);
        checkBoxSet(cbUpThuong,cbBetweenThuong,cbDownThuong,cbExactlyThuong );

        btnXemTruoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String token = mActivity.p.getString(Pref.PREF_KEY_TOKEN,null);
                for(int i = 0;i<arrPositionJob.size();i++){
                    if(arrPositionJob.get(i).equals(autocomplete_vitri.getText().toString())){
                        positionXemTruoc = arrPositionJobId.get(i);
                    }
                }
                String title = title_recruitment.getText().toString();
                String company_id = "";
//                if(spThoiGianLamViec.getSelectedItem().toString().equals(getString(R.string.fulltime))){
//                     categories = "2";
//                }else {
//                     categories = "1";
//                }
                categories ="";
                if(edtSoLuongCanTuyen.getText().toString().length()>0){
                    quantity = edtSoLuongCanTuyen.getText().toString();
                }else {
                    quantity = "0";
                }
                String level = "";
                if(spThoiGianLamViec.getSelectedItem().toString().equals(getString(R.string.fulltime))){
                    time_type = "2";
                }else {
                    time_type = "1";
                }
                String range_salary_id = "";
                if(cbUpLuongCoBan.isChecked()){
                    salary = "> "+edtUpLuongCoBan.getText().toString().replaceAll(",","");
                }else
                if(cbBetweenLuongCoBan.isChecked()){
                    salary = edtBetweenLuongCoBanFrom.getText().toString().replaceAll(",","") + " - " + edtBetweenLuongCoBanTo.getText().toString().replaceAll(",","");
                }else
                if(cbDownLuongCoBan.isChecked()){
                    salary = "< "+edtDownLuongCoBan.getText().toString().replaceAll(",","");
                }else
                if(cbExactlyLuongCoBan.isChecked()){
                    salary = edtExactlyLuongCoBan.getText().toString().replaceAll(",","");
                }else {
                    salary = "";
                }
                JSONArray jsonArrayLocation = new JSONArray();
                JSONObject jsonObjectLocation = new JSONObject();
                try {
//                    for(int i =0;i<locationModels.size();i++){
//                        if(locationModels.get(i).getLocat_value().equals(autocomplete_location.getText().toString())){
//                            jsonObjectLocation.put("province_id", locationModels.get(i).getLocat_id());
//                        }
//                    }
//                    jsonObjectLocation.put("province_value", locationModels.get(spCity.getSelectedItemPosition()).getLocat_id());
                    jsonObjectLocation.put("province_name", autocomplete_location.getText().toString());
                    jsonObjectLocation.put("province_value", autocomplete_location.getText().toString());
                    jsonObjectLocation.put("district", edtTown.getText().toString());
                    jsonObjectLocation.put("street", edtStreet.getText().toString());
                    jsonObjectLocation.put("number", edtNumber.getText().toString());
                    jsonArrayLocation.put(jsonObjectLocation);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String work_location = jsonArrayLocation.toString();
                if(spSex.getSelectedItem().toString().equals(getString(R.string.male))){
                    gender = "1";
                }else if(spSex.getSelectedItem().toString().equals(getString(R.string.remale))){
                    gender = "2";
                }else {
                    gender = "3";
                }
                String description = edtMoTaCongViec.getText().toString();
                String range_allowance_id = "";
                if(cbUpPhuCap.isChecked()){
                    allowance = "> "+edtUpPhuCap.getText().toString().replaceAll(",","").replaceAll("\\.","");
                }else
                if(cbBetweenPhuCap.isChecked()){
                    allowance = edtBetweenPhuCapFrom.getText().toString().replaceAll(",","").replaceAll("\\.","") + " - " + edtBetweenPhuCapTo.getText().toString().replaceAll(",","").replaceAll("\\.","");
                }else
                if(cbDownPhuCap.isChecked()){
                    allowance = "< "+edtDownPhuCap.getText().toString().replaceAll(",","").replaceAll("\\.","");
                }else
                if(cbExactlyPhuCap.isChecked()){
                    allowance = edtExactlyPhuCap.getText().toString().replaceAll(",","").replaceAll("\\.","");
                }else {
                    allowance = "";
                }
                String range_bonus_id = "";
                if(cbUpThuong.isChecked()){
                    bonus = "> "+edtUpThuong.getText().toString().replaceAll(",","").replaceAll("\\.","");
                }else
                if(cbBetweenThuong.isChecked()){
                    bonus = edtBetweenThuongFrom.getText().toString().replaceAll(",","").replaceAll("\\.","") + " - " + edtBetweenThuongTo.getText().toString().replaceAll(",","").replaceAll("\\.","");
                }else
                if(cbDownThuong.isChecked()){
                    bonus = "< "+edtDownThuong.getText().toString().replaceAll(",","").replaceAll("\\.","");
                }else
                if(cbExactlyThuong.isChecked()){
                    bonus = edtExactlyThuong.getText().toString().replaceAll(",","").replaceAll("\\.","");
                }else {
                    bonus = "";
                }
                String extra_desc = edtInfoRecruitment.getText().toString();
                String expired_time = spNam.getSelectedItem().toString() + "-" + spThang.getSelectedItem().toString().replace("Th.","") + "-" + spNgay.getSelectedItem().toString();
                String languages_profile = languageModels.get(spNgonNguHoSo.getSelectedItemPosition()).getLang_code();
                String language_name = spNgonNguHoSo.getSelectedItem().toString();
                String language_id = arrLanguage_id.get(spNgonNguHoSo.getSelectedItemPosition());
                String album_image_id = "";
                String album_contract_id = "";
                String skills = "";
                String year_exps = "";
                if(edtExpYear.length()==0&&edtExpMonth.length()!=0){
                    year_exps = "0 "+ mActivity.getString(R.string.year)+ " "+edtExpMonth.getText().toString() +" "+mActivity.getString(R.string.month);
                }else if(edtExpMonth.length()==0&&edtExpYear.length()!=0){
                    year_exps = edtExpYear.getText().toString() + " "+ mActivity.getString(R.string.year)+ "0 "+mActivity.getString(R.string.month);
                }else if(edtExpMonth.length()==0&&edtExpYear.length()==0){
                    year_exps = "0 "+ mActivity.getString(R.string.year)+ "0 "+mActivity.getString(R.string.month);
                }else {
                    year_exps = edtExpYear.getText().toString() + " "+ mActivity.getString(R.string.year)+ " "+edtExpMonth.getText().toString() +" "+mActivity.getString(R.string.month);
                }

                int selectedIdGioitinh = rdbGrGioitinh.getCheckedRadioButtonId();
                int selectedIdKinhNghiem = rdbGrKinhNghiem.getCheckedRadioButtonId();
                int selectedIdTuoiTac = rdbGrTuoiTac.getCheckedRadioButtonId();
                int selectedIdNoiSinh = rdbGrNoiSinh.getCheckedRadioButtonId();
                JSONArray exclude_conditions = new JSONArray();
                JSONArray require_conditions = new JSONArray();
                JSONObject jsonObjectGioitinh = new JSONObject();
                JSONObject jsonObjectKinhNghiem = new JSONObject();
                JSONObject jsonObjectTuoiTac = new JSONObject();
                JSONObject jsonObjectNoiSinh = new JSONObject();
//                if(selectedIdKinhNghiem == rdbYeuCauKinhNghiem.getId()){
//                    try {
//                        jsonObjectKinhNghiem.put("jexca_id","3");
//                        jsonObjectKinhNghiem.put("jreqco_value",spKinhNghiem.getSelectedItem().toString());
//                        require_conditions.put(jsonObjectKinhNghiem);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }else {
//                    try {
//                        jsonObjectKinhNghiem.put("jexca_id","3");
//                        jsonObjectKinhNghiem.put("jexcon_value",spKinhNghiem.getSelectedItem().toString());
//                        exclude_conditions.put(jsonObjectKinhNghiem);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
                if(selectedIdNoiSinh == rdbYeuCauNoiSinh.getId()){
                    try {
                        jsonObjectNoiSinh.put("jexca_id","4");
                        jsonObjectNoiSinh.put("jreqco_value",edtNoiSinh.getText().toString());
                        require_conditions.put(jsonObjectNoiSinh);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        jsonObjectNoiSinh.put("jexca_id","4");
                        jsonObjectNoiSinh.put("jexcon_value",edtNoiSinh.getText().toString());
                        exclude_conditions.put(jsonObjectNoiSinh);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if(selectedIdTuoiTac == rdbYeuCauTuoiTac.getId()){
                    try {
                        jsonObjectTuoiTac.put("jexca_id","2");
                        if(cbUp.isChecked()){
                            jsonObjectTuoiTac.put("jreqco_value","> "+edtUp.getText().toString());
                        }else if(cbBetween.isChecked()){
                            jsonObjectTuoiTac.put("jreqco_value",edtBetweenFrom.getText().toString() + " - "+edtBetweenTo.getText().toString());
                        }else if(cbDown.isChecked()){
                            jsonObjectTuoiTac.put("jreqco_value","< "+edtDown.getText().toString());
                        }else if(cbExactly.isChecked()){
                            jsonObjectTuoiTac.put("jreqco_value",edtExactly.getText().toString());
                        }
                        require_conditions.put(jsonObjectTuoiTac);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        jsonObjectTuoiTac.put("jexca_id","2");
                        if(cbUp.isChecked()){
                            jsonObjectTuoiTac.put("jexcon_value","> "+edtUp.getText().toString());
                        }else if(cbBetween.isChecked()){
                            jsonObjectTuoiTac.put("jexcon_value",edtBetweenFrom.getText().toString() + " - "+edtBetweenTo.getText().toString());
                        }else if(cbDown.isChecked()){
                            jsonObjectTuoiTac.put("jexcon_value","< "+edtDown.getText().toString());
                        }else if(cbExactly.isChecked()){
                            jsonObjectTuoiTac.put("jexcon_value",edtExactly.getText().toString());
                        }
                        exclude_conditions.put(jsonObjectTuoiTac);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if(selectedIdGioitinh == rdbYeuCauGioitinh.getId()){
                    try {
                        jsonObjectGioitinh.put("jexca_id","1");
                        jsonObjectGioitinh.put("jreqco_value",spSex.getSelectedItem().toString());
                        require_conditions.put(jsonObjectGioitinh);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        jsonObjectGioitinh.put("jexca_id","1");
                        jsonObjectGioitinh.put("jexcon_value",spSex.getSelectedItem().toString());
                        exclude_conditions.put(jsonObjectGioitinh);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

//                String exclude_conditions = "";
//                String require_conditions = "";
                JSONArray jsonArrayContact = new JSONArray();
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("contact_position","");
                    jsonObject.put("jcont_name",edtTenNguoiLienHe.getText().toString());
                    jsonObject.put("jcont_phone",edtSDTNguoiLienHe.getText().toString());
                    jsonObject.put("jcont_email",edtEmailNguoiLienHe.getText().toString());
                    jsonArrayContact.put(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String contact = jsonArrayContact.toString();
                String status = "2";
                String job_welfare = edtInfoPhucLoi.getText().toString();
                String job_id = "";
                if(isEdit){
                    job_id = job_id_check;
                }else {
                    job_id = "999";
                }

                if(mActivity.isCaNhan){
                    owner_id = p.getString(Pref.USER_ID,null);
                    owner_avatar = p.getString(Pref.PREF_KEY_AVATAR,null);
                    owner_name = p.getString(Pref.upb_full_name,null);
                    owner_website = "";
                    owner_phone = p.getString(Pref.upb_phone,null);
                }else {
                    owner_id = p.getString(Pref.USER_ID,null);
                    owner_avatar = p.getString(Pref.com_avatar_url,null);
                    owner_name = p.getString(Pref.com_name,null);
                    owner_website = p.getString(Pref.com_website,null);
                    owner_phone = p.getString(Pref.com_phone,null);
                }
                String is_user_saved = "";
                String number_matching = "";
                String is_user_apply = "";

                if(DateUtils.isFuture(spNam.getSelectedItem().toString(),spThang.getSelectedItem().toString().replace("Th.",""),spNgay.getSelectedItem().toString())){
                    mActivity.databaseHelper.deleteARecruitmentByPosId(job_id);
                    mActivity.databaseHelper.insertARecruitment(job_id, company_id, owner_id, "", positionXemTruoc, positionXemTruoc, quantity,
                            time_type, "", gender, "", "", title, "",
                            "", title, languages_profile, languages_profile, "", "", GetTime.dateTimeToday(), GetTime.dateTimeToday()
                            , autocomplete_vitri.getText().toString(), autocomplete_vitri.getText().toString(), "", "", "", salary, work_location
                            , description, allowance, bonus, extra_desc, expired_time, languages_profile, ""
                            , "", "", year_exps, GetTime.dateTimeToday(), GetTime.dateTimeToday(), language_name, language_id
                            , GetTime.dateTimeToday(), GetTime.dateTimeToday(), exclude_conditions.toString(), require_conditions.toString(), work_location.toString(), job_welfare, contact
                            , owner_id, owner_avatar, owner_name, owner_website, owner_phone, is_user_saved, number_matching,is_user_apply);
                    mActivity.p.putString(Pref.PREF_KEY_JOB_ID,job_id);
                    mActivity.p.putString(Pref.PREF_KEY_VIEW_NEWS_BEFORE,"1");
                    mActivity.selectItem(20);
                }else {
                    DialogCall.showResponse(mActivity, mActivity.getString(R.string.thoihanungtuyen_error), new View.OnClickListener() {
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
        btnDangTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String token = mActivity.p.getString(Pref.PREF_KEY_TOKEN,null);
                String position = "";
                JSONArray jsonArrayPos = new JSONArray();
                JSONObject jsonObjectPos = new JSONObject();
                try {
                    String poss = "";
                    String pos_name = autocomplete_vitri.getText().toString();
                    String pos_value = autocomplete_vitri.getText().toString();
                    for(int i = 0;i<arrPositionJob.size();i++){
                        if(arrPositionJob.get(i).equals(autocomplete_vitri.getText().toString())){
                            poss = arrPositionJobId.get(i);
                            pos_name = autocomplete_vitri.getText().toString();
                            pos_value = autocomplete_vitri.getText().toString();
                        }
                    }
                    jsonObjectPos.put("pos_id",poss);
                    jsonObjectPos.put("pos_name",pos_name);
                    jsonObjectPos.put("pos_value",pos_value);
                    jsonArrayPos.put(jsonObjectPos);
                    position = jsonArrayPos.toString();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String title = title_recruitment.getText().toString();
                if(mActivity.isCaNhan){
                    company_id = "";
                }else {
                    company_id = p.getString(Pref.com_id,null);
                }

//                if(spThoiGianLamViec.getSelectedItem().toString().equals(getString(R.string.fulltime))){
//                     categories = "2";
//                }else {
//                     categories = "1";
//                }
                categories ="";
                if(edtSoLuongCanTuyen.getText().toString().length()>0){
                    quantity = edtSoLuongCanTuyen.getText().toString();
                }else {
                    quantity = "0";
                }
                String level = "";
                if(spThoiGianLamViec.getSelectedItem().toString().equals(getString(R.string.fulltime))){
                    time_type = "2";
                }else {
                    time_type = "1";
                }
                String range_salary_id = "";
                if(cbUpLuongCoBan.isChecked()){
                    salary = "> "+edtUpLuongCoBan.getText().toString().replaceAll(",","").replaceAll("\\.","");
                }else
                if(cbBetweenLuongCoBan.isChecked()){
                    salary = edtBetweenLuongCoBanFrom.getText().toString().replaceAll(",","").replaceAll("\\.","") + " - " + edtBetweenLuongCoBanTo.getText().toString().replaceAll(",","").replaceAll("\\.","");
                }else
                if(cbDownLuongCoBan.isChecked()){
                    salary = "< "+edtDownLuongCoBan.getText().toString().replaceAll(",","").replaceAll("\\.","");
                }else
                if(cbExactlyLuongCoBan.isChecked()){
                    salary = edtExactlyLuongCoBan.getText().toString().replaceAll(",","").replaceAll("\\.","");
                }else {
                    salary = "";
                }
                JSONArray jsonArrayLocation = new JSONArray();
                JSONObject jsonObjectLocation = new JSONObject();
                try {
                    String province_id = "";
                    for(int i =0;i<locationModels.size();i++){
                        if(locationModels.get(i).getLocat_value().equals(autocomplete_location.getText().toString())){
                            province_id = String.valueOf(locationModels.get(i).getLocat_id());
                        }
                    }
                    jsonObjectLocation.put("province_id",province_id);
                    jsonObjectLocation.put("province_value", autocomplete_location.getText().toString());
                    jsonObjectLocation.put("district", edtTown.getText().toString());
                    jsonObjectLocation.put("street", edtStreet.getText().toString());
                    jsonObjectLocation.put("number", edtNumber.getText().toString());
                    jsonArrayLocation.put(jsonObjectLocation);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String work_location = jsonArrayLocation.toString();
                if(spSex.getSelectedItem().toString().equals(getString(R.string.male))){
                    gender = "1";
                }else if(spSex.getSelectedItem().toString().equals(getString(R.string.remale))){
                    gender = "2";
                }else {
                    gender = "3";
                }
                String description = edtMoTaCongViec.getText().toString();
                String range_allowance_id = "";
                if(cbUpPhuCap.isChecked()){
                    allowance = "> "+edtUpPhuCap.getText().toString().replaceAll(",","").replaceAll("\\.","");
                }else
                if(cbBetweenPhuCap.isChecked()){
                    allowance = edtBetweenPhuCapFrom.getText().toString().replaceAll(",","").replaceAll("\\.","") + " - " + edtBetweenPhuCapTo.getText().toString().replaceAll(",","").replaceAll("\\.","");
                }else
                if(cbDownPhuCap.isChecked()){
                    allowance = "< "+edtDownPhuCap.getText().toString().replaceAll(",","").replaceAll("\\.","");
                }else
                if(cbExactlyPhuCap.isChecked()){
                    allowance = edtExactlyPhuCap.getText().toString().replaceAll(",","").replaceAll("\\.","");
                }else {
                    allowance = "";
                }
                String range_bonus_id = "";
                if(cbUpThuong.isChecked()){
                    bonus = "> "+edtUpThuong.getText().toString().replaceAll(",","").replaceAll("\\.","");
                }else
                if(cbBetweenThuong.isChecked()){
                    bonus = edtBetweenThuongFrom.getText().toString().replaceAll(",","").replaceAll("\\.","") + " - " + edtBetweenThuongTo.getText().toString().replaceAll(",","").replaceAll("\\.","");
                }else
                if(cbDownThuong.isChecked()){
                    bonus = "< "+edtDownThuong.getText().toString().replaceAll(",","").replaceAll("\\.","");
                }else
                if(cbExactlyThuong.isChecked()){
                    bonus = edtExactlyThuong.getText().toString().replaceAll(",","").replaceAll("\\.","");
                }else {
                    bonus = "";
                }
                String extra_desc = edtInfoRecruitment.getText().toString();
                String expired_time = spNam.getSelectedItem().toString() + "-" + spThang.getSelectedItem().toString().replace("Th.","") + "-" + spNgay.getSelectedItem().toString();
                String languages_profile = languageModels.get(spNgonNguHoSo.getSelectedItemPosition()).getLang_code();
                String album_image_id = "";
                String album_contract_id = "";
                String skills = "";
                String year_exps = "";
                if(edtExpYear.length()==0&&edtExpMonth.length()!=0){
                    year_exps = "0 "+ mActivity.getString(R.string.year)+ " "+edtExpMonth.getText().toString() +" "+mActivity.getString(R.string.month);
                }else if(edtExpMonth.length()==0&&edtExpYear.length()!=0){
                    year_exps = edtExpYear.getText().toString() + " "+ mActivity.getString(R.string.year)+ "0 "+mActivity.getString(R.string.month);
                }else if(edtExpMonth.length()==0&&edtExpYear.length()==0){
                    year_exps = "0 "+ mActivity.getString(R.string.year)+ "0 "+mActivity.getString(R.string.month);
                }else {
                    year_exps = edtExpYear.getText().toString() + " "+ mActivity.getString(R.string.year)+ " "+edtExpMonth.getText().toString() +" "+mActivity.getString(R.string.month);
                }
                int selectedIdGioitinh = rdbGrGioitinh.getCheckedRadioButtonId();
                int selectedIdKinhNghiem = rdbGrKinhNghiem.getCheckedRadioButtonId();
                int selectedIdTuoiTac = rdbGrTuoiTac.getCheckedRadioButtonId();
                int selectedIdNoiSinh = rdbGrNoiSinh.getCheckedRadioButtonId();
                JSONArray exclude_conditions = new JSONArray();
                JSONArray require_conditions = new JSONArray();
                JSONObject jsonObjectGioitinh = new JSONObject();
                JSONObject jsonObjectKinhNghiem = new JSONObject();
                JSONObject jsonObjectTuoiTac = new JSONObject();
                JSONObject jsonObjectNoiSinh = new JSONObject();
//                if(selectedIdKinhNghiem != rdbYeuCauKinhNghiem.getId()){
//                    try {
//                        jsonObjectKinhNghiem.put("exclude_cate_id","3");
//                        jsonObjectKinhNghiem.put("value",spKinhNghiem.getSelectedItem().toString());
//                        exclude_conditions.put(jsonObjectKinhNghiem);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }else {
//                    try {
//                        jsonObjectKinhNghiem.put("require_cate_id","3");
//                        jsonObjectKinhNghiem.put("value",spKinhNghiem.getSelectedItem().toString());
//                        require_conditions.put(jsonObjectKinhNghiem);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
                if(selectedIdNoiSinh != rdbYeuCauNoiSinh.getId()){
                    try {
                        jsonObjectNoiSinh.put("exclude_cate_id","4");
                        jsonObjectNoiSinh.put("value",edtNoiSinh.getText().toString());
                        exclude_conditions.put(jsonObjectNoiSinh);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        jsonObjectNoiSinh.put("require_cate_id","4");
                        jsonObjectNoiSinh.put("value",edtNoiSinh.getText().toString());
                        require_conditions.put(jsonObjectNoiSinh);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if(selectedIdTuoiTac != rdbYeuCauTuoiTac.getId()){
                    try {
                        jsonObjectTuoiTac.put("exclude_cate_id","2");
                        if(cbUp.isChecked()){
                            jsonObjectTuoiTac.put("value","> "+edtUp.getText().toString());
                        }else if(cbBetween.isChecked()){
                            jsonObjectTuoiTac.put("value",edtBetweenFrom.getText().toString() + " - "+edtBetweenTo.getText().toString());
                        }else if(cbDown.isChecked()){
                            jsonObjectTuoiTac.put("value","< "+edtDown.getText().toString());
                        }else if(cbExactly.isChecked()){
                            jsonObjectTuoiTac.put("value",edtExactly.getText().toString());
                        }
                        exclude_conditions.put(jsonObjectTuoiTac);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        jsonObjectTuoiTac.put("require_cate_id","2");
                        if(cbUp.isChecked()){
                            jsonObjectTuoiTac.put("value","> "+edtUp.getText().toString());
                        }else if(cbBetween.isChecked()){
                            jsonObjectTuoiTac.put("value",edtBetweenFrom.getText().toString() + " - "+edtBetweenTo.getText().toString());
                        }else if(cbDown.isChecked()){
                            jsonObjectTuoiTac.put("value","< "+edtDown.getText().toString());
                        }else if(cbExactly.isChecked()){
                            jsonObjectTuoiTac.put("value",edtExactly.getText().toString());
                        }
                        require_conditions.put(jsonObjectTuoiTac);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if(selectedIdGioitinh != rdbYeuCauGioitinh.getId()){
                    try {
                        jsonObjectGioitinh.put("exclude_cate_id","1");
                        jsonObjectGioitinh.put("value",spSex.getSelectedItem().toString());
                        exclude_conditions.put(jsonObjectGioitinh);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        jsonObjectGioitinh.put("require_cate_id","1");
                        jsonObjectGioitinh.put("value",spSex.getSelectedItem().toString());
                        require_conditions.put(jsonObjectGioitinh);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                JSONArray jsonArrayContact = new JSONArray();
                JSONObject jsonObject = new JSONObject();
                try {
                    if(isEdit) {
                        jsonObject.put("contact_position", "");
                        jsonObject.put("contact_name", edtTenNguoiLienHe.getText().toString());
                        jsonObject.put("contact_phone", edtSDTNguoiLienHe.getText().toString());
                        jsonObject.put("contact_email", edtEmailNguoiLienHe.getText().toString());
                        jsonObject.put("contact_id", jcont_id);
                    }else {
                        jsonObject.put("contact_position", "");
                        jsonObject.put("contact_name", edtTenNguoiLienHe.getText().toString());
                        jsonObject.put("contact_phone", edtSDTNguoiLienHe.getText().toString());
                        jsonObject.put("contact_email", edtEmailNguoiLienHe.getText().toString());
                    }
                    jsonArrayContact.put(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String contact = jsonArrayContact.toString();
                String status = "2";
                String job_welfare = edtInfoPhucLoi.getText().toString();
                isDangTin = true;
                if(DateUtils.isFuture(spNam.getSelectedItem().toString(),spThang.getSelectedItem().toString().replace("Th.",""),spNgay.getSelectedItem().toString())){
                    mActivity.p.putString(Pref.TYPE_QUANLYTINTUYENDUNG_TAB,"1");
                    if(isEdit){
                        updateRecruitment(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),mActivity.p.getString(Pref.PREF_KEY_JOB_ID,null),position,title,company_id,categories,quantity,level,time_type,range_salary_id,salary,work_location,gender,
                                description,range_allowance_id,allowance,range_bonus_id,bonus,extra_desc,expired_time,languages_profile,album_image_id,
                                album_contract_id,skills,year_exps,exclude_conditions.toString(),require_conditions.toString(),contact,status,job_welfare,isDangTin);

                        Log.d("ALOLO","UPDATE");
                    }else {
                        createNewCruitment(token, position, title, company_id, categories, quantity, level, time_type, range_salary_id, salary, work_location, gender,
                                description, range_allowance_id, allowance, range_bonus_id, bonus, extra_desc, expired_time, languages_profile, album_image_id,
                                album_contract_id, skills, year_exps, exclude_conditions.toString(), require_conditions.toString(), contact, status, job_welfare,isDangTin);
                        Log.d("ALOLO","CREATE");
                    }
                }else {
                    DialogCall.showResponse(mActivity, mActivity.getString(R.string.thoihanungtuyen_error), new View.OnClickListener() {
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

        btnLuuTinNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String token = mActivity.p.getString(Pref.PREF_KEY_TOKEN,null);
                String position = "";
                JSONArray jsonArrayPos = new JSONArray();
                JSONObject jsonObjectPos = new JSONObject();
                try {
                    String poss = "";
                    String pos_name = autocomplete_vitri.getText().toString();
                    String pos_value = autocomplete_vitri.getText().toString();
                    for(int i = 0;i<arrPositionJob.size();i++){
                        if(arrPositionJob.get(i).equals(autocomplete_vitri.getText().toString())){
                            poss = arrPositionJobId.get(i);
                            pos_name = autocomplete_vitri.getText().toString();
                            pos_value = autocomplete_vitri.getText().toString();
                        }
                    }
                    jsonObjectPos.put("pos_id",poss);
                    jsonObjectPos.put("pos_name",pos_name);
                    jsonObjectPos.put("pos_value",pos_value);
                    jsonArrayPos.put(jsonObjectPos);
                    position = jsonArrayPos.toString();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String title = title_recruitment.getText().toString();
                if(mActivity.isCaNhan){
                    company_id = "";
                }else {
                    company_id = p.getString(Pref.com_id,null);
                }
//                if(spThoiGianLamViec.getSelectedItem().toString().equals(getString(R.string.fulltime))){
//                     categories = "2";
//                }else {
//                     categories = "1";
//                }
                categories ="";
                if(edtSoLuongCanTuyen.getText().toString().length()>0){
                    quantity = edtSoLuongCanTuyen.getText().toString();
                }else {
                    quantity = "0";
                }
                String level = "";
                if(spThoiGianLamViec.getSelectedItem().toString().equals(getString(R.string.fulltime))){
                    time_type = "2";
                }else {
                    time_type = "1";
                }
                String range_salary_id = "";
                if(cbUpLuongCoBan.isChecked()){
                    salary = "> "+edtUpLuongCoBan.getText().toString().replaceAll(",","").replaceAll("\\.","");
                }else
                if(cbBetweenLuongCoBan.isChecked()){
                    salary = edtBetweenLuongCoBanFrom.getText().toString().replaceAll(",","").replaceAll("\\.","") + " - " + edtBetweenLuongCoBanTo.getText().toString().replaceAll(",","").replaceAll("\\.","");
                }else
                if(cbDownLuongCoBan.isChecked()){
                    salary = "< "+edtDownLuongCoBan.getText().toString().replaceAll(",","").replaceAll("\\.","");
                }else
                if(cbExactlyLuongCoBan.isChecked()){
                    salary = edtExactlyLuongCoBan.getText().toString().replaceAll(",","").replaceAll("\\.","");
                }else {
                    salary = "";
                }
                JSONArray jsonArrayLocation = new JSONArray();
                JSONObject jsonObjectLocation = new JSONObject();
                try {
                    String province_id = "";
                    for(int i =0;i<locationModels.size();i++){
                        if(locationModels.get(i).getLocat_value().equals(autocomplete_location.getText().toString())){
                            province_id = String.valueOf(locationModels.get(i).getLocat_id());
                        }
                    }
                    jsonObjectLocation.put("province_id",province_id);
                    jsonObjectLocation.put("province_value", autocomplete_location.getText().toString());
                    jsonObjectLocation.put("district", edtTown.getText().toString());
                    jsonObjectLocation.put("street", edtStreet.getText().toString());
                    jsonObjectLocation.put("number", edtNumber.getText().toString());
                    jsonArrayLocation.put(jsonObjectLocation);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String work_location = jsonArrayLocation.toString();
                if(spSex.getSelectedItem().toString().equals(getString(R.string.male))){
                    gender = "1";
                }else if(spSex.getSelectedItem().toString().equals(getString(R.string.remale))){
                    gender = "2";
                }else {
                    gender = "3";
                }
                String description = edtMoTaCongViec.getText().toString();
                String range_allowance_id = "";
                if(cbUpPhuCap.isChecked()){
                    allowance = "> "+edtUpPhuCap.getText().toString().replaceAll(",","").replaceAll("\\.","");
                }else
                if(cbBetweenPhuCap.isChecked()){
                    allowance = edtBetweenPhuCapFrom.getText().toString().replaceAll(",","").replaceAll("\\.","") + " - " + edtBetweenPhuCapTo.getText().toString().replaceAll(",","").replaceAll("\\.","");
                }else
                if(cbDownPhuCap.isChecked()){
                    allowance = "< "+edtDownPhuCap.getText().toString().replaceAll(",","").replaceAll("\\.","");
                }else
                if(cbExactlyPhuCap.isChecked()){
                    allowance = edtExactlyPhuCap.getText().toString().replaceAll(",","").replaceAll("\\.","");
                }else {
                    allowance = "";
                }
                String range_bonus_id = "";
                if(cbUpThuong.isChecked()){
                    bonus = "> "+edtUpThuong.getText().toString().replaceAll(",","").replaceAll("\\.","");
                }else
                if(cbBetweenThuong.isChecked()){
                    bonus = edtBetweenThuongFrom.getText().toString().replaceAll(",","").replaceAll("\\.","") + " - " + edtBetweenThuongTo.getText().toString().replaceAll(",","").replaceAll("\\.","");
                }else
                if(cbDownThuong.isChecked()){
                    bonus = "< "+edtDownThuong.getText().toString().replaceAll(",","").replaceAll("\\.","");
                }else if(cbExactlyThuong.isChecked()){
                    bonus = edtExactlyThuong.getText().toString().replaceAll(",","").replaceAll("\\.","");
                }else {
                    bonus = "";
                }
                String extra_desc = edtInfoRecruitment.getText().toString();
                String expired_time = spNam.getSelectedItem().toString() + "-" + spThang.getSelectedItem().toString().replace("Th.","") + "-" + spNgay.getSelectedItem().toString();
                String languages_profile = languageModels.get(spNgonNguHoSo.getSelectedItemPosition()).getLang_code();
                String album_image_id = "";
                String album_contract_id = "";
                String skills = "";
                String year_exps = "";
                if(edtExpYear.length()==0&&edtExpMonth.length()!=0){
                    year_exps = "0 "+ mActivity.getString(R.string.year)+ " "+edtExpMonth.getText().toString() +" "+mActivity.getString(R.string.month);
                }else if(edtExpMonth.length()==0&&edtExpYear.length()!=0){
                    year_exps = edtExpYear.getText().toString() + " "+ mActivity.getString(R.string.year)+ "0 "+mActivity.getString(R.string.month);
                }else if(edtExpMonth.length()==0&&edtExpYear.length()==0){
                    year_exps = "0 "+ mActivity.getString(R.string.year)+ "0 "+mActivity.getString(R.string.month);
                }else {
                    year_exps = edtExpYear.getText().toString() + " "+ mActivity.getString(R.string.year)+ " "+edtExpMonth.getText().toString() +" "+mActivity.getString(R.string.month);
                }
                int selectedIdGioitinh = rdbGrGioitinh.getCheckedRadioButtonId();
                int selectedIdKinhNghiem = rdbGrKinhNghiem.getCheckedRadioButtonId();
                int selectedIdTuoiTac = rdbGrTuoiTac.getCheckedRadioButtonId();
                int selectedIdNoiSinh = rdbGrNoiSinh.getCheckedRadioButtonId();
                JSONArray exclude_conditions = new JSONArray();
                JSONArray require_conditions = new JSONArray();
                JSONObject jsonObjectGioitinh = new JSONObject();
                JSONObject jsonObjectKinhNghiem = new JSONObject();
                JSONObject jsonObjectTuoiTac = new JSONObject();
                JSONObject jsonObjectNoiSinh = new JSONObject();
//                if(selectedIdKinhNghiem != rdbYeuCauKinhNghiem.getId()){
//                    try {
//                        jsonObjectKinhNghiem.put("exclude_cate_id","3");
//                        jsonObjectKinhNghiem.put("value",spKinhNghiem.getSelectedItem().toString());
//                        exclude_conditions.put(jsonObjectKinhNghiem);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }else {
//                    try {
//                        jsonObjectKinhNghiem.put("require_cate_id","3");
//                        jsonObjectKinhNghiem.put("value",spKinhNghiem.getSelectedItem().toString());
//                        require_conditions.put(jsonObjectKinhNghiem);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
                if(selectedIdNoiSinh != rdbYeuCauNoiSinh.getId()){
                    try {
                        jsonObjectNoiSinh.put("exclude_cate_id","4");
                        jsonObjectNoiSinh.put("value",edtNoiSinh.getText().toString());
                        exclude_conditions.put(jsonObjectNoiSinh);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        jsonObjectNoiSinh.put("require_cate_id","4");
                        jsonObjectNoiSinh.put("value",edtNoiSinh.getText().toString());
                        require_conditions.put(jsonObjectNoiSinh);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if(selectedIdTuoiTac != rdbYeuCauTuoiTac.getId()){
                    try {
                        jsonObjectTuoiTac.put("exclude_cate_id","2");
                        if(cbUp.isChecked()){
                            jsonObjectTuoiTac.put("value","> "+edtUp.getText().toString());
                        }else if(cbBetween.isChecked()){
                            jsonObjectTuoiTac.put("value",edtBetweenFrom.getText().toString() + " - "+edtBetweenTo.getText().toString());
                        }else if(cbDown.isChecked()){
                            jsonObjectTuoiTac.put("value","< "+edtDown.getText().toString());
                        }else if(cbExactly.isChecked()){
                            jsonObjectTuoiTac.put("value",edtExactly.getText().toString());
                        }
                        exclude_conditions.put(jsonObjectTuoiTac);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        jsonObjectTuoiTac.put("require_cate_id","2");
                        if(cbUp.isChecked()){
                            jsonObjectTuoiTac.put("value","> "+edtUp.getText().toString());
                        }else if(cbBetween.isChecked()){
                            jsonObjectTuoiTac.put("value",edtBetweenFrom.getText().toString() + " - "+edtBetweenTo.getText().toString());
                        }else if(cbDown.isChecked()){
                            jsonObjectTuoiTac.put("value","< "+edtDown.getText().toString());
                        }else if(cbExactly.isChecked()){
                            jsonObjectTuoiTac.put("value",edtExactly.getText().toString());
                        }
                        require_conditions.put(jsonObjectTuoiTac);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if(selectedIdGioitinh != rdbYeuCauGioitinh.getId()){
                    try {
                        jsonObjectGioitinh.put("exclude_cate_id","1");
                        jsonObjectGioitinh.put("value",spSex.getSelectedItem().toString());
                        exclude_conditions.put(jsonObjectGioitinh);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        jsonObjectGioitinh.put("require_cate_id","1");
                        jsonObjectGioitinh.put("value",spSex.getSelectedItem().toString());
                        require_conditions.put(jsonObjectGioitinh);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                Log.d("rdbYeuCauGioitinh","ss"+exclude_conditions+"ss"+require_conditions);
                JSONArray jsonArrayContact = new JSONArray();
                JSONObject jsonObject = new JSONObject();
                try {
                    if(isEdit) {
                        jsonObject.put("contact_position", "");
                        jsonObject.put("contact_name", edtTenNguoiLienHe.getText().toString());
                        jsonObject.put("contact_phone", edtSDTNguoiLienHe.getText().toString());
                        jsonObject.put("contact_email", edtEmailNguoiLienHe.getText().toString());
                        jsonObject.put("contact_id", jcont_id);
                    }else {
                        jsonObject.put("contact_position", "");
                        jsonObject.put("contact_name", edtTenNguoiLienHe.getText().toString());
                        jsonObject.put("contact_phone", edtSDTNguoiLienHe.getText().toString());
                        jsonObject.put("contact_email", edtEmailNguoiLienHe.getText().toString());
                    }
                    jsonArrayContact.put(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String contact = jsonArrayContact.toString();
                String status = "1";
                String job_welfare = edtInfoPhucLoi.getText().toString();
                isDangTin = false;

                if(DateUtils.isFuture(spNam.getSelectedItem().toString(),spThang.getSelectedItem().toString().replace("Th.",""),spNgay.getSelectedItem().toString())){
                    if(isEdit){
                        updateRecruitment(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),mActivity.p.getString(Pref.PREF_KEY_JOB_ID,null),position,title,company_id,categories,quantity,level,time_type,range_salary_id,salary,work_location,gender,
                                description,range_allowance_id,allowance,range_bonus_id,bonus,extra_desc,expired_time,languages_profile,album_image_id,
                                album_contract_id,skills,year_exps,exclude_conditions.toString(),require_conditions.toString(),contact,mActivity.p.getString(Pref.PREF_KEY_STATUS_ID,null),job_welfare,isDangTin);
                    }else {
                        mActivity.p.putString(Pref.TYPE_QUANLYTINTUYENDUNG_TAB,"0");
                        createNewCruitment(token,position,title,company_id,categories,quantity,level,time_type,range_salary_id,salary,work_location,gender,
                                description,range_allowance_id,allowance,range_bonus_id,bonus,extra_desc,expired_time,languages_profile,album_image_id,
                                album_contract_id,skills,year_exps,exclude_conditions.toString(),require_conditions.toString(),contact,status,job_welfare,isDangTin);
                    }
                }else {
                    DialogCall.showResponse(mActivity, mActivity.getString(R.string.thoihanungtuyen_error), new View.OnClickListener() {
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

        btnHuyTaoTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.selectItem(17);
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
//                    mActivity.selectItem(17);
                    return true;
                }
                return false;
            }
        } );
        return rootView;
    }

    private void  checkBoxSet(final CheckBox cbups, final CheckBox cbBet, final CheckBox cbDowns, final CheckBox cbEx){
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

    private void createNewCruitment(String token, String position, String title, String company_id, String categories, String quantity,
                                    String level, String time_type, String range_salary_id, String salary, String work_location,
                                    String gender, String description, String range_allowance_id, String allowance, String range_bonus_id,
                                    String bonus, String extra_desc, String expired_time, String languages_profile, String album_image_id,
                                    String album_contract_id, String skills, String year_exps, String exclude_conditions, String require_conditions,
                                    String contact, String status, String job_welfare, final boolean isDangTin){
        HttpPostNewRecruitment api = new HttpPostNewRecruitment(mActivity,token);
        api.request(position,title,company_id,categories,quantity,level,time_type,range_salary_id,salary,work_location,gender,
                description,range_allowance_id,allowance,range_bonus_id,bonus,extra_desc,expired_time,languages_profile,album_image_id,
                album_contract_id,skills,year_exps,exclude_conditions,require_conditions,contact,status,job_welfare,
                new API.APIDelegate() {
                    @Override
                    public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                        if (httpResponseCode == HttpResponseCode.SUCCESS) {
                            if(isDangTin){
                                DialogCall.showResponse(mActivity, mActivity.getString(R.string.dangtin_success), new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        int id = v.getId();
                                        if (id == R.id.btnOk) {
                                            mActivity.selectItem(17);
                                            mActivity.getPositionJobs(p.getString(Pref.PREF_KEY_TOKEN,null));
                                            mActivity.getLocation(p.getString(Pref.PREF_KEY_TOKEN,null));
                                        }
                                    }
                                });
                            }else {
                                DialogCall.showResponse(mActivity, mActivity.getString(R.string.addRecruitmentSuccess), new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        int id = v.getId();
                                        if (id == R.id.btnOk) {
                                            mActivity.selectItem(17);
                                            mActivity.getPositionJobs(p.getString(Pref.PREF_KEY_TOKEN,null));
                                            mActivity.getLocation(p.getString(Pref.PREF_KEY_TOKEN,null));
                                        }
                                    }
                                });
                            }

                        }else {
                            if(isDangTin){
                                DialogCall.showResponse(mActivity, mActivity.getString(R.string.dangtin_error), new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        int id = v.getId();
                                        if (id == R.id.btnOk) {
                                        }
                                    }
                                });
                            }else {
                                DialogCall.showResponse(mActivity, mActivity.getString(R.string.addRecruitmentError), new View.OnClickListener() {
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
    }
    public void showToolBarItem(){
        mActivity.showItemToolBar(false, false, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    private void init(){

        if(adapterExp!=null){
            adapterExp.clear();
            adapterExp.notifyDataSetChanged();
        }
        if(adapterPosition!=null){
            adapterPosition.clear();
            adapterPosition.notifyDataSetChanged();
        }
        if(adapterNgonNguHoSo!=null){
            adapterNgonNguHoSo.clear();
            adapterNgonNguHoSo.notifyDataSetChanged();
        }
        if(adapterLocation!=null){
            adapterLocation.clear();
            adapterLocation.notifyDataSetChanged();
        }

        if(adapterCheDo!=null){
            adapterCheDo.clear();
            adapterCheDo.notifyDataSetChanged();
        }
        if(adapterTimeWork!=null){
            adapterTimeWork.clear();
            adapterTimeWork.notifyDataSetChanged();
        }
        if(adapterSex!=null){
            adapterSex.clear();
            adapterSex.notifyDataSetChanged();
        }
        if(adapterSucKhoe!=null){
            adapterSucKhoe.clear();
            adapterSucKhoe.notifyDataSetChanged();
        }
        if(adapterNgoaiHinh!=null){
            adapterNgoaiHinh.clear();
            adapterNgoaiHinh.notifyDataSetChanged();
        }

        if(adapterMonth!=null){
            adapterMonth.clear();
            adapterMonth.notifyDataSetChanged();
        }
        if(adapterYear!=null){
            adapterYear.clear();
            adapterYear.notifyDataSetChanged();
        }
        if(adapterDay!=null){
            adapterDay.clear();
            adapterDay.notifyDataSetChanged();
        }
        arrCheDo.add(mActivity.getString(R.string.congkhai));
        arrCheDo.add(mActivity.getString(R.string.canhan));

        arrTimeWork.add(mActivity.getString(R.string.fulltime));
        arrTimeWork.add(mActivity.getString(R.string.parttime));
        arrSex.add(mActivity.getString(R.string.male));
        arrSex.add(mActivity.getString(R.string.remale));
        arrSex.add(mActivity.getString(R.string.other));

        arrNgoaiHinh.add(mActivity.getString(R.string.dep));
        arrNgoaiHinh.add(mActivity.getString(R.string.trungbinh));
        arrNgoaiHinh.add(mActivity.getString(R.string.ua_nhin));
        arrNgoaiHinh.add(mActivity.getString(R.string.xau));
        arrExp.add(mActivity.getString(R.string.khong_yeu_cau));
        arrExp.add(mActivity.getString(R.string.mot_nam));
        arrExp.add(mActivity.getString(R.string.mot_nam_ruoi));
        arrExp.add(mActivity.getString(R.string.hai_nam));
        arrExp.add(mActivity.getString(R.string.hai_nam_ruoi));
        arrExp.add(mActivity.getString(R.string.ba_nam));
        arrExp.add(mActivity.getString(R.string.ba_nam_ruoi));
        arrExp.add(mActivity.getString(R.string.bon_nam));
        arrExp.add(mActivity.getString(R.string.bon_nam_ruoi));
        arrExp.add(mActivity.getString(R.string.nam_nam));
        arrExp.add(mActivity.getString(R.string.hon_nam_nam));

        arrSucKhoe.add(getString(R.string.suc_khoe_tot));
        arrSucKhoe.add(getString(R.string.suc_khoe_trung_binh));

        adapterCheDo = new ArrayAdapter<String>(mActivity,R.layout.spinner_item, arrCheDo);
//        adapterCheDo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterTimeWork = new ArrayAdapter<String>(mActivity,R.layout.spinner_item, arrTimeWork);
//        adapterTimeWork.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterSex = new ArrayAdapter<String>(mActivity,R.layout.spinner_item, arrSex);
//        adapterSex.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterSucKhoe = new ArrayAdapter<String>(mActivity,R.layout.spinner_item, arrSucKhoe);
//        adapterSucKhoe.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterNgoaiHinh = new ArrayAdapter<String>(mActivity,R.layout.spinner_item, arrNgoaiHinh);
//        adapterNgoaiHinh.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterExp = new ArrayAdapter<String>(mActivity,R.layout.spinner_item, arrExp);
//        adapterExp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterPosition = new ArrayAdapter<String>(mActivity,R.layout.spinner_item, arrPositionJob);
//        adapterPosition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterNgonNguHoSo = new ArrayAdapter<String>(mActivity,R.layout.spinner_item, arrLanguage);
//        adapterNgonNguHoSo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterLocation = new ArrayAdapter<String>(mActivity,R.layout.spinner_item, arrLocation);
//        adapterLocation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterMonth = ArrayAdapter.createFromResource(
                mActivity, R.array.month, R.layout.spinner_item);
        spThang.setAdapter(adapterMonth);

        ArrayList<String> arrayListYear = new ArrayList<>();
        List<String> Lines = Arrays.asList(mActivity.getResources().getStringArray(R.array.year));
        Calendar c = Calendar.getInstance();
        int currentYear =  c.get(Calendar.YEAR);
        int futureYear = currentYear + 15;
        for (int i =0;i<Lines.size();i++){
            int start = Integer.parseInt(Lines.get(i));
            if((start >= currentYear)&&(start<=futureYear)){
                arrayListYear.add(Lines.get(i));
            }
        }
        adapterYear = new ArrayAdapter(mActivity, R.layout.spinner_item, arrayListYear);
//        adapterYear = ArrayAdapter.createFromResource(
//                mActivity, R.array.year, R.layout.spinner_item);
//        adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spNam.setAdapter(adapterYear);

        adapterDay = ArrayAdapter.createFromResource(
                mActivity, R.array.day, R.layout.spinner_item);
        spNgay.setAdapter(adapterDay);

        spXem.setAdapter(adapterCheDo);
        spUngTuyen.setAdapter(adapterCheDo);
        spThoiGianLamViec.setAdapter(adapterTimeWork);
        spSex.setAdapter(adapterSex);
        spNgoaiHinh.setAdapter(adapterNgoaiHinh);
        spKinhNghiem.setAdapter(adapterExp);

        spSucKhoe.setAdapter(adapterSucKhoe);
        autocomplete_vitri.setAdapter(adapterPosition);
        autocomplete_location.setAdapter(adapterLocation);
        spPosition.setAdapter(adapterPosition);
        spNgonNguHoSo.setAdapter(adapterNgonNguHoSo);
        for(int i = 0;i<arrLanguage.size();i++){
            if(arrLanguage.get(i).equals("Viet Nam")){
                spNgonNguHoSo.setSelection(i);
            }
        }
        spCity.setAdapter(adapterLocation);
        job_id_check = mActivity.p.getString(Pref.PREF_KEY_JOB_ID,null);
        recruitmentModels.clear();
        recruitmentModels = mActivity.databaseHelper.getRecruitmentByJobId(Integer.parseInt(job_id_check));
        if(recruitmentModels.size()>0) {
        }
        if(!job_id_check.equals("9999")){
            if(!job_id_check.equals("999")){
                mActivity.mTitleToolBar.setText(mActivity.getString(R.string.capnhattintuyendung));
                isEdit = true;
            }else {
                isEdit = false;
            }
            if(recruitmentModels.size()>0){
                title_recruitment.setText(recruitmentModels.get(0).getJob_title());
                edtInfoRecruitment.setText(recruitmentModels.get(0).getJob_extra_desc());
                edtSoLuongCanTuyen.setText(recruitmentModels.get(0).getJob_quantity());
//            edtTown.setText(recruitmentModels.get(0).getJob_extra_desc());
//            edtStreet.setText(recruitmentModels.get(0).getJob_title());
//            edtNumber.setText(recruitmentModels.get(0).getJob_extra_desc());
                edtMoTaCongViec.setText(recruitmentModels.get(0).getJob_description());
                edtInfoPhucLoi.setText(recruitmentModels.get(0).getJob_welfare());
                txtCheckedPosition.setText(recruitmentModels.get(0).getPos_value());
                txtCheckedLanguage.setText(recruitmentModels.get(0).getJob_language_profile());

                String locationList = recruitmentModels.get(0).getJob_location();
                try {
                    JSONArray jsonArrayLoca = new JSONArray(locationList);
                    if(jsonArrayLoca.length()>0){
                        JSONObject jsonObject = jsonArrayLoca.getJSONObject(0);
                        edtTown.setText(jsonObject.getString("district"));
                        edtStreet.setText(jsonObject.getString("street"));
                        edtNumber.setText(jsonObject.getString("number"));
                        autocomplete_location.setText(jsonObject.getString("province_value"));
                        for(int i =0;i<arrLocation.size();i++){
                            if(jsonObject.toString().contains("province_value")) {
                                if (arrLocation.get(i).equals(jsonObject.getString("province_value"))) {
                                    Log.d("locationList","ss"+arrLocation.get(i)+"ss"+jsonObject.getString("province_value"));
                                    spCity.setSelection(i);
                                }
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            for(int i = 0;i<arrPositionJob.size();i++){
                if(arrPositionJobId.get(i).equals(recruitmentModels.get(0).getJob_position_id())){
                    spPosition.setSelection(i);
                }
            }
            autocomplete_vitri.setText(recruitmentModels.get(0).getPos_value());
//            spPosition.setSelection(Integer.parseInt(recruitmentModels.get(0).getJob_position_id())-1);
            if(recruitmentModels.get(0).getJob_time_type().equals("2")){
                spThoiGianLamViec.setSelection(0);
            }else {
                spThoiGianLamViec.setSelection(1);
            }

            if(recruitmentModels.get(0).getJob_gender().equals("1")){
                spSex.setSelection(0);
            }else if(recruitmentModels.get(0).getJob_gender().equals("2")){
                spSex.setSelection(1);
            }else{
                spSex.setSelection(2);
            }

////            if(recruitmentModels.get(0).getJob_gender().equals("1")){
////                spNgoaiHinh.setSelection(0);
////            }else if(recruitmentModels.get(0).getJob_gender().equals("2")){
////                spNgoaiHinh.setSelection(1);
////            }else if(recruitmentModels.get(0).getJob_gender().equals("3")){
////                spNgoaiHinh.setSelection(2);
////            }else{
////                spNgoaiHinh.setSelection(3);
////            }
//
//            for (int i = 0;i<arrExp.size();i++){
//                if(arrExp.get(i).equals(recruitmentModels.get(0).getJob_year_exps())){
//                    spThoiGianLamViec.setSelection(i);
//                }else {
//                    spThoiGianLamViec.setSelection(i);
//                }
//            }
//
            if( recruitmentModels.get(0).getJob_salary().contains(">")){
                cbUpLuongCoBan.setChecked(true);
                edtUpLuongCoBan.setText(Edittext.convertTextToCommas(recruitmentModels.get(0).getJob_salary().replace("> ","")));
            }else if( recruitmentModels.get(0).getJob_salary().contains("-")){
                cbBetweenLuongCoBan.setChecked(true);
                String[] parts = recruitmentModels.get(0).getJob_salary().split(" - ");
                if(parts.length>1) {
                    edtBetweenLuongCoBanFrom.setText(Edittext.convertTextToCommas(parts[0]));
                    edtBetweenLuongCoBanTo.setText(Edittext.convertTextToCommas(parts[1]));
                }
            }else if( recruitmentModels.get(0).getJob_salary().contains("<")){
                cbDownLuongCoBan.setChecked(true);
                edtDownLuongCoBan.setText(Edittext.convertTextToCommas(recruitmentModels.get(0).getJob_salary().replace("< ","")));
            }else{
                cbExactlyLuongCoBan.setChecked(true);
                edtExactlyLuongCoBan.setText(Edittext.convertTextToCommas(recruitmentModels.get(0).getJob_salary()));
            }

            if( recruitmentModels.get(0).getJob_bonus().contains(">")){
                cbUpThuong.setChecked(true);
                edtUpThuong.setText(Edittext.convertTextToCommas(recruitmentModels.get(0).getJob_bonus().replace(">","")));
            }else if( recruitmentModels.get(0).getJob_bonus().contains("-")){
                cbBetweenThuong.setChecked(true);
                String[] parts = recruitmentModels.get(0).getJob_bonus().split(" - ");
                if(parts.length>1){
                    edtBetweenThuongFrom.setText(Edittext.convertTextToCommas(parts[0]));
                    edtBetweenThuongTo.setText(Edittext.convertTextToCommas(parts[1]));
                }
            }else if( recruitmentModels.get(0).getJob_bonus().contains("<")){
                cbDownThuong.setChecked(true);
                edtDownThuong.setText(Edittext.convertTextToCommas(recruitmentModels.get(0).getJob_bonus().replace("< ","")));
            }else{
                cbExactlyThuong.setChecked(true);
                edtExactlyThuong.setText(Edittext.convertTextToCommas(recruitmentModels.get(0).getJob_bonus()));
            }

            if( recruitmentModels.get(0).getJob_allowance().contains(">")){
                cbUpPhuCap.setChecked(true);
                edtUpPhuCap.setText(Edittext.convertTextToCommas(recruitmentModels.get(0).getJob_allowance().replace(">","")));
            }else if( recruitmentModels.get(0).getJob_allowance().contains("-")){
                cbBetweenPhuCap.setChecked(true);
                String[] parts = recruitmentModels.get(0).getJob_allowance().split(" - ");
                if(parts.length>1) {
                    edtBetweenPhuCapFrom.setText(Edittext.convertTextToCommas(parts[0]));
                    edtBetweenPhuCapTo.setText(Edittext.convertTextToCommas(parts[1]));
                }
            }else if( recruitmentModels.get(0).getJob_allowance().contains("<")){
                cbDownPhuCap.setChecked(true);
                edtDownPhuCap.setText(Edittext.convertTextToCommas(recruitmentModels.get(0).getJob_allowance().replace("< ","")));
            }else{
                cbExactlyPhuCap.setChecked(true);
                edtExactlyPhuCap.setText(Edittext.convertTextToCommas(recruitmentModels.get(0).getJob_allowance()));
            }
            String job_language_profile = recruitmentModels.get(0).getJob_language_profile();
            for(int i = 0;i<arrLanguage.size();i++){
                if(arrLanguage_id.get(i).equals(job_language_profile)){
                    spNgonNguHoSo.setSelection(i);
                }
            }

            String contactList = recruitmentModels.get(0).getJob_contact();
            try {
                JSONArray jsonArrayContact = new JSONArray(contactList);
                if(jsonArrayContact.length()>0){
                    JSONObject jsonObject = jsonArrayContact.getJSONObject(0);
                    if(jsonObject.toString().contains("jcont_phone")){
                        edtSDTNguoiLienHe.setText(jsonObject.getString("jcont_phone").replace("null",""));
                    }
                    if(jsonObject.toString().contains("jcont_name")){
                        edtTenNguoiLienHe.setText(jsonObject.getString("jcont_name").replace("null",""));
                    }
                    if(jsonObject.toString().contains("jcont_email")){
                        edtEmailNguoiLienHe.setText(jsonObject.getString("jcont_email").replace("null",""));
                    }
                    if(jsonObject.toString().contains("jcont_id")){
                        jcont_id = jsonObject.getString("jcont_id").replace("null","");
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            String job_expired_time = recruitmentModels.get(0).getJob_expired_time();
            if(job_expired_time.length()>5){
                for (int i = 0;i<adapterYear.getCount();i++){
                    if(spNam.getItemAtPosition(i).toString().equals(job_expired_time.substring(0,4))){
                        spNam.setSelection(i);
                    }
                }
                for (int i = 0;i<adapterMonth.getCount();i++){
                    if(spThang.getItemAtPosition(i).toString().replace("Th.","").equals(job_expired_time.substring(5,7))){
                        spThang.setSelection(i);
                    }
                }
                for (int i = 0;i<adapterDay.getCount();i++){
                    if(spNgay.getItemAtPosition(i).toString().equals(job_expired_time.substring(8,10))){
                        spNgay.setSelection(i);
                    }
                }
            }
            String yearExp = recruitmentModels.get(0).getJob_year_exps();
            String[] partsExp = yearExp.split(mActivity.getString(R.string.year));
            if(partsExp.length>=2){
                edtExpYear.setText(partsExp[0].trim());
                edtExpMonth.setText(partsExp[1].replace(mActivity.getString(R.string.month),"").trim());
            }

            String job_require_condition = recruitmentModels.get(0).getJob_require_condition();
            try {
                JSONArray jsonArrayExclude = new JSONArray(job_require_condition);
                if(jsonArrayExclude.length()>0){
                    for (int i = 0;i<jsonArrayExclude.length();i++){
                        JSONObject jsonObject = jsonArrayExclude.getJSONObject(i);
                        if(jsonObject.toString().contains("jexca_id")){
                            if(jsonObject.getString("jexca_id").replace("null","").equals("1")){
                                String value = jsonObject.getString("jreqco_value").replace("null","");
                                rdbYeuCauGioitinh.setChecked(true);
                                for(int j = 0;j<arrSex.size();j++){
                                    if(arrSex.get(j).equals(value)){
                                        spSex.setSelection(j);
                                    }
                                }
                            }else if(jsonObject.getString("jexca_id").replace("null","").equals("2")){
                                String value = jsonObject.getString("jreqco_value").replace("null","");
                                rdbYeuCauTuoiTac.setChecked(true);
                                if(value.contains(">")){
                                    cbUp.setChecked(true);
                                    cbDown.setChecked(false);
                                    cbBetween.setChecked(false);
                                    cbExactly.setChecked(false);
                                    edtUp.setText(value.replace("> ",""));
                                }else if(value.contains("<")){
                                    cbUp.setChecked(false);
                                    cbDown.setChecked(true);
                                    cbBetween.setChecked(false);
                                    cbExactly.setChecked(false);
                                    edtDown.setText(value.replace("< ",""));
                                }else if(value.contains("-")){
                                    cbUp.setChecked(false);
                                    cbDown.setChecked(false);
                                    cbBetween.setChecked(true);
                                    cbExactly.setChecked(false);
                                    String[] parts = value.split(" - ");
                                    edtBetweenFrom.setText(parts[0]);
                                    edtBetweenTo.setText(parts[1]);
                                }else {
                                    cbUp.setChecked(false);
                                    cbDown.setChecked(false);
                                    cbBetween.setChecked(false);
                                    cbExactly.setChecked(true);
                                    edtExactly.setText(value);
                                }
                            }else if(jsonObject.getString("jexca_id").replace("null","").equals("3")){
                                String value = jsonObject.getString("jreqco_value").replace("null","");
                                rdbYeuCauKinhNghiem.setChecked(true);
                                for(int j = 0;j<arrExp.size();j++){
                                    if(arrExp.get(j).equals(value)){
                                        spKinhNghiem.setSelection(j);
                                    }
                                }
                            }else if(jsonObject.getString("jexca_id").replace("null","").equals("4")){
                                rdbYeuCauNoiSinh.setChecked(true);
                                edtNoiSinh.setText(jsonObject.getString("jreqco_value").replace("null",""));
                            }
                        }
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            String job_exclude_condition = recruitmentModels.get(0).getJob_exclude_condition();
            try {
                JSONArray jsonArrayExclude = new JSONArray(job_exclude_condition);
                if(jsonArrayExclude.length()>0){
                    for (int i = 0;i<jsonArrayExclude.length();i++){
                        JSONObject jsonObject = jsonArrayExclude.getJSONObject(i);
                        if(jsonObject.getString("jexca_id").replace("null","").equals("1")){
                            String value = jsonObject.getString("jexcon_value").replace("null","");
                            rdbLoaiTruGioitinh.setChecked(true);
                            for(int j = 0;j<arrSex.size();j++){
                                if(arrSex.get(j).equals(value)){
                                    spSex.setSelection(j);
                                }
                            }
                        }else if(jsonObject.getString("jexca_id").replace("null","").equals("2")){
                            String value = jsonObject.getString("jexcon_value").replace("null","");
                            rdbLoaiTruTuoiTac.setChecked(true);
                            if(value.contains(">")){
                                cbUp.setChecked(true);
                                cbDown.setChecked(false);
                                cbBetween.setChecked(false);
                                cbExactly.setChecked(false);
                                edtUp.setText(value.replace("> ",""));
                            }else if(value.contains("<")){
                                cbUp.setChecked(false);
                                cbDown.setChecked(true);
                                cbBetween.setChecked(false);
                                cbExactly.setChecked(false);
                                edtDown.setText(value.replace("< ",""));
                            }else if(value.contains("-")){
                                cbUp.setChecked(false);
                                cbDown.setChecked(false);
                                cbBetween.setChecked(true);
                                cbExactly.setChecked(false);
                                String[] parts = value.split(" - ");
                                edtBetweenFrom.setText(parts[0]);
                                edtBetweenTo.setText(parts[1]);
                            }else {
                                cbUp.setChecked(false);
                                cbDown.setChecked(false);
                                cbBetween.setChecked(false);
                                cbExactly.setChecked(true);
                                edtExactly.setText(value);
                            }
                        }else if(jsonObject.getString("jexca_id").replace("null","").equals("3")){
                            String value = jsonObject.getString("jexcon_value").replace("null","");
                            rdbLoaiTruKinhNghiem.setChecked(true);
                            for(int j = 0;j<arrExp.size();j++){
                                if(arrExp.get(j).equals(value)){
                                    spKinhNghiem.setSelection(j);
                                }
                            }
                        }else if(jsonObject.getString("jexca_id").replace("null","").equals("4")){
                            rdbLoaiTruNoiSinh.setChecked(true);
                            edtNoiSinh.setText(jsonObject.getString("jexcon_value").replace("null",""));
                        }
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            btnLuuTinNhap.setText(getString(R.string.luuchinhsua));
        }else {
            isEdit = false;
            for (int i = 0;i<adapterYear.getCount();i++){
                if(spNam.getItemAtPosition(i).toString().equals(""+newCalendar.get(Calendar.YEAR))){
                    spNam.setSelection(i);
                }
            }
            for (int i = 0;i<adapterMonth.getCount();i++){
                if(spThang.getItemAtPosition(i).toString().replace("Th.","").equals(""+newCalendar.get(Calendar.MONTH))){
                    spThang.setSelection(i);
                }
            }
            for (int i = 0;i<adapterDay.getCount();i++){
                if(spNgay.getItemAtPosition(i).toString().equals(""+newCalendar.get(Calendar.DAY_OF_MONTH))){
                    spNgay.setSelection(i);
                }
            }
        }
    }
    private void addDataToSpinner(Spinner spinner, ArrayList<String> list){
        ArrayAdapter<String> adp = new ArrayAdapter<String>(mActivity,R.layout.spinner_item, list);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adp);
    }


    private void updateRecruitment(String token, String id,String position,String title,String company_id,String categories,String quantity,
                                   String level,String time_type,String range_salary_id,String salary,String work_location,
                                   String gender,String description,String range_allowance_id,String allowance,String range_bonus_id,
                                   String bonus,String extra_desc,String expired_time,String languages_profile,String album_image_id,
                                   String album_contract_id,String skills,String year_exps,String exclude_conditions,String require_conditions,
                                   String contact,String status,String job_welfare,boolean isDangtin){
        int response = HttpUpdateStatusRecruitment.updateRecruitment(token,id,position,title,company_id,categories,quantity,level,time_type,range_salary_id,salary,work_location,gender,
                description,range_allowance_id,allowance,range_bonus_id,bonus,extra_desc,expired_time,languages_profile,album_image_id,
                album_contract_id,skills,year_exps,exclude_conditions,require_conditions,contact,status,job_welfare);
        if(response == 200){
            if(isDangtin){
                DialogCall.showResponse(mActivity, mActivity.getString(R.string.dangtin_success), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int id = v.getId();
                        if (id == R.id.btnOk) {
                            mActivity.selectItem(17);
                            mActivity.getPositionJobs(p.getString(Pref.PREF_KEY_TOKEN,null));
                            mActivity.getLocation(p.getString(Pref.PREF_KEY_TOKEN,null));
                        }
                    }
                });
            }else {
                DialogCall.showResponse(mActivity, mActivity.getString(R.string.luuchinhsua_success), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int id = v.getId();
                        if (id == R.id.btnOk) {
                            mActivity.selectItem(17);
                            mActivity.getPositionJobs(p.getString(Pref.PREF_KEY_TOKEN,null));
                            mActivity.getLocation(p.getString(Pref.PREF_KEY_TOKEN,null));
                        }
                    }
                });
            }

        }else {
            if(isDangtin){
                DialogCall.showResponse(mActivity, mActivity.getString(R.string.dangtin_error), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int id = v.getId();
                        if (id == R.id.btnOk) {
                        }
                    }
                });
            }else {
                DialogCall.showResponse(mActivity, mActivity.getString(R.string.luuchinhsua_error), new View.OnClickListener() {
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
    public void getExcludeJob(String token){
        HttpGetJobExcludes api = new HttpGetJobExcludes(mActivity,token);
        api.request(new API.APIDelegate() {
            @Override
            public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                if (httpResponseCode == HttpResponseCode.SUCCESS) {
                    mActivity.databaseHelper.deleteAllExcludesJob();
                    try {
                        JSONArray data = jsonResponse.getJSONArray("data");
                        for(int i = 0;i<data.length();i++){
                            JSONObject jsonObject = data.getJSONObject(i);
                            String jexca_id = jsonObject.getString("jexca_id").replace("null","");
                            String jexca_name = jsonObject.getString("jexca_name").replace("null","");
                            String jexca_description = jsonObject.getString("jexca_description").replace("null","");
                            String jexca_created_at = jsonObject.getString("jexca_created_at").replace("null","");
                            String jexca_updated_at = jsonObject.getString("jexca_updated_at").replace("null","");
                            mActivity.databaseHelper.insertAExcludesJob(jexca_id,jexca_name,jexca_description,jexca_created_at,jexca_updated_at);
                        }
                        excludesModels = mActivity.databaseHelper.getAllExcludesJob();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }else {
                }
            }
        });
    }
}
