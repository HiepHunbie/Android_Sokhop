package com.example.ev.SoKhop.Fragment;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ev.SoKhop.Base.BaseFragment;
import com.example.ev.SoKhop.R;
import com.example.ev.SoKhop.Utils.DateUtils;
import com.example.ev.SoKhop.Utils.Pref;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by MSI on 10/5/2016.
 */

public class ProfileFragment extends BaseFragment {

        public ProfileFragment() {
        }
        private Pref p;
        private TextView txtInfoBirthday,txtInfoMenh,txtInfoSex,txtInfoPhone,txtInfoEmail,txtInfoPlacesBirth,txtInfoPlaces,
                txtInfoMangXaHoi,txtInfoNgoaiHinh,txtInfoHeight,txtInfoWeight,txtInfoSoDo3Vong,txtInfoHobbies,txtInfoNoiDuocGiong,
                txtInfoHatDuocGiong,txtInfoSkills,txtInfoKhaNangDacBiet;
    private LinearLayout layout_khanangdacbiet,layout_kinang,layout_hatduocgiong,layout_noiduocgiong,layout_sothich,layout_sodo3vong,layout_cannang,
            layout_chieucao,layout_ngoaihinh,layout_hienotai,layout_noisinh,layout_email,layout_sdt,layout_gioitinh,layout_menh,layout_ngaysinh;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
//        activity = (InfomationActivity)getActivity();
            p = new Pref(getActivity());

            txtInfoNgoaiHinh = (TextView)rootView.findViewById(R.id.txtInfoNgoaiHinh);
            txtInfoBirthday = (TextView)rootView.findViewById(R.id.txtInfoBirthday);
            txtInfoMenh = (TextView)rootView.findViewById(R.id.txtInfoMenh);
            txtInfoSex = (TextView)rootView.findViewById(R.id.txtInfoSex);
            txtInfoPhone = (TextView)rootView.findViewById(R.id.txtInfoPhone);
            txtInfoEmail = (TextView)rootView.findViewById(R.id.txtInfoEmail);
            txtInfoPlacesBirth = (TextView)rootView.findViewById(R.id.txtInfoPlacesBirth);
            txtInfoPlaces = (TextView)rootView.findViewById(R.id.txtInfoPlaces);
            txtInfoMangXaHoi = (TextView)rootView.findViewById(R.id.txtInfoMangXaHoi);
            txtInfoHeight = (TextView)rootView.findViewById(R.id.txtInfoHeight);
            txtInfoWeight = (TextView)rootView.findViewById(R.id.txtInfoWeight);
            txtInfoSoDo3Vong = (TextView)rootView.findViewById(R.id.txtInfoSoDo3Vong);
            txtInfoHobbies = (TextView)rootView.findViewById(R.id.txtInfoHobbies);
            txtInfoNoiDuocGiong = (TextView)rootView.findViewById(R.id.txtInfoNoiDuocGiong);
            txtInfoHatDuocGiong = (TextView)rootView.findViewById(R.id.txtInfoHatDuocGiong);
            txtInfoSkills = (TextView)rootView.findViewById(R.id.txtInfoSkills);
            txtInfoKhaNangDacBiet = (TextView)rootView.findViewById(R.id.txtInfoKhaNangDacBiet);

            layout_khanangdacbiet = (LinearLayout)rootView.findViewById(R.id.layout_khanangdacbiet);
            layout_kinang = (LinearLayout)rootView.findViewById(R.id.layout_kinang);
            layout_hatduocgiong = (LinearLayout)rootView.findViewById(R.id.layout_hatduocgiong);
            layout_noiduocgiong = (LinearLayout)rootView.findViewById(R.id.layout_noiduocgiong);
            layout_sothich = (LinearLayout)rootView.findViewById(R.id.layout_sothich);
            layout_sodo3vong = (LinearLayout)rootView.findViewById(R.id.layout_sodo3vong);
            layout_cannang = (LinearLayout)rootView.findViewById(R.id.layout_cannang);
            layout_chieucao = (LinearLayout)rootView.findViewById(R.id.layout_chieucao);
            layout_ngoaihinh = (LinearLayout)rootView.findViewById(R.id.layout_ngoaihinh);
            layout_hienotai = (LinearLayout)rootView.findViewById(R.id.layout_hienotai);
            layout_noisinh = (LinearLayout)rootView.findViewById(R.id.layout_noisinh);
            layout_email = (LinearLayout)rootView.findViewById(R.id.layout_email);
            layout_sdt = (LinearLayout)rootView.findViewById(R.id.layout_sdt);
            layout_gioitinh = (LinearLayout)rootView.findViewById(R.id.layout_gioitinh);
            layout_menh = (LinearLayout)rootView.findViewById(R.id.layout_menh);
            layout_ngaysinh = (LinearLayout)rootView.findViewById(R.id.layout_ngaysinh);

            if(p.getString(Pref.upb_dob,null)!=null){
                if(p.getString(Pref.upb_dob,null).length()>0){
                    layout_ngaysinh.setVisibility(View.VISIBLE);
                    txtInfoBirthday.setText(DateUtils.convertDateFormat(p.getString(Pref.upb_dob,null)));
                }else {
                    layout_ngaysinh.setVisibility(View.GONE);
                }
            }
            if(p.getString(Pref.upb_fate,null)!=null){
                if(p.getString(Pref.upb_fate,null).length()>0){
                    layout_menh.setVisibility(View.VISIBLE);
                    txtInfoMenh.setText(p.getString(Pref.upb_fate,null));
                }else {
                    layout_menh.setVisibility(View.GONE);
                }
            }
            if(p.getString(Pref.upb_gender,null)!=null){
                if(p.getString(Pref.upb_gender,null).length()>0){
                    layout_gioitinh.setVisibility(View.VISIBLE);
                    if(p.getString(Pref.upb_gender,null).equals("1")){
                        txtInfoSex.setText(getString(R.string.male));
                    }else if(p.getString(Pref.upb_gender,null).equals("2")){
                        txtInfoSex.setText(getString(R.string.remale));
                    }else if(p.getString(Pref.upb_gender,null).equals("3")){
                        txtInfoSex.setText(getString(R.string.other));
                    }
                }else {
                    layout_gioitinh.setVisibility(View.GONE);
                }
            }

            if(p.getString(Pref.upb_phone,null)!=null){
                if(p.getString(Pref.upb_phone,null).length()>0){
                    layout_sdt.setVisibility(View.VISIBLE);
                    txtInfoPhone.setText(p.getString(Pref.upb_phone,null));
                }else {
                    layout_sdt.setVisibility(View.GONE);
                }
            }
            if(p.getString(Pref.upb_email,null)!=null){
                if(p.getString(Pref.upb_email,null).length()>0){
                    layout_email.setVisibility(View.VISIBLE);
                    txtInfoEmail.setText(p.getString(Pref.upb_email,null));
                }else {
                    layout_email.setVisibility(View.GONE);
                }
            }
            if(p.getString(Pref.upb_address,null)!=null){
                if(p.getString(Pref.upb_address,null).length()>0){
                    layout_noisinh.setVisibility(View.VISIBLE);
                    txtInfoPlacesBirth.setText(p.getString(Pref.upb_address,null));
                }else {
                    layout_noisinh.setVisibility(View.GONE);
                }

            }
            if(p.getString(Pref.upb_address_temp,null)!=null){
                if(p.getString(Pref.upb_address_temp,null).length()>0){
                    layout_hienotai.setVisibility(View.VISIBLE);
                    txtInfoPlaces.setText(p.getString(Pref.upb_address_temp,null));
                }else {
                    layout_hienotai.setVisibility(View.GONE);
                }
            }
            if(p.getString(Pref.upb_website,null)!=null){
//                if(p.getString(Pref.upb_dob,null).length()>0){
//                    layout_menh.setVisibility(View.VISIBLE);
//                    txtInfoMenh.setText(p.getString(Pref.upb_fate,null));
//                }else {
//                    layout_menh.setVisibility(View.GONE);
//                }
                txtInfoMangXaHoi.setText(p.getString(Pref.upb_website,null));
            }
//        if(p.getString(Pref.upb_appearance,null)!=null){
//            txtInfoNgoaiHinh.setText(p.getString(Pref.upb_appearance,null));
//        }
            if(p.getString(Pref.upb_appearance,null)!=null){
                if(p.getString(Pref.upb_appearance,null).length()>0){
                    layout_ngoaihinh.setVisibility(View.VISIBLE);
                    if(p.getString(Pref.upb_appearance,null).equals("1")){
                        txtInfoNgoaiHinh.setText(getString(R.string.dep));
                    }else if(p.getString(Pref.upb_appearance,null).equals("2")){
                        txtInfoNgoaiHinh.setText(getString(R.string.ua_nhin));
                    }else if(p.getString(Pref.upb_appearance,null).equals("3")){
                        txtInfoNgoaiHinh.setText(getString(R.string.normal));
                    }
                }else {
                    layout_ngoaihinh.setVisibility(View.GONE);
                }

            }


            if(p.getString(Pref.upb_height,null)!=null){
                if(p.getString(Pref.upb_height,null).length()>0){
                    layout_chieucao.setVisibility(View.VISIBLE);
                    txtInfoHeight.setText(p.getString(Pref.upb_height,null));
                }else {
                    layout_chieucao.setVisibility(View.GONE);
                }
            }
            if(p.getString(Pref.upb_weight,null)!=null){
                if(p.getString(Pref.upb_weight,null).length()>0){
                    layout_cannang.setVisibility(View.VISIBLE);
                    txtInfoWeight.setText(p.getString(Pref.upb_weight,null));
                }else {
                    layout_cannang.setVisibility(View.GONE);
                }
            }
            if(p.getString(Pref.upb_measurements,null)!=null){
                if(p.getString(Pref.upb_measurements,null).length()>0){
                    layout_sodo3vong.setVisibility(View.VISIBLE);
                    try {
                        JSONObject jsonObjectSodo = new JSONObject(p.getString(Pref.upb_measurements,null));
                        txtInfoSoDo3Vong.setText(jsonObjectSodo.getString("v1")+" - "+jsonObjectSodo.getString("v2")+" - "+jsonObjectSodo.getString("v3"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else {
                    layout_sodo3vong.setVisibility(View.GONE);
                }
            }
            if(p.getString(Pref.upb_hobbies,null)!=null&&p.getString(Pref.upb_hobbies,null).length()>0){
                layout_sothich.setVisibility(View.VISIBLE);
                txtInfoHobbies.setVisibility(View.VISIBLE);
                txtInfoHobbies.setText(p.getString(Pref.upb_hobbies,null));
            }else {
                txtInfoHobbies.setVisibility(View.INVISIBLE);
                layout_sothich.setVisibility(View.GONE);
            }
            if(p.getString(Pref.upb_speaks,null)!=null&&p.getString(Pref.upb_speaks,null).length()>0){
                layout_noiduocgiong.setVisibility(View.VISIBLE);
                txtInfoNoiDuocGiong.setVisibility(View.VISIBLE);
                txtInfoNoiDuocGiong.setText(p.getString(Pref.upb_speaks,null));
            }else {
                layout_noiduocgiong.setVisibility(View.GONE);
                txtInfoNoiDuocGiong.setVisibility(View.INVISIBLE);
            }
            if(p.getString(Pref.upb_sings,null)!=null&&p.getString(Pref.upb_sings,null).length()>0){
                layout_hatduocgiong.setVisibility(View.VISIBLE);
                txtInfoHatDuocGiong.setVisibility(View.VISIBLE);
                txtInfoHatDuocGiong.setText(p.getString(Pref.upb_sings,null));
            }else {
                layout_hatduocgiong.setVisibility(View.GONE);
                txtInfoHatDuocGiong.setVisibility(View.INVISIBLE);
            }
            if(p.getString(Pref.upb_skills,null)!=null&&p.getString(Pref.upb_skills,null).length()>0){
                layout_kinang.setVisibility(View.VISIBLE);
                txtInfoSkills.setVisibility(View.VISIBLE);
                txtInfoSkills.setText(p.getString(Pref.upb_skills,null));
            }else {
                layout_kinang.setVisibility(View.GONE);
                txtInfoSkills.setVisibility(View.INVISIBLE);
            }
            if(p.getString(Pref.upb_special_skills,null)!=null&&p.getString(Pref.upb_special_skills,null).length()>0){
                layout_khanangdacbiet.setVisibility(View.VISIBLE);
                txtInfoKhaNangDacBiet.setVisibility(View.VISIBLE);
                txtInfoKhaNangDacBiet.setText(p.getString(Pref.upb_special_skills,null));
            }else {
                layout_khanangdacbiet.setVisibility(View.GONE);
                txtInfoKhaNangDacBiet.setVisibility(View.INVISIBLE);
            }
            if(p.getString(Pref.BOOLEAN_IS_ME,null)!=null){
                if(p.getString(Pref.BOOLEAN_IS_ME,null).equals("1")){
                    showToolBarItem(false);
                    txtInfoEmail.setVisibility(View.INVISIBLE);
                    txtInfoPhone.setVisibility(View.INVISIBLE);
                }else {
                    showToolBarItem(true);
                    txtInfoEmail.setVisibility(View.VISIBLE);
                    txtInfoPhone.setVisibility(View.VISIBLE);
                }
            }else {
                showToolBarItem(true);
                txtInfoEmail.setVisibility(View.VISIBLE);
                txtInfoPhone.setVisibility(View.VISIBLE);
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

            return rootView;
        }
    public void showToolBarItem(boolean isMe){
        if(isMe){
            mActivity.showItemToolBar(true, false, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = v.getId();
                    if (id == R.id.btnCreate) {
                        mActivity.selectItem(13);
                    }
                }
            });
        }else {
            mActivity.showItemToolBar(false, false, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }

    }

}
