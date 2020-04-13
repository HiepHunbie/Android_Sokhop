package com.example.ev.SoKhop.Fragment;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ev.SoKhop.Base.BaseFragment;
import com.example.ev.SoKhop.R;
import com.example.ev.SoKhop.Utils.Pref;

/**
 * Created by MSI on 10/24/2016.
 */

public class OtherUserPageFragment extends BaseFragment{
    public OtherUserPageFragment() {
    }
    private Pref p;
    private TextView txtInfoBirthday,txtInfoMenh,txtInfoSex,txtInfoPhone,txtInfoEmail,txtInfoPlacesBirth,txtInfoPlaces,
            txtInfoMangXaHoi,txtInfoNgoaiHinh,txtInfoHeight,txtInfoWeight,txtInfoSoDo3Vong,txtInfoHobbies,txtInfoNoiDuocGiong,
            txtInfoHatDuocGiong,txtInfoSkills,txtInfoKhaNangDacBiet;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_other_user_page, container, false);
//        activity = (InfomationActivity)getActivity();
        p = new Pref(getActivity());
        showToolBarItem();
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

        if(p.getString(Pref.upb_dob,null)!=null){
            txtInfoBirthday.setText(p.getString(Pref.upb_dob,null));
        }
        if(p.getString(Pref.upb_fate,null)!=null){
            txtInfoMenh.setText(p.getString(Pref.upb_fate,null));
        }
        if(p.getString(Pref.upb_gender,null)!=null){
            if(p.getString(Pref.upb_gender,null).equals("1")){
                txtInfoSex.setText(getString(R.string.male));
            }else if(p.getString(Pref.upb_gender,null).equals("2")){
                txtInfoSex.setText(getString(R.string.remale));
            }else if(p.getString(Pref.upb_gender,null).equals("3")){
                txtInfoSex.setText(getString(R.string.other));
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
        if(p.getString(Pref.upb_website,null)!=null){
            txtInfoMangXaHoi.setText(p.getString(Pref.upb_website,null));
        }
//        if(p.getString(Pref.upb_appearance,null)!=null){
//            txtInfoNgoaiHinh.setText(p.getString(Pref.upb_appearance,null));
//        }
        if(p.getString(Pref.upb_appearance,null)!=null){
            if(p.getString(Pref.upb_appearance,null).equals("1")){
                txtInfoNgoaiHinh.setText(getString(R.string.dep));
            }else if(p.getString(Pref.upb_appearance,null).equals("2")){
                txtInfoNgoaiHinh.setText(getString(R.string.normal));
            }else if(p.getString(Pref.upb_appearance,null).equals("3")){
                txtInfoNgoaiHinh.setText(getString(R.string.ua_nhin));
            }
        }


        if(p.getString(Pref.upb_height,null)!=null){
            txtInfoHeight.setText(p.getString(Pref.upb_height,null));
        }
        if(p.getString(Pref.upb_weight,null)!=null){
            txtInfoWeight.setText(p.getString(Pref.upb_weight,null));
        }
        if(p.getString(Pref.upb_measurements,null)!=null){
            txtInfoSoDo3Vong.setText(p.getString(Pref.upb_measurements,null));
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
        rootView.setFocusableInTouchMode(true);
        rootView.requestFocus();
        rootView.setOnKeyListener( new View.OnKeyListener()
        {
            @Override
            public boolean onKey( View v, int keyCode, KeyEvent event )
            {
                if( keyCode == KeyEvent.KEYCODE_BACK )
                {
//                    mActivity.selectItem(16);
                    return true;
                }
                return false;
            }
        } );

        return rootView;
    }
    public void showToolBarItem(){
        mActivity.showItemToolBar(false, false, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
            }
        });
    }
}
