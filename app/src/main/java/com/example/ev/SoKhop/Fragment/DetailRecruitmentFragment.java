package com.example.ev.SoKhop.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ev.SoKhop.Api.HttpGetRecruitmentByJobId;
import com.example.ev.SoKhop.Api.HttpUpdateCandicateJob;
import com.example.ev.SoKhop.Api.core.API;
import com.example.ev.SoKhop.Api.core.HttpResponseCode;
import com.example.ev.SoKhop.Base.BaseFragment;
import com.example.ev.SoKhop.Dialog.DialogCall;
import com.example.ev.SoKhop.Model.RecruitmentModel;
import com.example.ev.SoKhop.R;
import com.example.ev.SoKhop.Utils.AutoResizeTextView;
import com.example.ev.SoKhop.Utils.DateUtils;
import com.example.ev.SoKhop.Utils.Edittext;
import com.example.ev.SoKhop.Utils.Pref;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MSI on 10/24/2016.
 */

public class DetailRecruitmentFragment extends BaseFragment{

    private String job_id_check;
    private ImageView img_logo,imgUngTuyen,imgSaveJob,imgChiaSe,imgOtherJob,imgQuanlyUngVien,imgQuanLyBaiTest,imgTienTrinhTuyenDung,imgBangTongSap;
    private Button imgLienHe;
    private TextView txtEmail,txtSoDienThoai,txtNguoiLienHe,txtNgonNguHoSo,txtTimeApply,txtPhuCap,txtThuong,txtLuongCoBan
            ,txtChieuCao,txtSucKhoe,txtHienTruTai,txtNoiSinh,txtKinhNghiem,txtNgoaiHinh,txtTuoiTac,txtGioiTinh,txtNoiLamViec
            ,txtThoigianLamViec,txtSoLuong,txtvitri,txtInfoUpdateTime,txtInfoluotxem,txtChieuCaoLoaiTru,txtSucKhoeLoaiTru
            ,txtHienTruTaiLoaiTru,txtNoiSinhLoaiTru,txtKinhNghiemLoaiTru,txtNgoaiHinhLoaiTru,txtTuoiTacLoaiTru,txtGioiTinhLoaiTru,txtKinhNghiem_add,txtJobName;
    private AutoResizeTextView txtCompanyName,txtCompanyLoca,txtCompanyPhone,txtCompanyWeb,txtCompanyEmail;

    private TextView txtLoiDan,txtMoTaCongViec,txtPhucLoi;
    private List<RecruitmentModel> recruitmentModels ;
    private LinearLayout layout_button_this_user,layout_button_other_user;
    private String jca_name,jca_value,jca_tags,jca_description,jca_created_at,jca_updated_at,lang_name,lang_code,lang_created_at,lang_updated_at;
    private String jca_id,lang_id;
    private boolean isSavedJob = false;
    private boolean isApplyedJob = false;

    private RelativeLayout layout_gioitinh,layout_tuoitac,layout_ngoaihinh,layout_kinhnghiem,layout_noisinh,layout_hientrutai,layout_suckhoe,layout_chieucao
            ,layout_gioitinhLoaiTru,layout_tuoitacLoaiTru,layout_ngoaihinhLoaiTru,layout_kinhnghiemLoaiTru,layout_noisinhLoaiTru,layout_hientrutaiLoaiTru,layout_suckhoeLoaiTru,layout_chieucaoLoaiTru;
    public DetailRecruitmentFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_detail_recruitment, container, false);
        recruitmentModels = new ArrayList<>();
        job_id_check = mActivity.p.getString(Pref.PREF_KEY_JOB_ID,null);
        getRecruitmentByJobId(p.getString(Pref.PREF_KEY_TOKEN,null),job_id_check);

        layout_button_this_user = (LinearLayout)rootView.findViewById(R.id.layout_button_this_user);
        layout_button_other_user = (LinearLayout)rootView.findViewById(R.id.layout_button_other_user);

        layout_gioitinh = (RelativeLayout)rootView.findViewById(R.id.layout_gioitinh);
        layout_tuoitac = (RelativeLayout)rootView.findViewById(R.id.layout_tuoitac);
        layout_ngoaihinh = (RelativeLayout)rootView.findViewById(R.id.layout_ngoaihinh);
        layout_kinhnghiem = (RelativeLayout)rootView.findViewById(R.id.layout_kinhnghiem);
        layout_noisinh = (RelativeLayout)rootView.findViewById(R.id.layout_noisinh);
        layout_hientrutai = (RelativeLayout)rootView.findViewById(R.id.layout_hientrutai);
        layout_suckhoe = (RelativeLayout)rootView.findViewById(R.id.layout_suckhoe);
        layout_chieucao = (RelativeLayout)rootView.findViewById(R.id.layout_chieucao);
        layout_gioitinhLoaiTru = (RelativeLayout)rootView.findViewById(R.id.layout_gioitinhLoaiTru);
        layout_tuoitacLoaiTru = (RelativeLayout)rootView.findViewById(R.id.layout_tuoitacLoaiTru);
        layout_ngoaihinhLoaiTru = (RelativeLayout)rootView.findViewById(R.id.layout_ngoaihinhLoaiTru);
        layout_kinhnghiemLoaiTru = (RelativeLayout)rootView.findViewById(R.id.layout_kinhnghiemLoaiTru);
        layout_noisinhLoaiTru = (RelativeLayout)rootView.findViewById(R.id.layout_noisinhLoaiTru);
        layout_hientrutaiLoaiTru = (RelativeLayout)rootView.findViewById(R.id.layout_hientrutaiLoaiTru);
        layout_suckhoeLoaiTru = (RelativeLayout)rootView.findViewById(R.id.layout_suckhoeLoaiTru);
        layout_chieucaoLoaiTru = (RelativeLayout)rootView.findViewById(R.id.layout_chieucaoLoaiTru);
        imgQuanlyUngVien = (ImageView)rootView.findViewById(R.id.imgQuanlyUngVien);
        imgQuanLyBaiTest = (ImageView)rootView.findViewById(R.id.imgQuanLyBaiTest);
        imgTienTrinhTuyenDung = (ImageView)rootView.findViewById(R.id.imgTienTrinhTuyenDung);
        imgBangTongSap = (ImageView)rootView.findViewById(R.id.imgBangTongSap);
        img_logo = (ImageView)rootView.findViewById(R.id.img_logo);
        imgUngTuyen = (ImageView)rootView.findViewById(R.id.imgUngTuyen);
        imgSaveJob = (ImageView)rootView.findViewById(R.id.imgSaveJob);
        imgChiaSe = (ImageView)rootView.findViewById(R.id.imgChiaSe);
        imgOtherJob = (ImageView)rootView.findViewById(R.id.imgOtherJob);
        imgLienHe = (Button)rootView.findViewById(R.id.imgLienHe);
        txtKinhNghiem_add = (TextView)rootView.findViewById(R.id.txtKinhNghiem_add);
        txtEmail = (TextView)rootView.findViewById(R.id.txtEmail);
        txtSoDienThoai = (TextView)rootView.findViewById(R.id.txtSoDienThoai);
        txtNguoiLienHe = (TextView)rootView.findViewById(R.id.txtNguoiLienHe);
        txtNgonNguHoSo = (TextView)rootView.findViewById(R.id.txtNgonNguHoSo);
        txtTimeApply = (TextView)rootView.findViewById(R.id.txtTimeApply);
        txtPhuCap = (TextView)rootView.findViewById(R.id.txtPhuCap);
        txtThuong = (TextView)rootView.findViewById(R.id.txtThuong);
        txtLuongCoBan = (TextView)rootView.findViewById(R.id.txtLuongCoBan);
        txtChieuCao = (TextView)rootView.findViewById(R.id.txtChieuCao);
        txtSucKhoe = (TextView)rootView.findViewById(R.id.txtSucKhoe);
        txtHienTruTai = (TextView)rootView.findViewById(R.id.txtHienTruTai);
        txtNoiSinh = (TextView)rootView.findViewById(R.id.txtNoiSinh);
        txtKinhNghiem = (TextView)rootView.findViewById(R.id.txtKinhNghiem);
        txtNgoaiHinh = (TextView)rootView.findViewById(R.id.txtNgoaiHinh);
        txtTuoiTac = (TextView)rootView.findViewById(R.id.txtTuoiTac);
        txtGioiTinh = (TextView)rootView.findViewById(R.id.txtGioiTinh);
        txtNoiLamViec = (TextView)rootView.findViewById(R.id.txtNoiLamViec);
        txtThoigianLamViec = (TextView)rootView.findViewById(R.id.txtThoigianLamViec);
        txtSoLuong = (TextView)rootView.findViewById(R.id.txtSoLuong);
        txtvitri = (TextView)rootView.findViewById(R.id.txtvitri);
        txtInfoUpdateTime = (TextView)rootView.findViewById(R.id.txtInfoUpdateTime);
        txtInfoluotxem = (TextView)rootView.findViewById(R.id.txtInfoluotxem);
        txtCompanyName = (AutoResizeTextView)rootView.findViewById(R.id.txtCompanyName);
        txtCompanyLoca = (AutoResizeTextView)rootView.findViewById(R.id.txtCompanyLoca);
        txtCompanyPhone = (AutoResizeTextView)rootView.findViewById(R.id.txtCompanyPhone);
        txtCompanyWeb = (AutoResizeTextView)rootView.findViewById(R.id.txtCompanyWeb);
        txtCompanyEmail = (AutoResizeTextView)rootView.findViewById(R.id.txtCompanyEmail);
        txtLoiDan = (TextView)rootView.findViewById(R.id.txtLoiDan);
        txtMoTaCongViec = (TextView)rootView.findViewById(R.id.txtMoTaCongViec);
        txtPhucLoi = (TextView)rootView.findViewById(R.id.txtPhucLoi);
        txtChieuCaoLoaiTru = (TextView)rootView.findViewById(R.id.txtChieuCaoLoaiTru);
        txtSucKhoeLoaiTru = (TextView)rootView.findViewById(R.id.txtSucKhoeLoaiTru);
        txtHienTruTaiLoaiTru = (TextView)rootView.findViewById(R.id.txtHienTruTaiLoaiTru);
        txtNoiSinhLoaiTru = (TextView)rootView.findViewById(R.id.txtNoiSinhLoaiTru);
        txtKinhNghiemLoaiTru = (TextView)rootView.findViewById(R.id.txtKinhNghiemLoaiTru);
        txtNgoaiHinhLoaiTru = (TextView)rootView.findViewById(R.id.txtNgoaiHinhLoaiTru);
        txtTuoiTacLoaiTru = (TextView)rootView.findViewById(R.id.txtTuoiTacLoaiTru);
        txtGioiTinhLoaiTru = (TextView)rootView.findViewById(R.id.txtGioiTinhLoaiTru);
        txtJobName = (TextView)rootView.findViewById(R.id.txtJobName);
        imgBangTongSap.setVisibility(View.GONE);
        imgQuanLyBaiTest.setVisibility(View.GONE);
        imgTienTrinhTuyenDung.setVisibility(View.GONE);
        imgChiaSe.setVisibility(View.GONE);
        imgOtherJob.setVisibility(View.GONE);
            if(mActivity.p.getString(Pref.PREF_KEY_VIEW_NEWS_BEFORE,null)!=null&&mActivity.p.getString(Pref.PREF_KEY_VIEW_NEWS_BEFORE,null).equals("1")) {
                imgSaveJob.setVisibility(View.GONE);
                imgUngTuyen.setVisibility(View.GONE);
                imgChiaSe.setVisibility(View.GONE);
                imgOtherJob.setVisibility(View.GONE);
                imgQuanlyUngVien.setVisibility(View.GONE);
                imgQuanLyBaiTest.setVisibility(View.GONE);
                imgTienTrinhTuyenDung.setVisibility(View.GONE);
                imgBangTongSap.setVisibility(View.GONE);
                imgLienHe.setVisibility(View.GONE);
                txtCompanyPhone.setTextColor(Color.parseColor("#3A494C"));
                txtCompanyWeb.setTextColor(Color.parseColor("#3A494C"));
                txtCompanyEmail.setTextColor(Color.parseColor("#3A494C"));
            }else {
                txtCompanyPhone.setTextColor(Color.parseColor("#ffffff"));
                txtCompanyWeb.setTextColor(Color.parseColor("#ffffff"));
                txtCompanyEmail.setTextColor(Color.parseColor("#ffffff"));
            }
        imgUngTuyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int response = HttpUpdateCandicateJob.updateApplyJobs(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),"",job_id_check);
                if(response == 200){
                    DialogCall.showResponse(mActivity, mActivity.getString(R.string.apply_success), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int id = v.getId();
                            if (id == R.id.btnOk) {
                                mActivity.p.putString(Pref.JOB_TAB,"2");
                                mActivity.selectItem(6);
                            }
                        }
                    });
                }else {
                    DialogCall.showResponse(mActivity, mActivity.getString(R.string.apply_error), new View.OnClickListener() {
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

        imgQuanlyUngVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.selectItem(22);
            }
        });
        imgSaveJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int response = HttpUpdateCandicateJob.updateSaveJobs(mActivity.p.getString(Pref.PREF_KEY_TOKEN,null),job_id_check);
                if(response == 200){
                    if(isSavedJob){
                        DialogCall.showResponse(mActivity, mActivity.getString(R.string.unsavejob_success), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int id = v.getId();
                                if (id == R.id.btnOk) {
                                    mActivity.p.putString(Pref.JOB_TAB,"1");
                                    mActivity.selectItem(6);
                                }
                            }
                        });
                    }else {
                        DialogCall.showResponse(mActivity, mActivity.getString(R.string.savejob_success), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int id = v.getId();
                                if (id == R.id.btnOk) {
                                    mActivity.p.putString(Pref.JOB_TAB,"1");
                                    mActivity.selectItem(6);
                                }
                            }
                        });
                    }

                }else {
                    if(isSavedJob) {
                        DialogCall.showResponse(mActivity, mActivity.getString(R.string.unsavejob_error), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int id = v.getId();
                                if (id == R.id.btnOk) {
                                }
                            }
                        });
                    }else {
                        DialogCall.showResponse(mActivity, mActivity.getString(R.string.savejob_error), new View.OnClickListener() {
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
        rootView.setFocusableInTouchMode(true);
        rootView.requestFocus();
        rootView.setOnKeyListener( new View.OnKeyListener()
        {
            @Override
            public boolean onKey( View v, int keyCode, KeyEvent event )
            {
                if( keyCode == KeyEvent.KEYCODE_BACK )
                {
                    if(recruitmentModels.size()>0) {
                        if (mActivity.isCaNhan) {
//                            if(p.getString(Pref.BOOLEAN_IS_FROM_JOB,null).equals("1"))
                            if (p.getString(Pref.BOOLEAN_IS_FROM_JOB,null).equals("2")) {
                                if(mActivity.p.getString(Pref.PREF_KEY_VIEW_NEWS_BEFORE,null)!=null&&mActivity.p.getString(Pref.PREF_KEY_VIEW_NEWS_BEFORE,null).equals("1")) {
                                        mActivity.selectItem(18);
                                } else {
                                    mActivity.selectItem(17);
                                }
                            } else {
                                mActivity.selectItem(6);
                            }
                        } else {
                            if(mActivity.p.getString(Pref.PREF_KEY_VIEW_NEWS_BEFORE,null)!=null&&mActivity.p.getString(Pref.PREF_KEY_VIEW_NEWS_BEFORE,null).equals("1")) {
                                mActivity.selectItem(18);
                            } else {
                                mActivity.selectItem(17);
                            }
                        }
                    }

                    return true;
                }
                return false;
            }
        } );
        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        mActivity.p.putString(Pref.PREF_KEY_VIEW_NEWS_BEFORE,"0");
    }

    @Override
    public void onPause() {
        super.onPause();
        mActivity.p.putString(Pref.PREF_KEY_VIEW_NEWS_BEFORE,"0");
    }

    public void showToolBarItem(boolean isMe){
        if(isMe){
                if(mActivity.p.getString(Pref.PREF_KEY_VIEW_NEWS_BEFORE,null)!=null&&mActivity.p.getString(Pref.PREF_KEY_VIEW_NEWS_BEFORE,null).equals("1")) {
                mActivity.showBackItem(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int id = v.getId();
                        if (id == R.id.txtBackToolBar) {
                            if(recruitmentModels.size()>0) {
                                if (mActivity.isCaNhan) {
                                    if (p.getString(Pref.BOOLEAN_IS_FROM_JOB,null).equals("2")) {
                                        if(mActivity.p.getString(Pref.PREF_KEY_VIEW_NEWS_BEFORE,null)!=null&&mActivity.p.getString(Pref.PREF_KEY_VIEW_NEWS_BEFORE,null).equals("1")) {
                                            mActivity.selectItem(18);
                                        } else {
                                            mActivity.selectItem(17);
                                        }
                                    } else {
                                        mActivity.selectItem(6);
                                    }
                                } else {
                                    if(mActivity.p.getString(Pref.PREF_KEY_VIEW_NEWS_BEFORE,null)!=null&&mActivity.p.getString(Pref.PREF_KEY_VIEW_NEWS_BEFORE,null).equals("1")) {
                                        mActivity.selectItem(18);
                                    } else {
                                        mActivity.selectItem(17);
                                    }
                                }
                            }
                        }
                    }
                });
            }else { mActivity.showItemToolBar(true, false, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int id = v.getId();
                        if (id == R.id.btnCreate) {
                            mActivity.p.putString(Pref.PREF_KEY_JOB_ID, job_id_check);
                            mActivity.p.putString(Pref.PREF_KEY_STATUS_ID, "1");
                            mActivity.selectItem(18);
                        }
                    }
                });
                }
        }else {
            mActivity.showItemToolBar(false, false, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = v.getId();
                    if (id == R.id.btnCreate) {
                    }
                }
            });
        }

    }


    public void getRecruitmentByJobId(String token, final String job_idsssss){
        HttpGetRecruitmentByJobId api = new HttpGetRecruitmentByJobId(mActivity,token,job_idsssss);
        api.request(new API.APIDelegate() {
            @Override
            public void onRequestFinished(HttpResponseCode httpResponseCode, JSONObject jsonResponse) {
                Log.d("rdbYeuCauGioitinh","ss"+jsonResponse);
                if (httpResponseCode == HttpResponseCode.SUCCESS) {
                    if(mActivity.p.getString(Pref.PREF_KEY_VIEW_NEWS_BEFORE,null)!=null&&mActivity.p.getString(Pref.PREF_KEY_VIEW_NEWS_BEFORE,null).equals("1")) {

                    }else {
                        mActivity.databaseHelper.deleteARecruitmentByPosId(job_idsssss);
                    }
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
                            String is_user_saved = jsonObject.getString("is_user_saved");
                            String is_user_apply = jsonObject.getString("is_user_apply");
                            String number_matching = "";
//                            String job_exclude_condition = jsonObject.getString("");
//                            String job_require_condition = jsonObject.getString("");
//                            String job_location = jsonObject.getString("");
                            if(mActivity.p.getString(Pref.PREF_KEY_VIEW_NEWS_BEFORE,null)!=null&&mActivity.p.getString(Pref.PREF_KEY_VIEW_NEWS_BEFORE,null).equals("1")) {

                            }else {
                                mActivity.databaseHelper.insertARecruitment(job_id, job_company_id, job_owner_id, jca_id, job_position_id, pos_id, job_quantity,
                                        job_time_type, job_range_salary_id, job_gender, job_range_allowance_id, job_range_bonus_id, job_status, job_isactive,
                                        lang_id, job_title, jca_name, jca_value, jca_tags, jca_description, jca_created_at, jca_updated_at
                                        , pos_name, pos_value, job_views_number, job_apply_number, job_level_id, job_salary, job_work_location
                                        , job_description, job_allowance, job_bonus, job_extra_desc, job_expired_time, job_language_profile, job_album_img_id
                                        , job_album_contract_id, job_skills, job_year_exps, job_created_at, job_updated_at, lang_name, lang_code
                                        , lang_created_at, lang_updated_at, job_exclude_condition.toString(), job_require_condition.toString(), job_location.toString(),job_welfare,job_contact
                                        ,owner_id,owner_avatar,owner_name,owner_website,owner_phone,is_user_saved,number_matching,is_user_apply);
                            }
                        }
                        recruitmentModels = mActivity.databaseHelper.getRecruitmentByJobId(Integer.parseInt(job_idsssss));
                        if(recruitmentModels.size()>0){
                            if(recruitmentModels.get(0).getIs_user_saved().equals("1")){
                                isSavedJob = true;
                            }else {
                                isSavedJob = false;
                            }
                            if(recruitmentModels.get(0).getIs_user_apply().equals("1")){
                                isApplyedJob = true;
                            }else {
                                isApplyedJob = false;
                            }
                        if(recruitmentModels.get(0).getOwner_avatar().length()>4){
                            Glide.with(mActivity).load(recruitmentModels.get(0).getOwner_avatar()).into(img_logo);
                        }
                            txtJobName.setText(recruitmentModels.get(0).getJob_title());
                        txtCompanyName.setText(recruitmentModels.get(0).getOwner_name());
                        txtCompanyPhone.setText(recruitmentModels.get(0).getOwner_phone());
                        txtCompanyWeb.setText(recruitmentModels.get(0).getOwner_website());

                        if(recruitmentModels.get(0).getJob_owner_id().equals(p.getString(Pref.USER_ID,null))){
                            layout_button_other_user.setVisibility(View.GONE);
                            layout_button_this_user.setVisibility(View.VISIBLE);
                            imgLienHe.setVisibility(View.GONE);
                            showToolBarItem(true);
                        }else {
                            if(isSavedJob){
                                imgSaveJob.setImageResource(R.drawable.button_huyquantam);
                            }else {
                                imgSaveJob.setImageResource(R.drawable.button_savejob);
                            }
                            if(isApplyedJob){
                                imgUngTuyen.setImageResource(R.drawable.button_daungtuyen);
                                imgUngTuyen.setEnabled(false);
                            }else {
                                imgUngTuyen.setImageResource(R.drawable.button_ungtuyen);
                                imgUngTuyen.setEnabled(true);
                            }
                            layout_button_other_user.setVisibility(View.VISIBLE);
                            layout_button_this_user.setVisibility(View.GONE);
                            imgLienHe.setVisibility(View.VISIBLE);
                            showToolBarItem(false);
                        }
                        txtNgonNguHoSo.setText(recruitmentModels.get(0).getLang_name());
                        if(recruitmentModels.get(0).getJob_expired_time().length()>4) {
                            txtTimeApply.setText(DateUtils.convertDateFormat(recruitmentModels.get(0).getJob_expired_time().substring(0,10)));
                        }

                            try {
                                JSONArray jsonArrayExclude = new JSONArray(recruitmentModels.get(0).getJob_require_condition());
                                if(jsonArrayExclude.length()>0){
                                    for (int i = 0;i<jsonArrayExclude.length();i++){
                                        JSONObject jsonObject = jsonArrayExclude.getJSONObject(i);
                                        if(jsonObject.toString().contains("jexca_id")){
                                            if(jsonObject.getString("jexca_id").replace("null","").equals("1")){
                                                String value = jsonObject.getString("jreqco_value").replace("null","");
                                                if(recruitmentModels.get(0).getJob_gender().equals("1")){
                                                    txtGioiTinh.setText(getString(R.string.male));
                                                }else if(recruitmentModels.get(0).getJob_gender().equals("2")){
                                                    txtGioiTinh.setText(getString(R.string.remale));
                                                }else {
                                                    txtGioiTinh.setText(getString(R.string.other));
                                                }
                                                layout_gioitinhLoaiTru.setVisibility(View.GONE);
                                                layout_gioitinh.setVisibility(View.VISIBLE);
                                            }else if(jsonObject.getString("jexca_id").replace("null","").equals("2")){
                                                String value = jsonObject.getString("jreqco_value").replace("null","");
                                                txtTuoiTac.setText(value);
                                                layout_tuoitacLoaiTru.setVisibility(View.GONE);
                                                layout_tuoitac.setVisibility(View.VISIBLE);
                                            }else if(jsonObject.getString("jexca_id").replace("null","").equals("3")){
                                                String value = jsonObject.getString("jreqco_value").replace("null","");
                                                txtKinhNghiem.setText(value);
                                                layout_kinhnghiemLoaiTru.setVisibility(View.GONE);
                                                layout_kinhnghiem.setVisibility(View.GONE);
                                            }else if(jsonObject.getString("jexca_id").replace("null","").equals("4")){
                                                String value = jsonObject.getString("jreqco_value").replace("null","");
                                                txtNoiSinh.setText(value);
                                                layout_noisinhLoaiTru.setVisibility(View.GONE);
                                                layout_noisinh.setVisibility(View.VISIBLE);
                                            }
                                        }
                                    }
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            try {
                                JSONArray jsonArrayExclude = new JSONArray(recruitmentModels.get(0).getJob_exclude_condition());
                                if(jsonArrayExclude.length()>0){
                                    for (int i = 0;i<jsonArrayExclude.length();i++){
                                        JSONObject jsonObject = jsonArrayExclude.getJSONObject(i);
                                        if(jsonObject.toString().contains("jexca_id")){
                                            if(jsonObject.getString("jexca_id").replace("null","").equals("1")){
                                                String value = jsonObject.getString("jexcon_value").replace("null","");
                                                if(recruitmentModels.get(0).getJob_gender().equals("1")){
                                                    txtGioiTinhLoaiTru.setText(getString(R.string.male));
                                                }else if(recruitmentModels.get(0).getJob_gender().equals("2")){
                                                    txtGioiTinhLoaiTru.setText(getString(R.string.remale));
                                                }else {
                                                    txtGioiTinhLoaiTru.setText(getString(R.string.other));
                                                }
                                                layout_gioitinh.setVisibility(View.GONE);
                                                layout_gioitinhLoaiTru.setVisibility(View.VISIBLE);
                                            }else if(jsonObject.getString("jexca_id").replace("null","").equals("2")){
                                                String value = jsonObject.getString("jexcon_value").replace("null","");
                                                txtTuoiTacLoaiTru.setText(value);
                                                layout_tuoitac.setVisibility(View.GONE);
                                                layout_tuoitacLoaiTru.setVisibility(View.VISIBLE);
                                            }else if(jsonObject.getString("jexca_id").replace("null","").equals("3")){
                                                String value = jsonObject.getString("jexcon_value").replace("null","");
                                                txtKinhNghiemLoaiTru.setText(value);
                                                layout_kinhnghiem.setVisibility(View.GONE);
                                                layout_kinhnghiemLoaiTru.setVisibility(View.GONE);
                                            }else if(jsonObject.getString("jexca_id").replace("null","").equals("4")){
                                                String value = jsonObject.getString("jexcon_value").replace("null","");
                                                txtNoiSinhLoaiTru.setText(value);
                                                layout_noisinh.setVisibility(View.GONE);
                                                layout_noisinhLoaiTru.setVisibility(View.VISIBLE);
                                            }
                                        }
                                    }
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Log.d("exclude_condition","ss"+recruitmentModels.get(0).getJob_exclude_condition()+"ssss"+recruitmentModels.get(0).getJob_require_condition());
                        txtPhuCap.setText(Edittext.convertTextToCommas(recruitmentModels.get(0).getJob_allowance()));
                        txtThuong.setText(Edittext.convertTextToCommas(recruitmentModels.get(0).getJob_bonus()));
                        txtLuongCoBan.setText(Edittext.convertTextToCommas(recruitmentModels.get(0).getJob_salary()));
//                        txtKinhNghiem.setText(recruitmentModels.get(0).getJob_year_exps());
                            if(recruitmentModels.get(0).getJob_time_type().equals("2")){
                                txtThoigianLamViec.setText(mActivity.getString(R.string.fulltime));
                            }else {
                                txtThoigianLamViec.setText(getString(R.string.parttime));
                            }
                        txtSoLuong.setText(recruitmentModels.get(0).getJob_quantity());
                        txtvitri.setText(recruitmentModels.get(0).getPos_value());
                        if(recruitmentModels.get(0).getJob_updated_at().length()>4){
                            txtInfoUpdateTime.setText(DateUtils.convertDateFormat(recruitmentModels.get(0).getJob_updated_at().substring(0,10)));
                        }
                        txtInfoluotxem.setText(recruitmentModels.get(0).getJob_views_number());
                        txtLoiDan.setText(recruitmentModels.get(0).getJob_extra_desc());
                        txtMoTaCongViec.setText(recruitmentModels.get(0).getJob_description());
                        txtPhucLoi.setText(recruitmentModels.get(0).getJob_welfare());
                            txtKinhNghiem_add.setText(recruitmentModels.get(0).getJob_year_exps());

                        String contactList = recruitmentModels.get(0).getJob_contact();
                        String locaList = recruitmentModels.get(0).getJob_location();
                        String languageList = recruitmentModels.get(0).getJob_language_profile();
                        try {
                            JSONArray jsonArrayLoca = new JSONArray(locaList);
                            if(jsonArrayLoca.length()>0){
                                JSONObject jsonObject = jsonArrayLoca.getJSONObject(0);
                                if(jsonObject.toString().contains("province_value")){
                                    txtCompanyLoca.setText(jsonObject.getString("province_value"));
                                    txtNoiLamViec.setText(jsonObject.getString("province_value"));
                                }
                            }
                            JSONArray jsonArrayContact = new JSONArray(contactList);
                            if(jsonArrayContact.length()>0){
                                JSONObject jsonObject = jsonArrayContact.getJSONObject(0);
                                if(jsonObject.toString().contains("jcont_phone")){
                                    txtSoDienThoai.setText(jsonObject.getString("jcont_phone"));
                                }
                                if(jsonObject.toString().contains("jcont_name")){
                                    txtNguoiLienHe.setText(jsonObject.getString("jcont_name"));
                                }
                                if(jsonObject.toString().contains("jcont_email")){
                                    txtEmail.setText(jsonObject.getString("jcont_email"));
                                    txtCompanyEmail.setText(jsonObject.getString("jcont_email"));
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

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
